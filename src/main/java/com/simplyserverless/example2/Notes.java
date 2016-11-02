package com.simplyserverless.example2;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.simplyserverless.Request;
import com.simplyserverless.Response;

import java.util.Date;

public class Notes {
    LambdaLogger logger;
    Table table;

    private void init(Context context) {
        Statics statics = new Statics(context).invoke();
        logger = statics.getLogger();
        table = statics.getTable();
    }

    public Response insertNote(Request request, Context context) {
        init(context);

        try {
            logger.log("Creating a new item...");
            logger.log("Request: " + request.toString());
            if (request.getParams() != null)
                if (request.getParams().get("data") != null)
                    logger.log("Hello " + request.getParams().get("data").get("note"));
                else
                    logger.log("No data param found");
            else
                logger.log("No params found: " + request.getParams());
            Item myItem = new Item()
                    .withPrimaryKey("Notetime", new Date().toString())
                    //srsly? put some null guards at a minimum here
                    .withString("Content", request.getParams().get("querystring").get("note"));

            logger.log("Adding a new item...");
            PutItemOutcome outcome = table.putItem(myItem);

            logger.log("PutItem succeeded:\n" + outcome.getPutItemResult());

            return new Response("Inserted a name");

        } catch (Exception e) {
            logger.log("Unable to add item");
            logger.log("MSG=>>" + e + " which says: " + e.getMessage() + "<<=MSG");
            return new Response("Failed to insert a name: " + e.getLocalizedMessage());
        }

    }


    public Response listNotes(Request request, Context context) {

        init(context);

        try {
            logger.log("getting an item...");


            logger.log("GetItem succeeded:\n" );

            return new Response("Got a name ");

        } catch (Exception e) {
            logger.log("Unable to get item");
            logger.log("MSG=>>" + e.getMessage() + "<<=MSG");
            return new Response("Failed to get a name" + e.getLocalizedMessage() + "/MSG");
        }
    }

    public Response deleteNotes(Request request, Context context) {
        return new Response("Emptied the list");
    }

    private static class Statics {
        private Context context;
        private LambdaLogger logger;
        private Table table;

        public Statics(Context context) {
            this.context = context;
        }

        public LambdaLogger getLogger() {
            return logger;
        }

        public Table getTable() {
            return table;
        }

        public Statics invoke() {
            logger = context.getLogger();
            logger.log("Hello Logs!!!");

            AmazonDynamoDBClient client = new AmazonDynamoDBClient(
                    new EnvironmentVariableCredentialsProvider());

            client.setRegion(Region.getRegion(Regions.EU_WEST_1));
            client.setSignerRegionOverride("eu-west-1");

            DynamoDB dynamoDB = new DynamoDB(client);

            table = dynamoDB.getTable("Notes");

            logger.log("Table: " + table);
            return this;
        }
    }
}





