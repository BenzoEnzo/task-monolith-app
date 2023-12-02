import React, { useState, useEffect } from 'react';
import axios from 'axios';

interface Task {
    id: number;

}

interface NotificationDto {
    title: string;
    description: string;
}

const PersonalNotification: React.FC = () => {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [notifications, setNotifications] = useState<NotificationDto[]>([]);
    const userId = sessionStorage.getItem("id"); // Pobierz ID użytkownika z sesji

    useEffect(() => {
        axios.post('/api/unauthorized/query-tasks', { id: userId })
            .then(response => {
                setTasks(response.data);
                return response.data; // Zwróć zadania do następnego .then
            })
            .then(tasks => {
                // Dla każdego zadania pobierz powiadomienia
                tasks.forEach(task => {
                    axios.post('/api/unauthorized/query-notification', { task_id: task.id })
                        .then(response => {
                            setNotifications(prevNotifications => [...prevNotifications, ...response.data]);
                        });
                });
            })
            .catch(error => {
                console.error('There was an error fetching the tasks or notifications', error);
            });
    }, [userId]);

    return (
        <div>
            <h2>Zadania użytkownika</h2>
            {/* Wyświetl zadania */}
            <ul>
                {tasks.map(task => (
                    <li key={task.id}>
                        {/* Wyświetl szczegóły zadania */}
                    </li>
                ))}
            </ul>

            <h2>Powiadomienia dla zadań</h2>
            {/* Wyświetl powiadomienia */}
            <ul>
                {notifications.map((notification, index) => (
                    <li key={index}>
                        <h3>{notification.title}</h3>
                        <p>{notification.description}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default PersonalNotification;