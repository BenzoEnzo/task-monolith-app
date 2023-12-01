import './Base.css'
import React, { useState } from "react";
import {validateAccount} from "../functions/Sign";
import {useNavigate} from "react-router-dom";

const SignPanel = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isError, setIsError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();
    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        validateAccount(email, password)
            .then(response => {
                navigate("/logged-in");
                console.log('Login success', response);
            })
            .catch(error => {
                setErrorMessage("Invalid Credentials ! Try again");
                setIsError(true);
                console.error('Login failed', error);
            });
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleSubmit}>
                <h2>Logowanie</h2>
                <input
                    type="text"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Hasło"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button type="submit">Zaloguj się</button>
                {isError && <div className="error-message">{errorMessage}</div>}
            </form>

        </div>
    );
}

export default SignPanel;