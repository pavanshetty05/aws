package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

// This project will  designed to trigger the destination  . 
// If  the event code is success it will push in success SQS que if even is fail it will push it in the 
//failure que  
public class LambdaFunctionHandler implements RequestHandler<S3EventNotification, String> {

	private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

	int evenQue = 0;

	public LambdaFunctionHandler() {
	}

	// Test purpose only.
	LambdaFunctionHandler(AmazonS3 s3) {
		this.s3 = s3;
	}

	@Override
	public String handleRequest(S3EventNotification event, Context context) {
		
		context.getLogger().log("Value of the Event >>>>>>>>>>>>>" + evenQue);

		if (evenQue % 2 == 0) {
			try {
				throw new MyOwnExpcetion("Caused exocetion>>>>>>>>>");
			} catch (MyOwnExpcetion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		context.getLogger().log("Received event: " + event);

		// Get the object from the event and show its content type
		String bucket = event.getRecords().get(0).getS3().getBucket().getName();
		String key = event.getRecords().get(0).getS3().getObject().getKey();
		try {
			S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
			String contentType = response.getObjectMetadata().getContentType();
			context.getLogger().log("CONTENT TYPE: " + contentType);
			return contentType;
		} catch (Exception e) {
			e.printStackTrace();
			context.getLogger().log(String.format("Error getting object %s from bucket %s. Make sure they exist and"
					+ " your bucket is in the same region as this function.", key, bucket));
			throw e;
		}
		}
		
		
		context.getLogger().log("Updated the count >>>>>>  " + event);
		evenQue++;
		return null;
	}
}