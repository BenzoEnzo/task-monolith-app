import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PersonalNotification.css';

interface Task {
    id: number;
    name: string;
}

interface NotificationDto {
    title: string;
    description: string;
    taskId: number;
    author_id: string;
    author_name: string;
}

interface TaskWithNotifications {
    task: Task;
    notifications: NotificationDto[];
}

const PersonalNotification: React.FC = () => {
    const [tasksWithNotifications, setTasksWithNotifications] = useState<TaskWithNotifications[]>([]);
    const userId = sessionStorage.getItem("id");
    const [openTaskId, setOpenTaskId] = useState<number | null>(null);

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

    const handleShowNotifications = (taskId: number) => {
        setOpenTaskId(taskId);
    };

    const handleCloseNotifications = () => {
        setOpenTaskId(null);
    };

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
                {tasksWithNotifications.map((taskWithNotification) => (
                    <tr key={taskWithNotification.task.id}>
                        <td>{taskWithNotification.task.name}</td>
                        <td>
                            <button onClick={() => handleShowNotifications(taskWithNotification.task.id)}>
                                Sprawdz wiadomosci
                            </button>
                            {openTaskId === taskWithNotification.task.id && (
                                <div className="overlay">
                                    <div className="offer-form">
                                        <h2>Wiadomości:</h2>
                                        <ul>
                                            {taskWithNotification.notifications.map((notification, nindex) => (
                                                <li key={nindex}>
                                                <span className="notification-section">
                                                    <span className="section-title">Autor:</span>
                                                    <span className="author">{notification.author_name}</span>
                                                    <button className="profile-button">Zobacz Profil</button>
                                                     <button className="reply-button">Odpowiedz</button>
                                                </span>
                                                                                            <span className="notification-section">
                                                    <span className="section-title">Tytuł:</span>
                                                    <span className="title">{notification.title}</span>
                                                </span>
                                                                                            <span className="notification-section">
                                                    <span className="section-title">Treść:</span>
                                                       <textarea className="description" disabled value={notification.description}></textarea>
                                                </span>
                                                </li>
                                            ))}
                                        </ul>
                                        <button onClick={handleCloseNotifications}>Zamknij</button>
                                    </div>
                                </div>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default PersonalNotification;