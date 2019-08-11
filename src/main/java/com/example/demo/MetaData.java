package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaData {

	@JsonProperty("1. Information")
	String information;
	@JsonProperty("2. Symbol")
	String symbol;
	@JsonProperty("3. Last Refreshed")
	String lastRefreshed;
	@JsonProperty("4. Interval")
	String interval;
	@JsonProperty("5. Output Size")
	String OutputSize;
	@JsonProperty("6. Time Zone")
	String TimeZone;

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getOutputSize() {
		return OutputSize;
	}

	public void setOutputSize(String outputSize) {
		OutputSize = outputSize;
	}

	public String getTimeZone() {
		return TimeZone;
	}

	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}

}
