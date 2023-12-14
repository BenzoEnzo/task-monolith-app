import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PersonalNotification.css';
import {useLocation} from "react-router-dom";


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
interface UserProfile {
    name: string;
    Score: number;
    createdTasks: any;
}


interface TaskWithNotifications {
    task: Task;
    notifications: NotificationDto[];
}

const PersonalNotification: React.FC = () => {
    const [tasksWithNotifications, setTasksWithNotifications] = useState<TaskWithNotifications[]>([]);
    const userId = sessionStorage.getItem("id");
    const [openTaskId, setOpenTaskId] = useState<number | null>(null);
    const [openDetail, setOpenDetail] = useState(false);
    const [userProfile, setUserProfile] = useState<UserProfile | null>(null);

    useEffect(() => {
        axios.post('/api/unauthorized/query-tasks', { creator_id: userId })
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

    const handleCloseUserDetail = () => {
        setOpenDetail(false);
    }
    const readUserProfile = (userId: any) => {
        axios.get(`/api/unauthorized/read-user/${userId}`)
            .then(response => {
                setUserProfile(response.data);
                setOpenTaskId(null);
                setOpenDetail(true);
                console.log(userProfile);
            })
            .catch(error => {
                console.error('There was an error fetching the user profile', error);
            });
    };

    // @ts-ignore
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
                            {  openDetail && userProfile && (
                                <div className="overlay">
                                    <div className="offer-form">
                                        <div className='account-container'>
                                            <div className='account-info-card'>
                                                <h2>Informacje o Użytkowniku: {userProfile.name}</h2>
                                                <p>Punkty: {userProfile.Score}</p>
                                                <p>Stworzone zlecenia:</p>
                                                <ul>
                                                    {userProfile.createdTasks.map((task:any, index:any) => (
                                                        <li key={index}>
                                                            <p>Nazwa zadania: {task.name}</p>
                                                            <p>Opis: {task.description}</p>
                                                            <p>Wynagrodzenie: {task.pay}</p>
                                                            <p>Status: {task.status}</p>
                                                        </li>
                                                    ))}
                                                </ul>
                                            </div>
                                        </div>
                                        <button onClick={handleCloseUserDetail}>
                                            Powrót
                                        </button>
                                    </div>
                                </div>
                            )}
                            {openTaskId === taskWithNotification.task.id && (
                                <div className="overlay">
                                    <div className="offer-form">
                                        <h2>Wiadomości:</h2>
                                        <div className="notification-list-container">
                                        <ul>
                                            {taskWithNotification.notifications.map((notification, nindex) => (
                                                <li key={nindex}>
                                                <span className="notification-section">
                                                    <span className="section-title">Autor:</span>
                                                    <span className="author">{notification.author_name}</span>
                                                    <button className="profile-button" onClick={() => readUserProfile(notification.author_id)}>Zobacz Profil</button>
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
                                        </div>
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