import React, {useEffect, useState} from 'react';
import './Base.css';


interface TaskDto {
    name: string;
    pay: number;
    description: string;
    creator_id: string;
}

const CreateTask: React.FC = () => {
    const [task, setTask] = useState<TaskDto>({
        name: '',
        pay: 0,
        description: '',
        creator_id: '',
    });

    useEffect(() => {
        const storedCreatorId = sessionStorage.getItem('id');
        if (storedCreatorId) {
            setTask(prevTask => ({
                ...prevTask,
                creator_id: storedCreatorId
            }));
        }
    }, []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setTask(prevState => ({
            ...prevState,
            [name]: name === 'pay' ? parseFloat(value) || 0 : value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const response = await fetch('/api/user/create-task', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(task)
            });

            if (response.status === 201) {
                console.log('Task created successfully');

            } else {
                console.error('Failed to create task');

            }
        } catch (error) {
            console.error('Error:', error);

        }
    };

    return (
            <form className="task-form" onSubmit={handleSubmit}>
                <h2>Utwórz zadanie</h2>
                <input
                    type="text"
                    name="name"
                    value={task.name}
                    onChange={handleChange}
                    placeholder="Nazwa zadania"
                    className="form-input"
                />
                <textarea
                    name="description"
                    value={task.description}
                    onChange={handleChange}
                    placeholder="Opis"
                    className="form-textarea"
                />
                <div className="label">Wynagrodzenie za wykonane zlecenie:</div>
                <input
                    type="number"
                    name="pay"
                    value={task.pay}
                    onChange={handleChange}
                    placeholder="Wynagrodzenie"
                    className="form-input"
                />
                <input
                    type="hidden"
                    name="creator_id"
                    value={task.creator_id}
                />
                <button type="submit">Utwórz zadanie</button>
            </form>

    );
};

export default CreateTask;