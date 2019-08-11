package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class UiInterface {
	
	private List<Double> open=new ArrayList<>();
	private List<Double> timeSpan=new ArrayList<>();
	private List<Double> volume=new ArrayList<>();
	

	public List<Double> getTimeSpan() {
		return timeSpan;
	}

	public List<Double> getVolume() {
		return volume;
	}

	public void setVolume(List<Double> volume) {
		this.volume = volume;
	}

	public void setTimeSpan(List<Double> timeSpan) {
		this.timeSpan = timeSpan;
	}

	public List<Double> getOpen() {
		return open;
	}

	public void setOpen(List<Double> open) {
		this.open = open;
	}

}
