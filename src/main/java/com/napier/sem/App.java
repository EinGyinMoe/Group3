package com.napier.sem;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class App
{
    public static void main(String[] args)
    {
        // Connecting to MongoDB on local system by using port 27000
        MongoClient mongoClient = new MongoClient("mongo-dbserver");
        // Get a database - will create when we use it
        MongoDatabase database = mongoClient.getDatabase("mydb");
        // Get a collection from the database
        MongoCollection<Document> collection = database.getCollection("test");
        // Creating a document to store our info
        Document doc = new Document("name", "Group3")
                .append("class", "BSc Hons")
                .append("year", "2019")
                .append("result", new Document("CW", 95).append("EX", 85));
        // Add document to collection
        collection.insertOne(doc);

        // Check document in collection
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
}