package com.cvicse.jy1.web.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 

import com.cvicse.jy1.config.ApplicationProperties;
import com.cvicse.jy1.domain.Document;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

/**                                                                                                                                                                                                  
 * REST controller for managing {@link com.cvicse.jy1.domain.Documentmenu}.
 */
@RestController
@RequestMapping("/api/files")
public class FileUploadResource {

    private final Path fileStorageLocation;

    public FileUploadResource(ApplicationProperties properties) {
        String uploadDir = properties.getFile().getUploadDir();
        if (uploadDir == null || uploadDir.isEmpty()) {
            throw new IllegalArgumentException("Upload directory is not configured properly.");
        }
        this.fileStorageLocation = Paths.get(uploadDir);
        System.out.println("Upload directory: " + this.fileStorageLocation.toAbsolutePath());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("11111111111111111111111111文件名称: " + file.getOriginalFilename());
            System.out.println("11111111111111111111111111文件大小: " + file.getSize());
            System.out.println("11111111111111111111111111上传路径: " + this.fileStorageLocation.toAbsolutePath());
            if (!Files.exists(this.fileStorageLocation)) {
                Files.createDirectories(this.fileStorageLocation);
            }
            String subDir = "ceshi";
            Path targerLocation = this.fileStorageLocation.resolve(subDir);
            Files.createDirectories(targerLocation);
            Path targetFile = targerLocation.resolve(file.getOriginalFilename());
            System.out.println("11111111111111111111111111文件新的路径: " + targerLocation);
            System.out.println("11111111111111111111111111文件: " + targetFile);
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
            // Files.copy(file.getInputStream(), this.fileStorageLocation.resolve(file.getOriginalFilename()));
            return ResponseEntity.ok("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = fileStorageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("")
    public void getAllDocuments() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    // @GetMapping("")
    // public ResponseEntity<Stream<Path>> listUploadedFiles() {
    //     try {
    //         Stream<Path> files = Files.walk(this.fileStorageLocation, 1)
    //             .filter(path -> !path.equals(this.fileStorageLocation))
    //             .map(this.fileStorageLocation::relativize);
    //         return ResponseEntity.ok(files);
    //     } catch (IOException e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
    // }
}
