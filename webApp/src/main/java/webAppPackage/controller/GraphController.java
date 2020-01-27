package webAppPackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project001DomainPackage.UiInterfaceDomain;
import webAppPackage.activity.InitializerActivity;
import webAppPackage.service.LinearRegressionService;

@Controller
public class GraphController {

	@Autowired
	private InitializerActivity init;

	@Autowired
	UiInterfaceDomain uiInterfaceDomain;


	@RequestMapping(value = "/ui")
	public String details2GetByJpa2(Model model) {
		
		
		init.initializer();
		
		//using thymeleaf to pass values to UI.
		model.addAttribute("open", uiInterfaceDomain.getOpen());
		model.addAttribute("timeSpan", uiInterfaceDomain.getTimeSpan());
		model.addAttribute("volume", uiInterfaceDomain.getVolume());
		
		//some manual typecasting is done in order to use LinearRegression.class
		double[] o = new double[uiInterfaceDomain.getOpen().size()];
		int i = 0;
		for (double d : uiInterfaceDomain.getOpen()) {
			o[i++] = d;
		}
		double[] v = new double[uiInterfaceDomain.getVolume().size()];
		int j = 0;
		for (double d : uiInterfaceDomain.getVolume()) {
			v[j++] = d;
		}
		
		//using class LinearReggression
		LinearRegressionService linearRegression = new LinearRegressionService(o, v);
		model.addAttribute("predictedValue", linearRegression.predictedValue(o));
		return "ui";
	}

	@RequestMapping(value = "/")
	public String details2GetByJpa3() {
		return "index";
	}

}
