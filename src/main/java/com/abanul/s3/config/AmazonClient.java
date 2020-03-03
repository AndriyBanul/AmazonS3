package com.abanul.s3.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AmazonClient {

    private AmazonS3 client;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3endpoint}")
    private String dbEndpoint;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.bucketName}")
    private String bucketName;

    @PostConstruct
    public void init(){
        AWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        this.client = new  AmazonS3Client(creds);
    }

    public void upload (MultipartFile file) throws IOException {
        File singleFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(singleFile);
        fos.write(file.getBytes());
        fos.close();
        client.putObject(new PutObjectRequest(bucketName, singleFile.getName(), singleFile));
    }

}
