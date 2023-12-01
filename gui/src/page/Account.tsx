import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Account = () => {
    const [account, setAccountInfo] = useState(null);
    const [error, setError] = useState("");
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

    if (error) {
        return <div>{error}</div>;
    }

    if (!account) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>Informacje o Koncie</h2>
            <p>Email: {account["mail"]}</p>
            <p>Saldo: {account["money"]}</p>
            <p>Rola: {account["role"]}</p>
        </div>
    );
};

export default Account;