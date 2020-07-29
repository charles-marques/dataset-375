package be.uclouvain.yasmim.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("digital")
public class DigitalLocation extends Location implements Serializable {
    private static final long serialVersionUID = 1L;

    private String relativePath;
    private String fileName;
    private String fileExtension;
    private Long fileSize;
    private String fileContentType;
    private String absolutePath;

    public DigitalLocation() {}

    public DigitalLocation(Resource resource, String relativePath) {
        super.setResource(resource);
        this.relativePath = relativePath;
    }

    @Column(name="absolute_path")
    public String getAbsolutePath() {
        return this.absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Column(name="relative_path")
    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    @Column(name="file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name="file_extension")
    public String getFileExtension() {
        if(fileExtension == null)
            return fileExtension;
        else
            return fileExtension.toLowerCase();
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Column(name="file_size")
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Column(name="file_content_type")
    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
}