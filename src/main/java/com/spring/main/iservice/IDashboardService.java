package com.spring.main.iservice;

import java.util.List;
import java.util.Map;

import com.spring.main.dto.DashBoardResponse;
import com.spring.main.dto.DashResponse;
import com.spring.main.dto.SummaryDto;

public interface IDashboardService {
	DashResponse getDashboardResponse();
	DashBoardResponse getUpcommingEvent();
	DashBoardResponse getMotherDashboard();

	DashBoardResponse getFatherDashboard();
	SummaryDto getDashboardSummary();
	DashResponse getTodayEvents();
}

