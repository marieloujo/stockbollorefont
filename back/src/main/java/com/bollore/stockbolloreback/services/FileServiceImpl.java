package com.bollore.stockbolloreback.services;

import com.bollore.stockbolloreback.services.FileService;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class FileServiceImpl implements FileService {

    /**
     * Permet de d'enregister le fichier
     * @param file
     * @return String
     */

    String path = "src/main/resources/static/upload/";

    File file1 = new File(path);

    String 	DIRECTORY_PATH = file1.getAbsolutePath();

    public String storeFile(MultipartFile file) throws ParseException {

        String filename = genereateName() + "." + file.getOriginalFilename().split("\\.")[1];

        Path filePath = Paths.get( DIRECTORY_PATH + "/" + filename);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "http://127.0.0.1:9090/upload/" + filename;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }




    private String genereateName() throws ParseException {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fileName = formatter.format(ts);

        fileName = fileName.replaceAll(" ", "_").toLowerCase();
        fileName = fileName.replaceAll("-", "_").toLowerCase();
        fileName = fileName.replaceAll(":", "_").toLowerCase();

        return fileName;

    }




}