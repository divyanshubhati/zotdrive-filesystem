package org.filesystem.storage.service;

import org.filesystem.storage.api.FileChunkRequest;
import org.filesystem.storage.dto.FileUploadDTO;
import org.filesystem.storage.notification.FileUploadNotificationService;
import org.filesystem.storage.validator.FileValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Set;

@Service
public class FileIOOrchestrator {

    private final FileValidator fileValidator;
    private final FileStorageService fileStorageService;
    private final FileUploadNotificationService fileUploadNotificationService;

    public FileIOOrchestrator(FileValidator fileValidator,
                              FileStorageService fileStorageService,
                              FileUploadNotificationService fileUploadNotificationService) {
        this.fileValidator = fileValidator;
        this.fileStorageService = fileStorageService;
        this.fileUploadNotificationService = fileUploadNotificationService;
    }

    public void uploadFile(MultipartFile multipartFile, FileUploadDTO fileUploadDTO) {

        fileValidator.validateFile(multipartFile, fileUploadDTO);
        fileStorageService.storeFile(multipartFile, fileUploadDTO);
        fileUploadNotificationService.publishFileUploadNotification(fileUploadDTO);
    }

    public void deleteFile(String fileName, FileChunkRequest fileChunkRequest) {
        fileStorageService.deleteFile(fileName, fileChunkRequest);
    }

    public Set<String> getFiles() throws IOException {
        return fileStorageService.getFiles();
    }

    public Resource getFileAsResource(String fileName) throws IOException{
        return  fileStorageService.getFileAsResource(fileName);
    }

}
