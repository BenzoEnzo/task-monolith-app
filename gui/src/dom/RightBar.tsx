import React, { useState } from 'react';
import Account from "../page/Account";
import Report from "../page/Report";
import CreateTask from "../page/CreateTask";
import PersonalTasks from "../page/PersonalTasks";
import CreateNotification from "../page/CreateNotification";
import PersonalNotification from "../page/PersonalNotification";

const RightBar: React.FC = () => {
    const [activeTab, setActiveTab] = useState('account');
    const [notificationsOpen, setNotificationsOpen] = useState(false);
    const [reportsOpen, setReportsOpen] = useState(false);

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

    const toggleReports = () => {
        setReportsOpen(!reportsOpen);
    };

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
                return <div>Tutaj będzie ranking uzytkownikow, plusy za dobrze wykonane zadanie</div>;
            default:
                return null;
        }
    };

    return (
        <>
            <div className="right-bar">
                <ul>
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
                </ul>
            </div>
            <div className="main-content">
                {renderContent()}
            </div>
        </>
    );
};

export default RightBar;