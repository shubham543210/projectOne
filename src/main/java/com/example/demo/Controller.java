package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private ServiceClass service;

	@RequestMapping(method = RequestMethod.GET, value = "/getData")
	public void details2GetByJpa() {
		 service.getData();
	}

}
