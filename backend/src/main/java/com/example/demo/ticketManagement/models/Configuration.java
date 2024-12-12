package com.example.demo.ticketManagement.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Configuration {
    @NotNull
    @Min(1) @Max(500)
    private int totalTickets;

    @NotNull
    @Min(1) @Max(500)
    private int ticketReleaseRate;

    @NotNull
    @Min(1) @Max(2000)
    private int customerRetrievalRate;

    @NotNull
    @Min(1) @Max(500)
    private int maxTicketCapacity;

    public Configuration(){

    }

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity){
        this.totalTickets = totalTickets;
        this.customerRetrievalRate=customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
    public boolean validationInput(){
        return totalTickets > 0 && totalTickets <= 500 &&
                ticketReleaseRate > 0 && ticketReleaseRate <= 500 &&
                customerRetrievalRate > 0 && customerRetrievalRate <= 2000 &&
                maxTicketCapacity > 0 && maxTicketCapacity <= 500 &&
                maxTicketCapacity >= totalTickets;
    }
    public static void jsonFileWriter(Configuration config, String fileName){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("maxTicketCapacity", config.getMaxTicketCapacity());
        jsonObject.put("totalTickets", config.getTotalTickets());
        jsonObject.put("ticketReleaseRate", config.getTicketReleaseRate());
        jsonObject.put("customerRetrievalRate", config.getCustomerRetrievalRate());

        fileName = "config_" + System.currentTimeMillis() + ".json";
        try(FileWriter file = new FileWriter(fileName)){
            file.write(jsonObject.toJSONString());
            System.out.println("Configuration saved to "+ fileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Configuration loadJsonFile(String fileName) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;
            int totalTickets = ((Long) jsonObject.get("totalTickets")).intValue();
            int ticketReleaseRate = ((Long) jsonObject.get("ticketReleaseRate")).intValue();
            int customerRetrievalRate = ((Long) jsonObject.get("customerRetrievalRate")).intValue();
            int maxTicketCapacity = ((Long) jsonObject.get("maxTicketCapacity")).intValue();
            return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<String> listSavedFiles() {
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
        List<String> filenames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                filenames.add(file.getName());
            }
        }
        return filenames;
    }
}
