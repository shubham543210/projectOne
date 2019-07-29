package com.example.demo;

import java.util.Iterator;
import java.util.Map;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
@JsonIgnoreProperties(ignoreUnknown = false)
public class Data {
	
	@Autowired TimeSeries timeSeriesData;
   
	@JsonProperty("Meta Data")
	private MetaData metaData;
	
	@JsonProperty("Time Series (15min)")
	private TimeS timeSeries;
	
	@JsonProperty("Time Series (15min)")
	private JSONObject timeSeries1;
	
	{
		
	
	}
	
	//mongo "mongodb://cluster0-shard-00-00-ovwhc.mongodb.net:27017,cluster0-shard-00-01-ovwhc.mongodb.net:27017,cluster0-shard-00-02-ovwhc.mongodb.net:27017/test?replicaSet=Cluster0-shard-0" --ssl --authenticationDatabase admin --username shubham --password <password>
	

	MongoClient mongoClient = MongoClients.create("mongodb://shubham:Shubham.2@cluster0-shard-00-00-ovwhc.mongodb.net:27017,cluster0-shard-00-01-ovwhc.mongodb.net:27017,cluster0-shard-00-02-ovwhc.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
    // Accessing the database 
    MongoDatabase database = mongoClient.getDatabase("mydb"); 

    // Retrieving a collection
    MongoCollection<Document> collection = database.getCollection("sampleCollection"); 
   
    public void removeAll()
    {
    	BasicDBObject document = new BasicDBObject();
    	collection.deleteMany(document);
    	System.out.println("document removed successfully");
    }
	public void setValue()
	{
				     
						
						  Document document = new Document("Time", timeSeriesData.getTime()) 
							        .append("open", timeSeriesData.getOpen())
							        .append("high", timeSeriesData.getHigh()) 
							        .append("low", timeSeriesData.getLow()) 
							        .append("close", timeSeriesData.getClose()) 
							        .append("volume", timeSeriesData.getVolume());  
							        collection.insertOne(document); 
							        System.out.println("Document inserted successfully");
		        
		     // Creating a Mongo client 
		        
		        
		    
		    

		        
		       
		     } 
		        

		        // do something here with the value...
		    

//    public JSONObject getTimeSeries() {
//		return timeSeries;
//	}
//
//
//
//	public void setTimeSeries(JSONObject timeSeries) {
//		this.timeSeries = timeSeries;
//	}

	
	public MetaData getMetaData() {
		return metaData;
	}

	@Override
	public String toString() {
		return "Data [metaData=" + metaData + "]";
	}

	public TimeS getTimeSeries() {
		return timeSeries;
	}

	public void setTimeSeries(TimeS timeSeries) {
		this.timeSeries = timeSeries;
	}

//	public void setMetaData(MetaData metaData) {
//		this.metaData = metaData;
//	}
//
//	public String getTimeSeries() {
//		return timeSeries;
//	}
//
//	public void setTimeSeries(String timeSeries) {
//		this.timeSeries = timeSeries;
//	}
//


}
