package com.example.tekushop.common;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.InputStream;
import java.net.URL;

public class FileUploadHandler {


    private static final String ACCESS_KEY = "KxmKn8Fajj23M7k4";
    private static final String SECRET_KEY = "GFzxrhF83QGzeblcwPdDObNKx9VksN4famraakN2";
    private static final String ENDPOINT_URL = "http://s3.tebi.io";
    private static final String REGION = "US";
    private static final String BUCKET_NAME = "tekushop";

    private AmazonS3 s3Client;

    public FileUploadHandler() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_URL, REGION))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .enablePathStyleAccess()
                .build();
    }

    public String uploadFileToS3(String fileName, InputStream fileContent) {
        try {
            String bucketFilePath = fileName;
            PutObjectRequest putRequest = new PutObjectRequest(BUCKET_NAME, bucketFilePath, fileContent, new ObjectMetadata());
            putRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(putRequest);
            URL fileUrl = s3Client.getUrl(BUCKET_NAME, bucketFilePath);
            fileContent.close();
            return fileUrl.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}