package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class Controller1 {

	@Autowired
	private ServiceClass service;

	@Autowired
	UiInterface uiInterface;


	@RequestMapping(value = "/ui")
	public String details2GetByJpa2(Model model) {
		
		
		service.getData();
		
		//using thymeleaf to pass values to UI.
		model.addAttribute("open", uiInterface.getOpen());
		model.addAttribute("timeSpan", uiInterface.getTimeSpan());
		model.addAttribute("volume", uiInterface.getVolume());
		
		//some manual typecasting is done in order to use LinearRegression.class
		double[] o = new double[uiInterface.getOpen().size()];
		int i = 0;
		for (double d : uiInterface.getOpen()) {
			o[i++] = d;
		}
		double[] v = new double[uiInterface.getVolume().size()];
		int j = 0;
		for (double d : uiInterface.getVolume()) {
			v[j++] = d;
		}
		
		//using class LinearReggression
		LinearRegression linearRegression = new LinearRegression(o, v);
		model.addAttribute("predictedValue", linearRegression.predictedValue(o));
		return "ui";
	}

	@RequestMapping(value = "/")
	public String details2GetByJpa3() {
		return "index";
	}

}
