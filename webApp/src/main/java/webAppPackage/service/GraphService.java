package webAppPackage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project001DomainPackage.TimeSeriesDomain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Service
public class GraphService {

	@Autowired
	DataService data;

	@Autowired
	TimeSeriesDomain timeSeriesDomain;
	RestTemplate restTemplate = new RestTemplate();

	// After every 36 seconds this method will be excequted automatically.
	@Scheduled(fixedRate = 900000)
	public void getData() {

		try {
			data.removeAll();
			/*
			 * Url with complete information like ACN here denotes accenture. Apikey is user
			 * specific. For complete info please visit alphavantage documentation site. You
			 * can also run directly on postman and check results.
			 */
			URL url = new URL(
					"https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=ACN&interval=15min&apikey=JVNI9RRYW2W6WSKG");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			// Fetching response from webservice
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String message = org.apache.commons.io.IOUtils.toString(br);
			connection.getResponseCode();
			connection.disconnect();
			HashMap<String, Object> result = new ObjectMapper().readValue(message, HashMap.class);
			Map<String, Object> output = (Map<String, Object>) result.get("Time Series (15min)");

			// This is one time hit on webservice.It will fetch data only once when getData
			// method is called.
			System.out.println("new hit at webservice \n " + output.keySet());

			// The data that we get here is 15 min series data. with total approx 600 (15
			// min) data in this series.And after every 15 min one data set is added by
			// alphavantage.
			int i = output.size();

			// getting individual attributes from data set.
			for (Entry<String, Object> entry : output.entrySet()) {
				timeSeriesDomain.setTime(entry.getKey());
				Map<String, String> statics = (Map<String, String>) entry.getValue();
				timeSeriesDomain.setClose(statics.get("4. close"));
				timeSeriesDomain.setHigh(statics.get("2. high"));
				timeSeriesDomain.setLow(statics.get("3. low"));
				timeSeriesDomain.setOpen(statics.get("1. open"));
				timeSeriesDomain.setVolume(statics.get("5. volume"));

				// This will store data in mongodb.Note here that one set of 15 min data is
				// inserted once at a time. This insertion will continue till all 600 datasets
				// are inserted.
				data.setValue();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	// after every 3 min old data is deleted from mongodb so that we dont overshoot
	// 500 mb of data alloted to us at cloud.
//	@Scheduled(fixedRate = 890000)
//	public void removeAllService() {
//		data.removeAll();
//	}
}