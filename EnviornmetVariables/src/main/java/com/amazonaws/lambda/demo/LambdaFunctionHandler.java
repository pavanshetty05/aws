package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        String region = System.getenv("AWS_REGION");
        context.getLogger().log("Region  >>>>>>>>>>>>>>>>> " + region);
        String userName = System.getenv("UESR_NAME");
        context.getLogger().log("User Name   >>>>>>>>>>>>>>>>> " + userName);
        return "Hello from Lambda!";
    }

}
