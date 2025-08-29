package apis.controllers;

import apis.resources.DashboardResponse;
import application.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bff")
public class DashboardController {


    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<DashboardResponse> getDashboard(@PathVariable String userId) {
        DashboardResponse response = dashboardService.getDashboard(userId);
        return ResponseEntity.ok(response);
    }
}
