package io.springboot.main.services;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.springboot.main.models.LocationStats;

@Service
public class CoronaVirusDataServices {
	
	private static String Virus_Data_Url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
		
		
	private List<LocationStats>  allStats= new ArrayList<>();
	
	
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}

	@PostConstruct                                       //to call mehtods once the application start
	@Scheduled(cron ="* * 1 * * *")                        // to run on daily bases and when file gets updated(* for sec, min,hr,day,month,yr)
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStats>  newStats= new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Virus_Data_Url))
				.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
     //   Reader in = new FileReader("path/to/file.csv");
    	//Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("Province/State").parse(csvBodyReader);
    	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

    	for (CSVRecord record : records) {
    		
    		LocationStats locationstat = new LocationStats();
    		locationstat.setState(record.get("Province/State"));
    		locationstat.setCountry(record.get("Country/Region"));
    		
    		
    		int latestcase = Integer.parseInt(record.get(record.size()-1));
    		int preDaycase = Integer.parseInt(record.get(record.size()-2));
    		
    		locationstat.setLatesttotalcases(latestcase);
    		locationstat.setDifffromprevDay(latestcase-preDaycase);

    		//System.out.println(locationstats);
    		newStats.add(locationstat);
    		
    		
    	  // System.out.println(state);
	}
    	this.allStats= newStats;
	
	
	}

}
