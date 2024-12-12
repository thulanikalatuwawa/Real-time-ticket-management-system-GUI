# Ticket Management System

## Overview
The Ticket Management System is a multi-threaded application that simulates a ticket pool managed by vendors and customers. The backend is built using Spring Boot and implements producer-consumer logic, while the frontend is developed with React.js to provide a user-friendly interface for system configuration and monitoring.

## Features
### Backend
1. **Configuration Management**:
    - Configure ticket system parameters such as ticket release rate, customer retrieval rate, and maximum ticket capacity.
    - Validate configuration inputs to ensure data integrity.
2. **Vendor Management**:
    - Start and stop vendor threads that release tickets into the pool.
3. **Customer Management**:
    - Start and stop customer threads that retrieve tickets from the pool.
4. **Ticket Pool**:
    - Thread-safe pool for managing ticket availability.
5. **Logging**:
    - Logs all ticket-related activities, vendor actions, and customer behavior.

### Frontend
1. **Configuration Panel**:
    - Update and display ticket management settings.
2. **Control Panel**:
    - Start and stop vendor and customer threads.
3. **Ticket Status**:
    - View the current number of available tickets in the pool.
4. **Logs**:
    - Display real-time logs from the backend.

## Prerequisites
- **Backend**:
    - Java 17 or higher
    - Maven
    - Spring Boot 3.x
- **Frontend**:
    - Node.js 18 or higher
    - npm or yarn

## Installation
### Clone the Repository
```bash
git clone https://github.com/thulani-kalatuwawa/real-time-ticket-management-system.git
cd real-time-ticket-management-system
```

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   The backend will run on `http://localhost:8080`.

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the application:
   ```bash
   npm start
   ```
   The frontend will run on `http://localhost:3000`.

## API Endpoints
### Configuration
- **Get Configuration**: `GET /api/configuration`
- **Update Configuration**: `POST /api/configuration`
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
- **Start Vendors**: `POST /api/tickets/start-vendor`
- **Stop Vendors**: `POST /api/tickets/stop-vendor`

### Customer Management
- **Start Customers**: `POST /api/tickets/start-customer`
- **Stop Customers**: `POST /api/tickets/stop-customer`

### Logs
- **Get Logs**: `GET /api/tickets/logs`

## Project Structure
```
real-time-ticket-management-system/
├── backend/       # Spring Boot backend code
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── frontend/      # React.js frontend code
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── README.md
└── README.md      # Root project-level README
```

## Troubleshooting
1. Ensure both frontend and backend are running on their respective ports (`3000` for frontend, `8080` for backend).
2. Cross-origin requests are allowed only from `http://localhost:3000` to `http://localhost:8080`.
3. If `node_modules` is missing, run `npm install` inside the `frontend` directory.
4. Check logs in the backend console for debugging multithreaded operations.

## Future Enhancements
1. Real-time updates using WebSockets.
2. Authentication and role-based access control.
3. Advanced monitoring dashboards.

---
This project demonstrates the integration of multithreaded backend logic with a modern frontend interface. Contributions and feedback are welcome!

