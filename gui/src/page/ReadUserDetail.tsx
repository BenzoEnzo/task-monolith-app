import './PersonalNotification.css';
import {useLocation, useNavigate} from 'react-router-dom';
import RightBar from "../dom/RightBar";
import React, { useState, useEffect } from "react";

const ReadUserDetail = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { userProfile, isOpen } = location.state || {};
    const [detailOpen, setDetailOpen] = useState(isOpen);

    useEffect(() => {
        setDetailOpen(isOpen);
    }, [isOpen]);

    if (!userProfile) {
        return <div>Loading...</div>;
    }

    const toggleDetails = () => {
        setDetailOpen(!detailOpen);
    };
    return (
    <div className="main">
        <RightBar/>

                            <div className='account-container'>
                                <div className='account-info-card'>
                                    {detailOpen && <>
                                    <h2>Informacje o Użytkowniku: {userProfile.name}</h2>

                                    <p>Punkty: {userProfile.score}</p>
                                    <p>Stworzone zlecenia:</p>
                                    <ul>
                                        {userProfile.createdTasks?.map((task:any, index:any) => (
                                            <li key={index}>
                                                <p>Nazwa zadania: {task.name}</p>
                                                <p>Opis: {task.description}</p>
                                                <p>Wynagrodzenie: {task.pay}</p>
                                                <p>Status: {task.status}</p>
                                            </li>
                                        ))}
                                    </ul>
                                    </>}
                                </div>
                            </div>
                            <button onClick={toggleDetails}>
                                {detailOpen ? "Hide Details" : "Show Details"}
                            </button>

    </div>
    );
};

export default ReadUserDetail;