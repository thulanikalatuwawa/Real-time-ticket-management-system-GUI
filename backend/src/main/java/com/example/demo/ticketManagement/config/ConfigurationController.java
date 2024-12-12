package com.example.demo.ticketManagement.config;

import com.example.demo.ticketManagement.models.Configuration;
import com.example.demo.ticketManagement.models.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationController {

    @Autowired
    private Configuration configuration;

    @Autowired
    private TicketPool ticketPool;

    @GetMapping
    public Configuration getConfiguration() {
        return configuration;
    }

    @PostMapping
    public String updateConfiguration(@RequestBody Configuration newConfiguration) {
        if (newConfiguration.validationInput()) {
            configuration.setTotalTickets(newConfiguration.getTotalTickets());
            configuration.setTicketReleaseRate(newConfiguration.getTicketReleaseRate());
            configuration.setCustomerRetrievalRate(newConfiguration.getCustomerRetrievalRate());
            configuration.setMaxTicketCapacity(newConfiguration.getMaxTicketCapacity());
            return "Configuration updated successfully!";
        }
        return "Invalid configuration values!";
    }
}

