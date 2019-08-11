package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

	@Autowired
	TimeSeries timeSeriesData;
	@Autowired
	UiInterface uiInterface;

	@JsonProperty("Meta Data")
	private MetaData metaData;

	@JsonProperty("Time Series (15min)")
	private TimeS timeSeries;

	@JsonProperty("Time Series (15min)")
	private JSONObject timeSeries1;

	// making connection with mongo atlas.You can get complete usage details and
	// other statics if you login in mongo atlas.
	MongoClient mongoClient = MongoClients.create(
			"mongodb://shubham:Shubham.2@cluster0-shard-00-00-ovwhc.mongodb.net:27017,cluster0-shard-00-01-ovwhc.mongodb.net:27017,cluster0-shard-00-02-ovwhc.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
	// Accessing the database
	MongoDatabase database = mongoClient.getDatabase("mydb");

	// Retrieving a collection
	MongoCollection<Document> collection = database.getCollection("sampleCollection");

	List<Double> list = new ArrayList<>();
	List<Double> volume = new ArrayList<>();
	List<Double> timeSer = new ArrayList<>();

	// method to empty collection and lists.
	public void removeAll() {
		BasicDBObject document = new BasicDBObject();
		collection.deleteMany(document);
		System.out.println("document removed successfully");
		list.clear();
		uiInterface.setOpen(list);
		timeSer.clear();
		uiInterface.setTimeSpan(timeSer);
		volume.clear();
		uiInterface.setVolume(volume);

	}

	int i = 0;
	double j = 0;

	public void setValue() {
		list.add(Double.parseDouble(timeSeriesData.getOpen()));
		volume.add(Double.parseDouble(timeSeriesData.getVolume()));

		Document document = new Document("Time", timeSeriesData.getTime()).append("open", timeSeriesData.getOpen())
				.append("high", timeSeriesData.getHigh()).append("low", timeSeriesData.getLow())
				.append("close", timeSeriesData.getClose()).append("volume", timeSeriesData.getVolume());

		// note-- 15 min data set is entered as one document.
		collection.insertOne(document);
		System.out.println("Document inserted successfully in mongo");

		uiInterface.setOpen(list);
		uiInterface.setVolume(volume);

		timeSer.add(j);

		uiInterface.setTimeSpan(timeSer);
		j++;
		i++;

	}

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

}
