package project001DomainPackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class TimeSDomain {

	@JsonProperty("2019-07-26 16:00:00")
	TimeSeriesDomain information;

	public TimeSeriesDomain getInformation() {
		return information;
	}

	public void setInformation(TimeSeriesDomain information) {
		this.information = information;
	}

}
