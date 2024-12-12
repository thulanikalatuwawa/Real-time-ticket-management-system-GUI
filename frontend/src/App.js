import React, { useState } from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import TicketStatus from './components/TicketStatus';
import ControlPanel from './components/ControlPanel';
import LogDisplay from './components/LogDisplay';
import './App.css';

function App() {
  const [ticketStatus, setTicketStatus] = useState(0);  //current number of tickets
  const [isVendorRunning, setIsVendorRunning] = useState(false);
  const [isCustomerRunning, setIsCustomerRunning] = useState(false);  //track threads 

  const handleStartVendor = async (ticketReleaseRate, releaseInterval) => {  //start thread
    try {
      const response = await fetch(`http://localhost:8080/api/tickets/start-vendor?ticketsPerRelease=${ticketReleaseRate}&releaseInterval=${releaseInterval}`, {
        method: 'POST',
      });
      if (response.ok) {
        setIsVendorRunning(true);
        fetchTicketStatus();
      }
    } catch (error) {
      console.error('Error starting vendor:', error);
    }
  };

  const handleStopVendor = async () => { 
    try {
      const response = await fetch('http://localhost:8080/api/tickets/stop-vendor', {
        method: 'POST',
      });
      if (response.ok) {
        setIsVendorRunning(false);
        fetchTicketStatus();
      }
    } catch (error) {
      console.error('Error stopping vendor:', error);
    }
  };

  const handleStartCustomer = async (retrievalInterval) => {
    try {
      const response = await fetch(`http://localhost:8080/api/tickets/start-customer?retrievalInterval=${retrievalInterval}`, {
        method: 'POST',
      });
      if (response.ok) {
        setIsCustomerRunning(true);
      }
    } catch (error) {
      console.error('Error starting customer:', error);
    }
  };

  const handleStopCustomer = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/tickets/stop-customer', {
        method: 'POST',
      });
      if (response.ok) {
        setIsCustomerRunning(false);
      }
    } catch (error) {
      console.error('Error stopping customer:', error);
    }
  };

  const fetchTicketStatus = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/tickets/status');
      const status = await response.json();
      setTicketStatus(status.count); 
    } catch (error) {
      console.error('Error fetching ticket status:', error);
    }
  };

  return (
    <div className="App">
      <h1>Ticket Management System</h1>
      <TicketStatus status={ticketStatus} />
      <ConfigurationForm handleStartVendor={handleStartVendor} handleStartCustomer={handleStartCustomer} />
      <ControlPanel
        isVendorRunning={isVendorRunning}
        isCustomerRunning={isCustomerRunning}
        handleStartVendor={handleStartVendor}
        handleStopVendor={handleStopVendor}
        handleStartCustomer={handleStartCustomer}
        handleStopCustomer={handleStopCustomer}
      />
      <LogDisplay />
    </div>
  );
}

export default App;
