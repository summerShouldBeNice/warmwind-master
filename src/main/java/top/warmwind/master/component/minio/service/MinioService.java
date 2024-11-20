package top.warmwind.master.component.minio.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.minio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.warmwind.master.core.exception.BizException;
import top.warmwind.master.core.exception.ParameterException;
import top.warmwind.master.core.utils.VerifyUtil;
import top.warmwind.master.core.web.ApiResult;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * @author warmwind
 * @since 2024-11-20 下午4:28
 */
@Service
public class MinioService {

    /**
     * 合法文件后缀名 字符串逗号分隔
     */
    @Value("${minio.suffixs}")
    private String suffixs;

    private MinioClient minioClient;

    @Autowired
    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    private final Logger logger = LoggerFactory.getLogger(MinioService.class.getName());

    /**
     * 上传文件到指定的bucket
     * @param file
     * @param bucketName
     * @return
     */
    public ApiResult<?> uploadToBucket(MultipartFile file, String bucketName) {
        if (Objects.isNull(file)) {
            throw new ParameterException("上传失败：上传文件错误");
        }
        if (StrUtil.isBlank(bucketName)) {
            throw new ParameterException("上传失败：上传存储位置错误");
        }
        try {
            // 文件名称
            String fileName = file.getOriginalFilename();
            // 文件类型
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 验证文件格式
            if (!VerifyUtil.matchText(suffixName, suffixs)) {
                throw new ParameterException("上传失败：上传文件不合法");
            }
            // 新文件名
            String objectName = IdUtil.simpleUUID() + suffixName;
            // 文件类型
            String fileType = file.getContentType();
            // 日期路径(当前日期)
            String datePath = DateUtil.format(new Date(), "yyyy/MM/dd");
            // 路径 + 文件对象
            String path = datePath + "/" + objectName;
            // 上传
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(fileType).build());
            // 获取文件URL
            GetObjectResponse object = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path).build());
            System.out.println(object);
            return null;
        } catch (Exception e) {
            logger.error("MINIO ERROR", e);
            throw new BizException("文件上传异常");
        }
    }
    
}
