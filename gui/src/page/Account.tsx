import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Account = () => {
    const [account, setAccountInfo] = useState(null);
    const [error, setError] = useState("");
    const [editMode, setEditMode] = useState(false);
    const [editedName, setEditedName] = useState("");
    const userId = sessionStorage.getItem("id");
    useEffect(() => {
        const fetchAccountInfo = async () => {
            try {
                const accountDto = { id: userId };
                const response = await axios.post('/api/unauthorized/my-account', accountDto);
                setAccountInfo(response.data);
            } catch (error) {
                setError('Error fetching account information');
                console.error('Error fetching account information', error);
            }
        };

        if (userId) {
            fetchAccountInfo();
        }
    }, [userId]);

    const handlePatchName = async () => {
        try {
            const response = await axios.patch('/api/unauthorized/edit-data', { user_id: userId, name: editedName });
                // @ts-ignore
            setAccountInfo({...account, userDto: {...account.userDto, name: editedName}});
            setEditMode(false);
        } catch (error) {
            setError('Error updating name');
            console.error('Error updating name', error);
        }
    };

    const renderEditView = () => (
        <>
            <input
                type="text"
                value={editedName}
                onChange={(e) => setEditedName(e.target.value)}
            />
            <button onClick={handlePatchName}>Zapisz zmiany</button>
        </>
    );

    const renderDisplayView = () => {
    if (!account) return null;
    return <p>Imię: {account["userDto"]["name"]}</p>
    };

    if (error) {
        return <div>{error}</div>;
    }

    if (!account) {
        return <div>Loading...</div>;
    }

    return (
        <div className='account-info-card'>
            <h2>Informacje o Koncie</h2>
            <p>Email: {account["accountDto"]["mail"]}</p>
            <p>Saldo: {account["accountDto"]["money"]}</p>
            <p>Rola: {account["accountDto"]["role"]}</p>
            {editMode ? renderEditView() : renderDisplayView()}
            <button
                className="details-button"
                onClick={() => {
                    setEditMode(true);
                    setEditedName(account["userDto"]["name"]);
                }}
            >
                Przejście w tryb edycji
            </button>
        </div>

    );
};

export default Account;