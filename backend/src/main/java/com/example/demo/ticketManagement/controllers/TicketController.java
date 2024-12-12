package com.example.demo.ticketManagement.controllers;

import com.example.demo.ticketManagement.models.Customer;
import com.example.demo.ticketManagement.models.TicketPool;
import com.example.demo.ticketManagement.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {

    @Autowired
    private TicketPool ticketPool;

    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();

    private static final int vendorCount = 3; // Number of vendors
    private static final int customerCount = 5; // Number of customers
    private static final int releaseIntervalVendor = 60000; // 60 seconds

    @GetMapping("/status")
    public String getTicketPoolStatus() {
        return "Tickets available: " + ticketPool.getCurrentTicketCapacity();
    }

    @PostMapping("/start-vendor")
    public String startVendor(@RequestParam int ticketsPerRelease) {
        if (!vendorThreads.isEmpty()){
            return "Vendor system is already running. Stop it first";
        }
        for (int i = 1; i <= vendorCount; i++) {
            Vendor vendor = new Vendor(i, ticketsPerRelease, releaseIntervalVendor, ticketPool);
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread); // Track vendor threads
            vendorThread.start();
        }
        return vendorCount + " Vendors started.";
    }
    @PostMapping("/stop-vendor")
    public String stopVendor() {
        vendorThreads.forEach(Thread::interrupt); // Interrupt all vendor threads
        vendorThreads.clear(); // Clear the thread list
        return "Vendors stopped.";
    }


    @PostMapping("/start-customer")
    public String startCustomer(@RequestParam int retrievalInterval) {
        if (!customerThreads.isEmpty()){
            System.out.println("Customer system is already running. Stop it first");
        }
        for (int i = 1; i <= customerCount; i++) {
            Customer customer = new Customer(i, retrievalInterval, ticketPool);
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread); // Track customer threads
            customerThread.start();
        }
        return customerCount + "Customers started.";
    }
    @PostMapping("/stop-customer")
    public String stopCustomer() {
        customerThreads.forEach(Thread::interrupt); // Interrupt all customer threads
        customerThreads.clear();
        return "Customers stopped.";
    }
    @GetMapping("/logs")
    public List<String> getLogs() {
        return ticketPool.getLogs();  // Fetch logs from the TicketPool
    }

}


