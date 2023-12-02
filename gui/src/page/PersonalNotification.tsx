import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PersonalNotification.css'

interface Task {
    id: number;
    name: string; // Zakładam, że zadanie zawiera pole 'name'
}

interface NotificationDto {
    title: string;
    description: string;
    taskId: number; // Dodane pole do identyfikacji, do którego zadania należy powiadomienie
}

interface TaskWithNotifications {
    task: Task;
    notifications: NotificationDto[];
}

const PersonalNotification: React.FC = () => {
    const [tasksWithNotifications, setTasksWithNotifications] = useState<TaskWithNotifications[]>([]);
    const userId = sessionStorage.getItem("id"); // Pobierz ID użytkownika z sesji

    useEffect(() => {
        axios.post('/api/unauthorized/query-tasks', { id: userId })
            .then(response => {
                const fetchedTasks = response.data as Task[];
                return Promise.all(fetchedTasks.map(task =>
                    axios.post('/api/unauthorized/query-notification', { task_id: task.id })
                        .then(notificationResponse => ({
                            task: task,
                            notifications: notificationResponse.data as NotificationDto[]
                        }))
                ));
            })
            .then(tasksWithNotifications => {
                setTasksWithNotifications(tasksWithNotifications);
            })
            .catch(error => {
                console.error('There was an error fetching the tasks or notifications', error);
            });
    }, [userId]);

    return (
        <div>
            <h2>Zadania i powiadomienia użytkownika</h2>
            <table className="task-notification-table">
                <thead>
                <tr>
                    <th>Zadanie</th>
                    <th>Powiadomienia</th>
                </tr>
                </thead>
                <tbody>
                {tasksWithNotifications.map((taskWithNotification, index) => (
                    <tr key={index}>
                        <td>{taskWithNotification.task.name}</td>
                        <td>
                            <ul>
                                {taskWithNotification.notifications.map((notification, nindex) => (
                                    <li key={nindex}>
                                        {notification.title}: {notification.description}
                                    </li>
                                ))}
                            </ul>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default PersonalNotification;