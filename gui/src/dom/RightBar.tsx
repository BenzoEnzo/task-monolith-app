import React, { useState } from 'react';
import Account from "../page/Account";
import Report from "../page/Report";
import CreateTask from "../page/CreateTask";

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
            case 'assigned':
                return <div>Tutaj będą zadania przypisane do użytkownika</div>;
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
                                <li>Skrzynka</li>
                                <li>Wyślij</li>
                            </ul>
                        )}
                    </li>
                    <li onClick={toggleReports}>
                        Zgłoszenia
                        {reportsOpen && (
                            <ul className="submenu">
                                <li onClick={() => handleTabClick('reports')}>Przeglądaj wszystkie</li>
                                <li>Przeglądaj własne</li>
                                <li onClick={() => handleTabClick('create-task')}>Stwórz nowe</li>
                            </ul>
                        )}
                    </li>
                    <li onClick={() => handleTabClick('assigned')}>Przypisane</li>
                </ul>
            </div>
            <div className="main-content">
                {renderContent()}
            </div>
        </>
    );
};

export default RightBar;