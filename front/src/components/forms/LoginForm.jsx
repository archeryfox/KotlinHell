// src/components/LoginForm.jsx
import React, {useState} from 'react';
import axios from 'axios';
import useCurrentUserStore from "../../stores/currentUserStore.js";
import {useNavigate} from "react-router-dom";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const {user, setUser} = useCurrentUserStore();
    const navigate = useNavigate()
    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {username, password});
            setMessage(response.data);
            setUser({
                username: username,
                password: password,
            })
            console.log(user);
            navigate('/userpage')
        } catch (error) {
            setMessage(error.response.data);
        }
    };

    return (
        <div>
            <h2>Вход</h2>
            <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)}/>
            <br/>
            <br/>
            <input type="password" placeholder="Password" value={password}
                   onChange={(e) => setPassword(e.target.value)}/>
            <br/>
            <br/>
            <button onClick={handleLogin}>Войти</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default LoginForm;
