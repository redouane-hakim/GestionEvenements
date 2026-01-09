package ma.xproce.gestionevenements.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public interface FileStorageService {

    public String storeImage(MultipartFile file) throws IOException ;

    private String getExt(String name) { return null ; }
}
