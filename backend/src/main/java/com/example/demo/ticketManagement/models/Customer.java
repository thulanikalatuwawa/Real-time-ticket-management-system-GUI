package com.example.demo.ticketManagement.models;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Customer implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(Customer.class);
    private int customerId;
    private int retrievalInterval;
    private TicketPool ticketPool;
    private volatile boolean running = true;

    @Autowired
    public Customer(@Value("${vendor.id}") int customerId, @Value("${customer.retrievalInterval}") int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }
    public void stopCustomer(){
        running = false;
    }
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                boolean ticketRetrieved = ticketPool.removeTicket();
                if (ticketRetrieved) {
                    System.out.println("Customer "+customerId+" retrieved a ticket");
                } else {
                    System.out.println("Customer "+customerId + " found no tickets.");
                }
                Thread.sleep(retrievalInterval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer " + customerId + " interrupted.");
        }
    }

}
