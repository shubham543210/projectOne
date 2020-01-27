package webAppPackage.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project001DomainPackage.MetaDataDomain;
import project001DomainPackage.TimeSDomain;
import project001DomainPackage.TimeSeriesDomain;
import project001DomainPackage.UiInterfaceDomain;

import java.util.ArrayList;
import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = false)
public class DataService {

	@Autowired
	TimeSeriesDomain timeSeriesDomainData;
	@Autowired
	UiInterfaceDomain uiInterfaceDomain;

	@JsonProperty("Meta Data")
	private MetaDataDomain metaDataDomain;

	@JsonProperty("Time Series (15min)")
	private TimeSDomain timeSeriesDomain;

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
		uiInterfaceDomain.setOpen(list);
		timeSer.clear();
		uiInterfaceDomain.setTimeSpan(timeSer);
		volume.clear();
		uiInterfaceDomain.setVolume(volume);

	}

	int i = 0;
	double j = 0;

	public void setValue() {
		list.add(Double.parseDouble(timeSeriesDomainData.getOpen()));
		volume.add(Double.parseDouble(timeSeriesDomainData.getVolume()));

		Document document = new Document("Time", timeSeriesDomainData.getTime()).append("open", timeSeriesDomainData.getOpen())
				.append("high", timeSeriesDomainData.getHigh()).append("low", timeSeriesDomainData.getLow())
				.append("close", timeSeriesDomainData.getClose()).append("volume", timeSeriesDomainData.getVolume());

		// note-- 15 min data set is entered as one document.
		collection.insertOne(document);
		System.out.println("Document inserted successfully in mongo"+ timeSeriesDomainData.getTime());

		uiInterfaceDomain.setOpen(list);
		uiInterfaceDomain.setVolume(volume);

		timeSer.add(j);

		uiInterfaceDomain.setTimeSpan(timeSer);
		j++;
		i++;

	}

	public MetaDataDomain getMetaDataDomain() {
		return metaDataDomain;
	}

	@Override
	public String toString() {
		return "Data [metaData=" + metaDataDomain + "]";
	}

	public TimeSDomain getTimeSeriesDomain() {
		return timeSeriesDomain;
	}

	public void setTimeSeriesDomain(TimeSDomain timeSeriesDomain) {
		this.timeSeriesDomain = timeSeriesDomain;
	}

}
