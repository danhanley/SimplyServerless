package com.simplyserverless.example2;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.simplyserverless.Request;
import com.simplyserverless.Response;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Names {

    public  Response insertName(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Hello Logs!!!");

        AmazonDynamoDBClient client = new AmazonDynamoDBClient(
                new EnvironmentVariableCredentialsProvider());

        client.setRegion(Region.getRegion(Regions.EU_WEST_1));
        client.setSignerRegionOverride("eu-west-1");

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Peeps");

        logger.log("Table: " + table);

        try {
            logger.log("Creating a new item...");
            Item myItem = new Item()
                    .withPrimaryKey("FirstName", "paul")
                    .withString("Surname", "west");

            logger.log("Adding a new item...");
            PutItemOutcome outcome = table.putItem(myItem);

            logger.log("PutItem succeeded:\n" + outcome.getPutItemResult());

            return new Response("Inserted a name");

        } catch (Exception e) {
            logger.log("Unable to add item");
            logger.log("MSG=>>"+e+" which says: "+e.getMessage()+"<<=MSG");
            return new Response("Failed to insert a name" + e.getLocalizedMessage() + "/MSG");
        }

    }

    public  Response listNames(Request request, Context context) {


            LambdaLogger logger = context.getLogger();
            logger.log("Hello Logs!!!");

            AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
            DynamoDB dynamoDB = new DynamoDB(client);

            Table table = dynamoDB.getTable("Names");

            try {
                logger.log("getting an item...");
                Item item = table.getItem("Name", "fred");

                logger.log("GetItem succeeded:\n" + item);

                return new Response("Got a name " + item);

            } catch (Exception e) {
                logger.log("Unable to get item");
                logger.log("MSG=>>"+e.getMessage()+"<<=MSG");
                return new Response("Failed to get a name" + e.getLocalizedMessage() + "/MSG");
            }
    }

    public  Response clearNames(Request request, Context context) {
        return new Response("Emptied the list");
    }
}





