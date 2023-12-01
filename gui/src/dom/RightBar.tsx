import React, {useState} from 'react';
import Report from "../page/Report";
import Account from "../page/Account";

const RightBar: React.FC = () => {
    const [activeTab, setActiveTab] = useState('account');

    const renderContent = () => {
        switch (activeTab) {
            case 'account':
                return <Account />;
            case 'notifications':
                return <div>Tutaj będą powiadomienia użytkownika</div>;
            case 'reports':
                return <Report />;
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
                    <li onClick={() => setActiveTab('account')}>Konto</li>
                    <li onClick={() => setActiveTab('notifications')}>Powiadomienia</li>
                    <li onClick={() => setActiveTab('reports')}>Zgłoszenia</li>
                    <li onClick={() => setActiveTab('assigned')}>Przypisane</li>
                </ul>
            </div>
            <div className="main-content">
                {renderContent()}
            </div>
        </>
    );
};

export default RightBar;