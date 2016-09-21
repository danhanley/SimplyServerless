package com.simplyserverless.example1;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class CallMe implements RequestHandler<Request,Response>{
    public  Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Hello Logs!!!");
        if (request.getParams() != null)
            if (request.getParams().get("querystring") != null)
                return new Response("Query parameter q1 = " + request.getParams().get("querystring").get("q1"));
            else
                return new Response("No query params found");
        else
            return new Response("No params found");
    }
}
