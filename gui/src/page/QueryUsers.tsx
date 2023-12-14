import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './QueryUsers.css'

interface User {
    id: number;
    name: string;
    Score: number;
}
const QueryUsers = () => {
    const [users, setUsers] = useState<User[]>([]);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get<User[]>('/api/administrator/que-users',{
                    headers: {
                    Authorization: sessionStorage.getItem("authToken")
                }});
                const sortedUsers = response.data.sort((a: User, b: User) => b.Score - a.Score);
                setUsers(sortedUsers);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };

        fetchUsers();
    }, []);

    const getMedalColor = (index: number) => {
        switch (index) {
            case 0:
                return 'gold';
            case 1:
                return 'silver';
            case 2:
                return 'bronze';
            default:
                return 'none';
        }
    };

    return (
        <div className="user-list">
            <div className="user-header">
                <span className="user-name-header">Imię:</span>
                <span className="user-score-header">Score:</span>
            </div>
            {users.map((user, index) => (
                <div key={user.id} className={`user-item ${getMedalColor(index)}`}>
                    <span className="user-name">{user.name ? user.name : "UNKNOWN_PROFILE"}</span>
                    <span className="user-score">{user.Score ? user.Score : "0"}</span>
                </div>
            ))}
        </div>
    );
};

export default QueryUsers;