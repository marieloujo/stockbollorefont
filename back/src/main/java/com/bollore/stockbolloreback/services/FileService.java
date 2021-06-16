package com.bollore.stockbolloreback.services;

import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

public interface FileService {
    String storeFile(MultipartFile file) throws ParseException;
}