import React, { useState, useEffect } from 'react';

function TicketStatus() {
    const [status, setStatus] = useState("Available tickets: ");

    useEffect(() => {
        const fetchStatus = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/tickets/status');  //fetch ticket status from backend
                const data = await response.text();
                setStatus(data);
            } catch (error) {
                console.error("Error fetching ticket status:", error);
            }
        };

        fetchStatus();
    }, []);  

    return (
        <div className='ticket-status'>
            <h3>Ticket Pool Status</h3>
            <p>{status}</p>
        </div>
    );
}

export default TicketStatus;
