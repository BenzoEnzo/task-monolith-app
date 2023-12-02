import React, { useState } from 'react';
interface NotificationDto {
    title: string;
    description: string;
    task_id: number;
}
const CreateNotification: React.FC = () => {
    const [notification, setNotification] = useState<NotificationDto>({
        title: '',
        description: '',
        task_id: 0,
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setNotification(prevNotification => ({
            ...prevNotification,
            [name]: value
        }));
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await fetch('/api/unauthorized/create-notification', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(notification)
            });

            if (response.ok) {
                console.log('Notification created successfully');
            } else {
                console.error('Failed to create notification');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (

        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="title">Title:</label>
                <input
                    type="text"
                    id="title"
                    name="title"
                    value={notification.title}
                    onChange={handleChange}
                />
            </div>
            <div>
                <label htmlFor="description">Description:</label>
                <input
                    type="text"
                    id="description"
                    name="description"
                    value={notification.description}
                    onChange={handleChange}
                />
            </div>
            <div>
                <label htmlFor="task_id">Task ID:</label>
                <input
                    type="number"
                    id="task_id"
                    name="task_id"
                    value={notification.task_id}
                    onChange={handleChange}
                />
            </div>
            <button type="submit">Create Notification</button>
        </form>

    );
};

export default CreateNotification;