import React, {useEffect, useState} from 'react';
import Account from "../page/Account";
import Report from "../page/Report";
import CreateTask from "../page/CreateTask";
import PersonalTasks from "../page/PersonalTasks";
import CreateNotification from "../page/CreateNotification";
import PersonalNotification from "../page/PersonalNotification";
import QueryUsers from "../page/QueryUsers";
import axios from "axios";


const RightBar: React.FC = () => {
    const [activeTab, setActiveTab] = useState('account');
    const [notificationsOpen, setNotificationsOpen] = useState(false);
    const [reportsOpen, setReportsOpen] = useState(false);
    const fileName = sessionStorage.getItem("photoId");
    const usrName = sessionStorage.getItem("name");
    const [imageSrc, setImageSrc] = useState('');
    const handleTabClick = (tab: string) => {
        if (tab !== 'notifications' && tab !== 'reports') {
            setNotificationsOpen(false);
            setReportsOpen(false);
        }
        setActiveTab(tab);
    }

    const toggleNotifications = () => {
        setNotificationsOpen(!notificationsOpen);
    };

    const logout = async () => {
        try {
            sessionStorage.clear();
            await axios.get('/api/manage/logout');
            window.location.href = '/';
        } catch (error) {
            console.error("Błąd podczas wylogowywania:", error);
        }

    };

    const toggleReports = () => {
        setReportsOpen(!reportsOpen);
    };
    useEffect(() => {
        const loadImage = async () => {
            try {
                const dataIMG = fileName + ".jpeg";
                const response = await axios.get(`/api/unauthorized/profile-image/load/${dataIMG}`, {responseType: 'arraybuffer'});
                const base64Image = `data:image/jpeg;base64,${btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''))}`;
                setImageSrc(base64Image);
            } catch (error) {
                console.error("Błąd podczas ładowania zdjęcia:", error);
            }
        };

        if (fileName) {
            loadImage();
        }
    });


    const renderContent = () => {
        switch (activeTab) {
            case 'account':
                return <Account />;
            case 'notifications':
                return notificationsOpen ? null : <div>Tutaj będą powiadomienia użytkownika</div>;
            case 'reports':
                return reportsOpen ? null : <Report />;
            case 'create-task':
                return reportsOpen ? null : <CreateTask />;
            case 'personal-tasks':
                return reportsOpen ? null : <PersonalTasks />;
            case 'personal-notifications':
                return reportsOpen ? null : <PersonalNotification />;
            case 'create-notification':
                return reportsOpen ? null : <CreateNotification/>;
            case 'ranking':
                return <QueryUsers/>;
            default:
                return null;
        }
    };

    return (
        <>
            <div className="right-bar">

                <ul>
                    <h3>Zalogowano:</h3>
                    <div className="img">
                        {imageSrc && <img src={imageSrc} alt="Profile" />}
                    </div>
                    <li onClick={() => handleTabClick('account')}>Konto</li>
                    <li onClick={toggleNotifications}>
                        Powiadomienia
                        {notificationsOpen && (
                            <ul className="submenu">
                                <li onClick={() => handleTabClick('personal-notifications')}>Skrzynka</li>
                                <li onClick={() => handleTabClick('create-notification')}>Wyślij</li>
                            </ul>
                        )}
                    </li>
                    <li onClick={toggleReports}>
                        Zgłoszenia
                        {reportsOpen && (
                            <ul className="submenu">
                                <li onClick={() => handleTabClick('reports')}>Przeglądaj wszystkie</li>
                                <li onClick={() => handleTabClick('personal-tasks')}>Przeglądaj własne</li>
                                <li onClick={() => handleTabClick('create-task')}>Stwórz nowe</li>
                            </ul>
                        )}
                    </li>
                    <li onClick={() => handleTabClick('ranking')}>Ranking</li>
                    <li onClick={() => handleTabClick('ranking')}>Relacje</li>
                    <li onClick={() => logout()}>Wyloguj</li>
                </ul>
            </div>
            <div className="main-content">
                {renderContent()}
            </div>
        </>
    );
};

export default RightBar;