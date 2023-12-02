import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface NotificationDto {
    title: string;
    description: string;
    // Możliwe inne pola
}

interface TaskDto {
    id: number;
    name: string;
    description: string;
    // Możliwe inne pola
    notifications: NotificationDto[];
}

const PersonalNotification: React.FC = () => {
    const [tasks, setTasks] = useState<TaskDto[]>([]);
    const userId = sessionStorage.getItem("id");

    useEffect(() => {
        axios.post('/api/unauthorized/query-tasks', { creator_id: userId })
            .then(response => {
                setTasks(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the tasks', error);
            });
    }, [userId]);

    return (
        <div>
            <h2>Zadania użytkownika</h2>
            <ul>
                {tasks.map(task => (
                    <li key={task.id}>
                        <h3>{task.name}</h3>
                        <p>{task.description}</p>
                        <ul>
                            {task.notifications.map((notification, index) => (
                                <li key={index}>
                                    <h4>{notification.title}</h4>
                                    <p>{notification.description}</p>
                                </li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default PersonalNotification;