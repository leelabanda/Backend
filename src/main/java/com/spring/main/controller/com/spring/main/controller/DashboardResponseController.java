package com.spring.main.controller.com.spring.main.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.dto.DashBoardResponse;
import com.spring.main.dto.DashResponse;
import com.spring.main.dto.SummaryDto;
import com.spring.main.iservice.IDashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dates")
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://localhost:57238"
})
public class DashboardResponseController {
	private final IDashboardService dashService;
	@GetMapping("/get")
	public DashResponse getDashboard() {
		return dashService.getDashboardResponse();
	}
	@GetMapping("/upcomming")
	public DashBoardResponse getUpCommingEvent() {
		return dashService.getUpcommingEvent();
	}
	@GetMapping("/mother")
	public DashBoardResponse getMotherDashboard() {
		return dashService.getMotherDashboard();
	}
	@GetMapping("/father")
	public DashBoardResponse getFatherDashboard() {
		return dashService.getFatherDashboard();
	}
	@GetMapping("/summary")
	public SummaryDto getSummary() {
	    return dashService.getDashboardSummary();
	}
	@GetMapping("/today")
	public DashResponse getToday() {
		return dashService.getTodayEvents();
	}
}

