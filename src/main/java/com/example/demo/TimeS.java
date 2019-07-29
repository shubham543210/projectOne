package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeS {
	
	
	@JsonProperty("2019-07-26 16:00:00")
	TimeSeries information;

	public TimeSeries getInformation() {
		return information;
	}

	public void setInformation(TimeSeries information) {
		this.information = information;
	}
	

}
