package io.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.springboot.main.models.LocationStats;
import io.springboot.main.services.CoronaVirusDataServices;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataServices coronaVirusDataServices;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<LocationStats>allStats = coronaVirusDataServices.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatesttotalcases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDifffromprevDay()).sum();

		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases",totalNewCases);


		return "home";
		
	}
	
	
}
