# Real-time-event-ticketing-system - backend

## Overview
This Spring Boot application implements a ticket management system using multithreaded producer-consumer logic. Vendors release tickets into a shared ticket pool every minute, while customers retrieve tickets from the pool.
The backend provides REST APIs for configuring the system, managing vendors and customers, and retrieving system logs.

## Features
1. **Configuration Management**:
    - Configure ticket management parameters such as total tickets, ticket release rate, customer retrieval rate, and maximum ticket capacity.
    - Validate configuration inputs to ensure they fall within acceptable ranges.
    - Save and load configurations as JSON files.

2. **Vendor Management**:
    - Start and stop vendor threads.
    - Vendors release a specified number of tickets into the pool at regular intervals.

3. **Customer Management**:
    - Start and stop customer threads.
    - Customers retrieve tickets from the pool at regular intervals.

4. **Ticket Pool**:
    - Thread-safe ticket pool for managing ticket availability.
    - Logs all ticket-related activities.

5. **Logging**:
    - Generate detailed logs for ticket pool activities, vendor actions, and customer behavior.
    - Expose logs via an API endpoint.

## Prerequisites
- Java 17 or higher
- Maven
- Spring Boot 3.x

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/thulani-kalatuwawa/real-time-ticket-management-system.git
   ```

2. Navigate to the project directory:
   ```bash
   cd ticket-management-system
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Configuration
- **Get Configuration**
    - Endpoint: `GET /api/configuration`
    - Description: Retrieve the current system configuration.

- **Update Configuration**
    - Endpoint: `POST /api/configuration`
    - Description: Update the ticket system configuration.
    - Payload:
      ```json
      {
        "totalTickets": 100,
        "ticketReleaseRate": 5,
        "customerRetrievalRate": 2,
        "maxTicketCapacity": 200
      }
      ```

### Vendor Management
- **Start Vendors**
    - Endpoint: `POST /api/tickets/start-vendor`
    - Description: Start vendor threads with the specified tickets per release.
    - Parameters: `ticketsPerRelease`

- **Stop Vendors**
    - Endpoint: `POST /api/tickets/stop-vendor`
    - Description: Stop all running vendor threads.

### Customer Management
- **Start Customers**
    - Endpoint: `POST /api/tickets/start-customer`
    - Description: Start customer threads with the specified retrieval interval.
    - Parameters: `retrievalInterval`

- **Stop Customers**
    - Endpoint: `POST /api/tickets/stop-customer`
    - Description: Stop all running customer threads.

### Logs
- **Get Logs**
    - Endpoint: `GET /api/tickets/logs`
    - Description: Retrieve the activity logs for the ticket pool.

## Code Structure
- **Models**
    - `Configuration`: Stores configuration details and validates inputs.
    - `TicketPool`: Manages tickets in a thread-safe manner.
    - `Vendor`: Implements vendor behavior for releasing tickets.
    - `Customer`: Implements customer behavior for retrieving tickets.

- **Controllers**
    - `ConfigurationController`: Manages configuration-related APIs.
    - `TicketController`: Manages ticket pool, vendors, and customers.

## Troubleshooting
1. Ensure the backend service is running on `http://localhost:8080`.
2. Cross-origin requests are allowed only from `http://localhost:3000`.


