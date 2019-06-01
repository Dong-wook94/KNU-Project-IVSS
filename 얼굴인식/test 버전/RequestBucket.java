package com.amazonaws.samples;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class RequestBucket {
	final static AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider()).withRegion("ap-northeast-2").build();
	//private static final String bucketName = "detected-image";
	private static final String filePath = "C:\\Users\\dongwook\\Desktop\\data\\";
	public void UploadtoBucket(String bucketName,String fileName) {
		// TODO Auto-generated method stub
		try {
			//System.out.println("������ �׻� path������ �����ؾߵȴ�.\n���� �̸� �Է� : ");
			//Scanner scan = new Scanner(System.in);
	        //String fileName = scan.nextLine();//�����̸� �Է¹���

	        PutObjectRequest request = new PutObjectRequest(bucketName,fileName,new File(filePath+fileName));
	        s3.putObject(request);
	       
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void BucketToBucket(String from_bucket,String to_bucket, String fileName) {
		try {
			s3.copyObject(from_bucket,fileName,to_bucket,fileName);		}
		catch(Exception e){
			e.printStackTrace();
			 System.exit(1);
		}
	}
	
	public void GetURL(String bucketName, String fileName) {
		 System.out.println(s3.getUrl(bucketName, fileName));
	}
	
	public void GrantACL(String bucketName, String fileName) {
		AccessControlList acl = s3.getObjectAcl(bucketName, fileName);
		Permission permission = Permission.FullControl;
		acl.grantAllPermissions();
		
	}
	
}
