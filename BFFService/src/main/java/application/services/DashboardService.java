package application.services;

import apis.dto.DashboardResponse;
import application.feignClients.AccountClient;
import application.feignClients.TransactionClient;
import application.feignClients.UserClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public interface DashboardService {
    DashboardResponse getDashboard(String userId);
}
