import React, { useState } from 'react';
interface NotificationDto {
    title: string;
    description: string;
    task_id: number;
    author_id: string;
    author_name: string;
}
const CreateNotification: React.FC = () => {

    const [notification, setNotification] = useState<NotificationDto>({
        title: '',
        description: '',
        task_id: 0,
        author_id: '',
        author_name: ''
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const author = sessionStorage.getItem('id');
        if(author){
        const { name, value } = e.target;
        setNotification(prevNotification => ({
            ...prevNotification,
            author_id: author,
            [name]: value
        }));}
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await fetch('/api/user/create-notification', {
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
            <div>
                <input
                    type="hidden"
                    name="author_id"
                    value={notification.author_id}
                />
            </div>
            <button type="submit">Create Notification</button>
        </form>

    );
};

export default CreateNotification;