import React, { useState } from "react";

const ConfigurationForm = ({ handleStartVendor, handleStartCustomer }) => {
  const [config, setConfig] = useState({ //store user input values
    maxTicketCapacity: 0,
    totalTickets: 0,
    ticketReleaseRate: 0,
    releaseInterval: 0,
    customerRetrievalRate: 0,
  });

  const [errors, setErrors] = useState({}); // error messages for inputs

  const handleChange = (e) => {  //update user inputs
    const { name, value } = e.target;
    setConfig((prevConfig) => ({
      ...prevConfig,
      [name]: parseInt(value, 10),
    }));
  };

  const validateInputs = () => {  // check if the inputs are in range
    const newErrors = {};

    if (config.maxTicketCapacity < 1 || config.maxTicketCapacity > 500) {
      newErrors.maxTicketCapacity =
        "Max Ticket Capacity must be between 1 and 500.";
    }
    if (config.totalTickets < 1 || config.totalTickets > 500) {
      newErrors.totalTickets = "Total Tickets must be between 1 and 500.";
    }
    if (config.ticketReleaseRate < 1 || config.ticketReleaseRate > 500) {
      newErrors.ticketReleaseRate =
        "Ticket Release Rate must be between 1 and 500 per minute.";
    }
    if (config.customerRetrievalRate < 1 || config.customerRetrievalRate > 2000) {
      newErrors.customerRetrievalRate =
        "Customer Retrieval Rate must be between 1 and 2000 milliseconds.";
    }

    setErrors(newErrors);

    return Object.keys(newErrors).length === 0; 
  };

  const handleSubmit = (e) => {       // validated configuration inputs submit
    e.preventDefault();

    if (validateInputs()) {
      const { ticketReleaseRate, releaseInterval, customerRetrievalRate } = config;
      handleStartVendor(ticketReleaseRate, releaseInterval);
      handleStartCustomer(customerRetrievalRate); 
      alert("Configuration saved successfully!"); 
    } else {
      alert("Please correct the errors before saving the configuration.");
    }
  };

  return (
    <div className="config-form">
      <h3>Configuration Settings</h3>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Max Ticket Capacity (1-500):</label>
          <input
            type="number"
            name="maxTicketCapacity"
            value={config.maxTicketCapacity}
            onChange={handleChange}
          />
          {errors.maxTicketCapacity && (
            <p className="error">{errors.maxTicketCapacity}</p>
          )}
        </div>
        <div>
          <label>Total Tickets (1-500):</label>
          <input
            type="number"
            name="totalTickets"
            value={config.totalTickets}
            onChange={handleChange}
          />
          {errors.totalTickets && (
            <p className="error">{errors.totalTickets}</p>
          )}
        </div>
        <div>
          <label>Ticket Release Rate (1-500 per minute):</label>
          <input
            type="number"
            name="ticketReleaseRate"
            value={config.ticketReleaseRate}
            onChange={handleChange}
          />
          {errors.ticketReleaseRate && (
            <p className="error">{errors.ticketReleaseRate}</p>
          )}
        </div>
        <div>
          <label>Customer Retrieval Rate (1-2000ms):</label>
          <input
            type="number"
            name="customerRetrievalRate"
            value={config.customerRetrievalRate}
            onChange={handleChange}
          />
          {errors.customerRetrievalRate && (
            <p className="error">{errors.customerRetrievalRate}</p>
          )}
        </div>
        <button type="submit">Save Configuration</button>
      </form>
    </div>
  );
};

export default ConfigurationForm;
