package com.example.demo.ticketManagement.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class TicketPool {

    private int currentTicketCapacity;
    private int maxTicketCapacity;
    private Lock tryLock = new ReentrantLock();
    private List<String> logs = new ArrayList<>();


    public TicketPool(){
        this.maxTicketCapacity = 100;
        this.currentTicketCapacity =0;
        this.tryLock = new ReentrantLock();
    }

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.currentTicketCapacity = 0;
    }
    public boolean addTickets(int vendorID, int ticketsPerRelease) {
        tryLock.lock();
        try {
            if (currentTicketCapacity + ticketsPerRelease <= maxTicketCapacity) {
                currentTicketCapacity += ticketsPerRelease;
                log("Vendor " + vendorID + " released " + ticketsPerRelease + " tickets. Total tickets available: " + currentTicketCapacity);
                return true;
            }else {
                log("Ticket pool is full.");
                return false;
            }
        } finally {
            tryLock.unlock();
        }
    }

    public boolean removeTicket() {
        tryLock.lock();
        try {
            if (currentTicketCapacity > 0) {
                currentTicketCapacity--;
                log("A ticket was retrieved. Remaining tickets - "+currentTicketCapacity);
                return true;
            }else {
                log("No tickets are currently available in the pool.");
            return false;
            }
        } finally {
            tryLock.unlock();
        }
    }

    public int getCurrentTicketCapacity() {
        tryLock.lock();
        try {
            return currentTicketCapacity;
        } finally {
            tryLock.unlock();
        }
    }

    public List<String> getLogs() {
        return logs;  // Return a copy of the logs list
    }
    private void log(String message) {
        String timeStamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String logMessage = "[" + timeStamp + "] " + message;
        logs.add(logMessage);  // Store the log in the list
        System.out.println(logMessage);  // Optionally still print to console

    }
}
