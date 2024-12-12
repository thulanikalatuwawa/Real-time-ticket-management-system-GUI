package com.example.demo.ticketManagement.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Vendor implements Runnable {
    private int vendorID;
    private int ticketsPerRelease;
    private int releaseInterval;
    private TicketPool ticketPool;

    private boolean running = true;

    @Autowired
    public Vendor(@Value("${vendor.id}") int vendorId, @Value("${vendor.ticketsPerRelease}") int ticketsPerRelease, @Value("${vendor.releaseInterval}") int releaseInterval, TicketPool ticketPool) {
        this.vendorID = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }
    // Stop the vendor thread
    public void stopVendor() {
        running = false;
    }
    @Override
    public void run() {
        try {
            while (running) {
                if (!ticketPool.addTickets(vendorID, ticketsPerRelease)) {
                    System.out.println("Vendor " + vendorID + " can't release more tickets.");
                }
                Thread.sleep(releaseInterval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor " + vendorID + " interrupted.");
        }
    }
}

