package com.example.crawler;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

public class MongoStorage {
    private final MongoDatabase db;

    public MongoStorage(MongoDatabase db) {
        this.db = db;
        db.getCollection("pages").createIndex(new Document("url", 1));
    }

    public void savePage(String url, int httpStatus, String title, String text, long contentLen) {
        Document doc = new Document("url", url)
                .append("status", httpStatus)
                .append("title", title)
                .append("text", text)
                .append("contentLen", contentLen)
                .append("crawledAt", new Date());
        try {
            db.getCollection("pages").insertOne(doc);
        } catch (Exception e) {
            // duplicate or other
        }
    }
}
