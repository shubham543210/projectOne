package com.example.demo;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class TimeSeries {

	@JsonProperty("1. open")
	String open;
	@JsonProperty("2. high")
	String high;
	@JsonProperty("3. low")
	String low;
	@JsonProperty("4. close")
	String close;
	@JsonProperty("5. volume")
	String volume;

	String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "TimeSeries [open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume="
				+ volume + "]";
	}

}
