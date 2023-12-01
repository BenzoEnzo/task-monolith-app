import React, { useState, useEffect } from 'react';
import axios from 'axios';

const PersonalTasks = () => {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {

        axios.post('/api/unauthorized/query-tasks', {id: sessionStorage.getItem("id")})
            .then(response => {
                setTasks(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the tasks', error);
            });
    }, []);



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
                        {/*<p>Assignee ID: {task["assignee_id"]}</p>*/}
                        {/*<p>Creator ID: {task["creator_id"]}</p>*/}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default PersonalTasks;