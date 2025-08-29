package application.services;

import apis.resources.DashboardResponse;

public interface DashboardService {
    DashboardResponse getDashboard(String userId);
}
