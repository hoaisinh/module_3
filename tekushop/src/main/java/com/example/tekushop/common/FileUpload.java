package com.example.tekushop.common;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUpload {
    private static final String UPLOAD_DIR = "";

    public static String uploadFile(Part filePart, String uploadPath) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);
        return fileName;
    }

}
