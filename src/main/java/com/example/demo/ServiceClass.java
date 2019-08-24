package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ServiceClass {

	@Autowired
	Data data;

	@Autowired
	TimeSeries timeSeries;
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
				timeSeries.setTime(entry.getKey());
				Map<String, String> statics = (Map<String, String>) entry.getValue();
				timeSeries.setClose(statics.get("4. close"));
				timeSeries.setHigh(statics.get("2. high"));
				timeSeries.setLow(statics.get("3. low"));
				timeSeries.setOpen(statics.get("1. open"));
				timeSeries.setVolume(statics.get("5. volume"));

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