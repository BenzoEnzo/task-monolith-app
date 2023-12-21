import React, { useState, useEffect } from 'react';
import axios from 'axios';
interface TaskDto {
    title: string;
    description: string;
    task_id: number;
}
const Report = () => {
    const [tasks, setTasks] = useState([]);
    const [isFormOpen, setIsFormOpen] = useState(false);
    const [formFields, setFormFields] = useState({
        firstName: '',
        lastName: '',
        age: '',
        description: '',
        phone: '',
    });

    useEffect(() => {

        axios.get('/api/administrator/query-all-tasks')
            .then(response => {
                setTasks(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the tasks', error);
            });
    }, []);




    const handleAcceptReport = (taskId: Number) => {
        setIsFormOpen(true);
    };

    const handleCloseForm = () => {
        setIsFormOpen(false);
    };

    const handleReadDetails = (taskId: Number) => {
        console.log(`Read details for report with ID: ${taskId}`);
    };

    const handleFormChange = (e:any) => {
        setFormFields({
            ...formFields,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = () => {
        console.log("Wysłano formularz:", formFields);
        // Logika do wysyłania danych formularza
        setIsFormOpen(false); // Zamknij formularz po wysłaniu
    };


    return (
        <div className="report-container">
            <h2>Lista ogłoszeń</h2>
            <ul className="task-list">
                {tasks.map((task, index) => (
                    <li key={index} className="task-item">
                        <h3>{task["name"]}</h3>
                        <p>Pay: {task["pay"]}</p>
                        <p>Status: {task["status"]}</p>
                        <p>Description: {task["description"]}</p>
                        <p>  {task["creator_id"] == sessionStorage.getItem("id") && (
                           <>
                               Jesteś zleceniodawcą tego zadania
                           </>
                        )}</p>
                        <div className="button-container">
                            {task["creator_id"] != sessionStorage.getItem("id") && (
                                <>
                                    {task["status"] != "WAITING_FOR_ACCEPT" && (
                                        <>
                                    <button
                                        className="accept-button"
                                        onClick={() => handleAcceptReport(task["id"])}
                                    >
                                        Prześlij ofertę
                                    </button>
                                        </>
                                    )}
                                    <button
                                        className="details-button"
                                        onClick={() => handleReadDetails(task["id"])}
                                    >
                                        Czytaj szczegóły
                                    </button>

                                </>
                            )}
                        </div>
                        {/*<p>Creator ID: {task["creator_id"]}</p>*/}
                        {/*<p>Assignee ID: {task["assignee_id"]}</p>*/}
                    </li>
                ))}
            </ul>
            {isFormOpen && (
                <div className="overlay">
                <div className="offer-form">
                    <h2>Wyślij swoją ofertę</h2>
                    <input
                        name="firstName"
                        value={formFields.firstName}
                        onChange={handleFormChange}
                        placeholder="Imię"
                        className="form-input"
                    />
                    <input
                        name="lastName"
                        value={formFields.lastName}
                        onChange={handleFormChange}
                        placeholder="Nazwisko"
                        className="form-input"
                    />
                    <input
                        name="age"
                        value={formFields.age}
                        onChange={handleFormChange}
                        placeholder="Wiek"
                        className="form-input"
                    />
                    <textarea
                        name="description"
                        value={formFields.description}
                        onChange={handleFormChange}
                        placeholder="Opis"
                        className="form-textarea"
                    />
                    <input
                        name="phone"
                        value={formFields.phone}
                        onChange={handleFormChange}
                        placeholder="Nr tel"
                        className="form-input"
                    />
                    <button onClick={handleSubmit}>Wyślij</button>
                    <button onClick={handleCloseForm}>Zamknij</button>
                </div>
                </div>
            )}
        </div>
    );
}

export default Report;