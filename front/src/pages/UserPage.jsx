// src/pages/UserPage.jsx
import React, {useEffect} from 'react';
import useCurrentUserStore from '../stores/currentUserStore';
import TicketList from "../components/TicketList.jsx";
import {useNavigate} from "react-router-dom";
import VenueList from "../components/VenueList.jsx";
import EventList from "../components/EventList.jsx";

const UserPage = () => {
    const {user, clearUser} = useCurrentUserStore();
    const navigate = useNavigate()

    useEffect(() => {

    }, []);

    if (!user) {
        return <p>Пожалуйста, войдите в систему.</p>;
    }

    function out() {
        clearUser()
        navigate("/")
    }

    return (
        <div style={{margin: '2em'}}>
            <h2>Добро пожаловать, {user.username}!</h2>
            <p>Это ваша пользовательская страница.</p>
            {user.email && <p>Электронная почта: {user.email}</p> }
            <TicketList/>
            <VenueList/>
            <EventList/>
            <button onClick={out}>Выйти</button>
            {/* Добавьте сюда дополнительные элементы интерфейса для пользователя */}
        </div>
    );
};

export default UserPage;
