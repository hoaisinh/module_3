package com.example.tekushop;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.List;
public class MainS3 {
    public static void main(String[] args) {
        // Thông tin về tài khoản AWS và S3 endpoint
        String accessKey = "KxmKn8Fajj23M7k4";
        String secretKey = "GFzxrhF83QGzeblcwPdDObNKx9VksN4famraakN2";
        String endpointUrl = "http://s3.tebi.io";  // Endpoint tùy chỉnh (ví dụ: tebex.io)
        String region = "US";  // Vùng dữ liệu của S3
        String bucketName = "tekushop";  // Tên bucket S3 của bạn
        String bucketFilePath = "This is the path in your bucket";  // Đường dẫn trong bucket
        String filePath = "This is your local path";  // Đường dẫn tệp bạn muốn tải lên

        // Tạo AWS Credentials
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // Tạo S3 Client
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl, region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .enablePathStyleAccess()  // Kích hoạt truy cập theo đường dẫn
                .build();

//        // Tải tệp lên S3
//        uploadFileToS3(s3Client, bucketName, bucketFilePath, filePath);
    }

    // Tải tệp lên S3
    public static void uploadFileToS3(AmazonS3 s3Client, String bucketName, String bucketFilePath, String filePath) {
        PutObjectRequest request = new PutObjectRequest(bucketName, bucketFilePath, new File(filePath));
        request.setCannedAcl(CannedAccessControlList.PublicRead);  // Thiết lập quyền truy cập công khai (nếu cần)

        s3Client.putObject(request);  // Thực hiện tải tệp lên

        System.out.println("File uploaded successfully to " + bucketFilePath);
    }


}
