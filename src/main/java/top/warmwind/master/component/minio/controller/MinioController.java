package top.warmwind.master.component.minio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.warmwind.master.component.minio.service.MinioService;
import top.warmwind.master.core.annotation.OperationRecord;
import top.warmwind.master.core.basic.BaseController;
import top.warmwind.master.core.web.ApiResult;

/**
 * @author warmwind
 * @since 2024-11-22 上午11:28
 */
@Tag(name = "minio接口", description = "系统minio接口接口")
@RestController
@RequestMapping("/api/v1/minio")
public class MinioController extends BaseController {

    private MinioService minioService;

    @Autowired
    public MinioController(MinioService minioService) {
        this.minioService = minioService;
    }

    @OperationRecord(value = "Minio上传文件")
    @Operation(summary = "上传文件-存储桶")
    @PostMapping("/upload/bucket")
    public ApiResult<?> uploadBucket(@RequestParam MultipartFile file, String bucket)  {
        return success(minioService.uploadToBucket(file, bucket));
    }
}
