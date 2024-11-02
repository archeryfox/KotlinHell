// src/App.jsx
import React, {useEffect} from 'react';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import RegistrationForm from './components/forms/RegistrationForm';
import LoginForm from './components/forms/LoginForm.jsx';
import useCurrentUserStore from "./stores/currentUserStore.js"; // Измените здесь на useCurrentUserStore
import UserPage from "./pages/UserPage.jsx";

const App = () => {
    const {setUser, user, clearUser} = useCurrentUserStore(); // Теперь правильно используем

    useEffect(() => {
        const loadUserFromLocalStorage = () => {
            const storedUser = localStorage.getItem('currentUser');
            return storedUser ? JSON.parse(storedUser) : null;
        };

        const loadedUser = loadUserFromLocalStorage();
        if (loadedUser) {
            setUser(loadedUser);
        }
    }, [setUser]);

    return (
        <Router>
            <div style={{padding: `5em`}}>

                <Routes>
                    <Route path="/login" element={<LoginForm/>}/>
                    <Route path="/register" element={<RegistrationForm/>}/>
                    {!user ? (
                        <>
                        </>
                    ) : (
                        <Route path="/userpage" element={<UserPage/>}/>
                    )}
                    <Route path="/" element={
                        <>
                            <h1>Добро пожаловать!</h1>
                            <br/>
                            <Link to={`/login`}>Авторизоваться</Link>
                            <br/>
                            <Link to={`/register`}>Зарегаться</Link>
                        </>
                    }/>
                </Routes>

            </div>
        </Router>
    );
};

export default App;
