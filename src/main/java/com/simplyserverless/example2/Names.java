package com.simplyserverless.example2;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.simplyserverless.Request;
import com.simplyserverless.Response;

public class Names {

    public  Response insertName(Request request, Context context) {
        return new Response("Inserted a name");
    }

    public  Response listNames(Request request, Context context) {
        return new Response("List of names");
    }

    public  Response clearNames(Request request, Context context) {
        return new Response("Emptied the list");
    }
}
