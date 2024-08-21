package com.cvicse.jy1.web.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSONObject;
import com.cvicse.jy1.config.ApplicationProperties;
import com.cvicse.jy1.domain.Documentmenu;
import com.cvicse.jy1.repository.DocumentmenuRepository;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

/**                                                                                                                                                                                                  
 * REST controller for managing {@link com.cvicse.jy1.domain.Documentmenu}.
 */
@RestController
@RequestMapping("/api/files")
public class FileUploadResource {

    private final Path fileStorageLocation;
    private final DocumentmenuRepository documentmenuRepository;

    public FileUploadResource(ApplicationProperties properties, DocumentmenuRepository documentmenuRepository) {
        String uploadDir = properties.getFile().getUploadDir();
        if (uploadDir == null || uploadDir.isEmpty()) {
            throw new IllegalArgumentException("Upload directory is not configured properly.");
        }
        this.fileStorageLocation = Paths.get(uploadDir);
        System.out.println("Upload directory: " + this.fileStorageLocation.toAbsolutePath());
        this.documentmenuRepository = documentmenuRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(
        @RequestParam("file") MultipartFile file,
        // @RequestParam("fileUrl") String fileUrl,
        @RequestParam("selected") String selected) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(selected);

            System.out.println("11111111111111111111111111路径selected是: " + selected);
            // System.out.println("11111111111111111111111111路径fileurl是: " + fileUrl);
            System.out.println("11111111111111111111111111文件名称: " + file.getOriginalFilename());
            System.out.println("11111111111111111111111111文件大小: " + file.getSize());
            System.out.println("11111111111111111111111111上传路径: " + this.fileStorageLocation.toAbsolutePath());
            if (!Files.exists(this.fileStorageLocation)) {
                Files.createDirectories(this.fileStorageLocation);
            }
            String urlparts[] = jsonObject.getString("fileurl").split("/");
            String url = "";
            System.out.println("11111111111111111111111111上传路径: " + urlparts);
            Path targerLocation = this.fileStorageLocation;
            for(int i=0 ; i<urlparts.length ; i++){
                url = urlparts[i];
                targerLocation = targerLocation.resolve(url);
                Files.createDirectories(targerLocation);
                System.out.println("11111111111111111111111111文件新的路径: " + targerLocation);
            }
            Path targetFile = targerLocation.resolve(file.getOriginalFilename());
            System.out.println("11111111111111111111111111文件: " + targetFile);
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
            // Files.copy(file.getInputStream(), this.fileStorageLocation.resolve(file.getOriginalFilename()));

            //保存文件目录
            Documentmenu documentmenu = new Documentmenu();
            documentmenu.setMenuid(jsonObject.getString("menuid")+"_"+file.getOriginalFilename());
            documentmenu.setBelongtype(jsonObject.getString("belongtype"));
            documentmenu.setMenuname(file.getOriginalFilename());
            documentmenu.setParentmenuid(jsonObject.getString("menuid"));
            documentmenu.setCreatetime(LocalDate.now());
            documentmenu.setType(2);
            documentmenu.setFileurl(targetFile.toString());
            System.out.println("保存文件目录参数："+documentmenu);
            documentmenuRepository.save(documentmenu); 
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!保存文件目录完成");


            return ResponseEntity.ok("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileurl") String fileurl) {
        try {
            System.out.println("111111111111111111111下载参数:"+fileurl);
            String fileurlString[] = fileurl.split("/");
            String filename = fileurlString[fileurlString.length-1];
            System.out.println("111111111111111111111下载参数名称:"+filename);
            Path file = Paths.get(fileurl);
            System.out.println("111111111111111111111下载参数路径:"+file);
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

    @GetMapping("/download/s")
    public void getAllDocuments(@RequestParam("fileurl") String fileurl) {
        System.out.println("111111111111111111111下载参数:"+fileurl);
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
