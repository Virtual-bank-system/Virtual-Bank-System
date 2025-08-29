//package apis.controllers;
//
//import application.models.Logging;
//import application.repos.LoggingRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/logs")
//public class LoggingController {
//
//    @Autowired
//    private LoggingRepo loggingRepo;
//
//    @GetMapping
//    public List<Logging> getAllLogs() {
//        return loggingRepo.findAll();
//    }
//
//    @GetMapping("/type/{type}")
//    public List<Logging> getLogsByType(@PathVariable String type) {
//        return loggingRepo.findAll().stream()
//                .filter(l -> l.getMessageType().equalsIgnoreCase(type))
//                .collect(Collectors.toList());
//    }
//}
//
