package com.inovvet.app.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
	
	@Bean
	@Primary
	public AWSCredentialsProvider buildDefaultAWSCredentialsProvider() {

		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAQ2TALS2FUHYWTM4Z", "3HbEkJTbYl9z3rhI8jfGQWu1w3JLli7lmm7iUWis");
	    return new AWSStaticCredentialsProvider(awsCreds);
	    
	}
	
	@Bean
	public AmazonS3 amazonS3Client(AWSCredentialsProvider credentialsProvider,
	                               @Value("${cloud.aws.region.static}") String region) {
	    return AmazonS3ClientBuilder
	            .standard()
	            .withCredentials(credentialsProvider)
	            .withRegion(region)	         
	            .build();
	}

}
