import React, { useState, useEffect } from 'react';
import axios from 'axios';
interface TaskDto {
    title: string;
    description: string;
    task_id: number;
}
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
        axios.post('/api/unauthorized/join-to-task', {id: taskId,
        assignee_id: sessionStorage.getItem("id")})
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.error('There was an error fetching the tasks', error);
            });

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
                        <p>  {task["creator_id"] == sessionStorage.getItem("id") && (
                           <>
                               Jesteś zleceniodawcą tego zadania
                           </>
                        )}</p>
                        <div className="button-container">
                            {task["creator_id"] != sessionStorage.getItem("id") && (
                                <>
                                    {task["status"] != "WAITING_FOR_ACCEPT" && (
                                        <>
                                    <button
                                        className="accept-button"
                                        onClick={() => handleAcceptReport(task["id"])}
                                    >
                                        Zaakceptuj zgłoszenie
                                    </button>
                                        </>
                                    )}
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