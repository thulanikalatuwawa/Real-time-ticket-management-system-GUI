import React from 'react';

const ControlPanel = ({ handleStartVendor, 
  handleStopVendor, 
  handleStartCustomer, 
  handleStopCustomer, 
  isVendorRunning, 
  isCustomerRunning 
}) => {
  
  return (
    <div className="control-panel">
      <h3>Control Panel</h3>
      <div> 
        <button onClick={() => handleStartVendor(100, 500)} disabled={isVendorRunning}>
          Start Vendor
        </button>
        <button onClick={handleStopVendor} disabled={!isVendorRunning}>
          Stop Vendor
        </button>
      </div>
      <div>
        <button onClick={() => handleStartCustomer(200)} disabled={isCustomerRunning}>
          Start Customer
        </button>
        <button onClick={handleStopCustomer} disabled={!isCustomerRunning}>
          Stop Customer
        </button>
      </div>
    </div>
  );
};

export default ControlPanel;
