import React, { useState, useEffect } from 'react';

function LogDisplay() {
    const [logs, setLogs] = useState([]); 

    useEffect(() => {
        const fetchLogs = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/tickets/logs'); //gettings log updates from backend
                const data = await response.json();
                setLogs(data);
            } catch (error) {
                console.error("Error fetching logs:", error);
            }
        };

        fetchLogs();
    }, []);  

    return (
        <div>
            <h3 className='log-display'>Log Display</h3>
            <div style={{ whiteSpace: 'pre-wrap' }}>
                {logs.map((log, index) => (
                    <div key={index}>{log}</div>
                ))}
            </div>
        </div>
    );
}

export default LogDisplay;
