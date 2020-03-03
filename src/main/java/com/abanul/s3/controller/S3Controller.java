package com.abanul.s3.controller;

import com.abanul.s3.config.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Controller {

    @Autowired
    public AmazonClient amazonClient;

    @PostMapping
    public void postFile(@RequestParam MultipartFile file) throws IOException {
        amazonClient.upload(file);
    }

}
