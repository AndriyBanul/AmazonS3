package com.abanul.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

    @Value("${cloud.aws.bucketName}")
    private String bucketName;

    @Autowired
    public AmazonS3Client amazonS3Client;

    public void uploadFile(MultipartFile file) throws IOException {
        File singleFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(singleFile);
        fos.write(file.getBytes());
        fos.close();
        amazonS3Client.putObject(new PutObjectRequest(bucketName, singleFile.getName(), singleFile));
    }
}
