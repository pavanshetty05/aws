package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class LambdaFunctionHandler implements RequestHandler<Object, Object> {

    @Override
    public Object handleRequest(Object message, Context context) {
        context.getLogger().log("Received event Came inside the project " );
        context.getLogger().log("Second version >>>>>>>>>> " );
        context.getLogger().log("Recived message >>>>>>>>" + message);
        return message;
    }
}
