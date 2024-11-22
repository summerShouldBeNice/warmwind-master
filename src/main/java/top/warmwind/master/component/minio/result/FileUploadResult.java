package top.warmwind.master.component.minio.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文件上传返回结果
 *
 * @author warmwind
 * @since 2024-11-22 上午11:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件上传返回结果")
public class FileUploadResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "文件url")
    private String url;

    @Schema(description = "文件预览url")
    private String previewUrl;

}
