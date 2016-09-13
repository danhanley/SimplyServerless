package com.simplyserverless.example1;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class CallMe implements RequestHandler<Request,Response>{
    public  Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Hello Logs");

        return new Response("The server says: " + request.param1 + " was recieved and returned.");
    }
}
