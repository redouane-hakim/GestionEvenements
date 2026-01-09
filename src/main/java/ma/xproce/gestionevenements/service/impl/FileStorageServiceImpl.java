package ma.xproce.gestionevenements.service.impl;

import ma.xproce.gestionevenements.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    private static final Set<String> ALLOWED = Set.of("image/png", "image/jpeg", "image/webp");

    public String storeImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        if (!ALLOWED.contains(file.getContentType())) {
            throw new IllegalArgumentException("Only PNG/JPG/WEBP images are allowed");
        }

        Path dir = Path.of(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(dir);

        String original = file.getOriginalFilename() == null ? "image" : file.getOriginalFilename();
        String ext = getExt(original);
        String filename = UUID.randomUUID() + ext;

        Path target = dir.resolve(filename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        // return URL saved in DB (not the filesystem path)
        return "/uploads/" + filename;
    }

    private String getExt(String name) {
        int i = name.lastIndexOf('.');
        if (i == -1) return "";
        return name.substring(i).toLowerCase();
    }
}