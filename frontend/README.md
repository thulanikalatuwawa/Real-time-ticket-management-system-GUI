# Ticket Management System - Frontend

## Overview
This React-based frontend interacts with the backend APIs to manage and visualize the ticket management system. It provides an interface for configuring the system, controlling vendors and customers, and viewing system logs.

## Features
1. **Configuration Panel**:
    - Update and save ticket management settings.
    - Display current configuration values.

2. **Control Panel**:
    - Start and stop vendor threads.
    - Start and stop customer threads.

3. **Ticket Status**:
    - Display the current number of available tickets in the pool.

4. **Logs**:
    - Real-time display of activity logs from the backend.

## Prerequisites
- Node.js 18 or higher
- npm or yarn

## Installation
1. Navigate to the frontend directory:
   ```bash
   cd ticket-management-system-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the application:
   ```bash
   npm start
   ```

   The application will run on `http://localhost:3000`.

## Components

### Configuration
- Allows users to update system settings such as ticket release rate and maximum capacity.
- Sends updates to the backend via API calls.

### Ticket Status
- Displays the current number of tickets in the pool.
- Updates dynamically based on API responses.

### Control Panel
- Buttons to start and stop vendors and customers.
- Sends control commands to the backend.

### Logs
- Fetches and displays logs from the backend.
- Provides real-time updates of ticket pool activity.

## API Integration
The frontend communicates with the backend using the following APIs:
- `GET /api/configuration`
- `POST /api/configuration`
- `GET /api/tickets/status`
- `POST /api/tickets/start-vendor`
- `POST /api/tickets/stop-vendor`
- `POST /api/tickets/start-customer`
- `POST /api/tickets/stop-customer`
- `GET /api/tickets/logs`

## Troubleshooting
1. Ensure the backend is running on `http://localhost:8080`.
2. Confirm cross-origin requests are permitted for `http://localhost:3000`.
3. Check the browser console for API call errors.

## Future Enhancements
- Implement real-time updates using WebSockets.
- Add authentication for secure API access.

