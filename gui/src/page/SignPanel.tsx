import './Base.css'
import React, { useState } from "react";
import {validateAccount} from "../functions/Sign";
import {registration} from "../functions/Sign";
import {useNavigate} from "react-router-dom";

const SignPanel = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [rEmail, setREmail] = useState('');
    const [rPassword, setRPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [isError, setIsError] = useState(false);
    const [isRError, setIsRError] = useState(false);
    const [isSuccess, setIsSuccess] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [rErrorMessage, setRErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const navigate = useNavigate();
    const isMatching = (rPassword == confirmPassword);

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        validateAccount(email, password)
            .then(response => {
                navigate("/logged-in");
                console.log('Login success', response);
            })
            .catch(error => {
                setErrorMessage("Invalid Credentials Or account doesnt confirmed");
                setIsError(true);
                console.error('Login failed', error);
            });
    };

    const registerSubmit = (z: React.FormEvent<HTMLFormElement>) => {
        z.preventDefault();
        registration(rEmail, rPassword)
            .then(() => {
                setSuccessMessage("Zarejestrowano pomyślnie, aktywuj konto na mailu");
                setIsSuccess(true);
                setIsRError(false);
            })
            .catch(error => {
                setRErrorMessage("Mail is used");
                setIsRError(true);
                setIsSuccess(false);
                console.error('Register failed', error);
            });
    };

    return (
        <div className="sign-container">
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
            <div className="register-container">
                <form className="login-form" onSubmit={registerSubmit}>
                    <h2>Rejestracja</h2>
                    <input
                        type="text"
                        placeholder="Email"
                        value={rEmail}
                        onChange={(z) => setREmail(z.target.value)}
                    />
                    <input
                        type="password"
                        placeholder="Hasło"
                        value={rPassword}
                        onChange={(z) => setRPassword(z.target.value)}
                    />
                    <input
                        type="password"
                        placeholder="Powtórz hasło"
                        value={confirmPassword}
                        onChange={(z) => setConfirmPassword(z.target.value)}
                    />
                    {isMatching ? (
                        <>
                        <div style={{ color: 'green' }}></div>
                        <button type="submit">Zarejestruj się</button>
                        </>
                    ) : (
                        <div style={{ color: 'red' }}>Hasła nie są takie same.</div>
                    )}
                    {isSuccess  && <div className="success-message">{successMessage}</div>}
                    {isRError && <div className="error-message">{rErrorMessage}</div>}
                </form>
            </div>
        </div>
    );
}

export default SignPanel;