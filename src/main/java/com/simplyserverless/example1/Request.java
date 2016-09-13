package com.simplyserverless.example1;

/**
 * Created by dan on 13/09/16.
 */
public class Request {
    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    String param1;


    public Request(String param1) {
        this.param1 = param1;
    }

    public Request() {
    }
}