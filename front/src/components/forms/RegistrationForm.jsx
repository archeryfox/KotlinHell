// src/components/RegistrationForm.jsx
import React, {useState} from 'react';
import axios from 'axios';
import useCurrentUserStore from "../../stores/currentUserStore.js";
import {useNavigate} from "react-router-dom";

const RegistrationForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState(''); // Новое состояние для хранения email
    const [message, setMessage] = useState('');
    const {user, setUser} = useCurrentUserStore()
    const handleRegister = async () => {
        const nav = useNavigate()
        try {
            const response = await axios.post('http://localhost:8080/api/auth/register', {
                username: username,
                password: password,
                email: email // Добавляем email в тело запроса
            });
            setMessage(response.data);
            setUser(response)
            nav('/userpage')
        } catch (error) {
            setMessage(error.response.data);
        }
    };

    return (
        <div>
            <h2>Регистрация</h2>
            <br/>
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <br/>

            <br/>
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <br/>

            <br/>
            <input
                type="email" // Используем тип email для валидации
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <br/>
            <br/>
            <button onClick={handleRegister}>Зарегистрироваться</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default RegistrationForm;
