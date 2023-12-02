import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Report = () => {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {

        axios.get('/api/unauthorized/query-all-tasks')
            .then(response => {

                setTasks(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the tasks', error);
            });
    }, []);

    const handleAcceptReport = (taskId: Number) => {
        console.log(`Accepted report with ID: ${taskId}`);
    };

    const handleReadDetails = (taskId: Number) => {
        console.log(`Read details for report with ID: ${taskId}`);
    };


    return (
        <div className="report-container">
            <h2>Lista ogłoszeń</h2>
            <ul className="task-list">
                {tasks.map((task, index) => (
                    <li key={index} className="task-item">
                        <h3>{task["name"]}</h3>
                        <p>Pay: {task["pay"]}</p>
                        <p>Status: {task["status"]}</p>
                        <p>Description: {task["description"]}</p>
                        <div className="button-container">
                            {task["creator_id"] != sessionStorage.getItem("id") && (
                                <>
                                    <button
                                        className="accept-button"
                                        onClick={() => handleAcceptReport(task["id"])}
                                    >
                                        Zaakceptuj zgłoszenie
                                    </button>
                                    <button
                                        className="details-button"
                                        onClick={() => handleReadDetails(task["id"])}
                                    >
                                        Czytaj szczegóły
                                    </button>
                                </>
                            )}
                        </div>
                        {/*<p>Creator ID: {task["creator_id"]}</p>*/}
                        {/*<p>Assignee ID: {task["assignee_id"]}</p>*/}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Report;