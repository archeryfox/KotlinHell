import React, { useState, useEffect } from 'react';
import useTicketStore from '../../stores/ticketStore';
import useEventStore from '../../stores/eventStore.js';
import useUserStore from '../../stores/userStore.js'; // Импортируем userStore

const TicketForm = ({ ticketId, onClose }) => {
    const ticketStore = useTicketStore();
    const eventStore = useEventStore();
    const userStore = useUserStore(); // Используем userStore для получения списка пользователей

    const [price, setPrice] = useState('');
    const [eventId, setEventId] = useState('');
    const [userId, setUserId] = useState(''); // Добавляем поле для ID пользователя

    useEffect(() => {
        if (ticketId) {
            const ticket = ticketStore.tickets.find(t => t.id === ticketId);
            if (ticket) {
                setPrice(ticket.price);
                setEventId(ticket.event.id);
                setUserId(ticket.user.id); // Устанавливаем ID пользователя, если редактируется билет
            }
        }
    }, []);

    useEffect(() => {
        userStore.fetchUsers(); // Загружаем пользователей при монтировании компонента
    }, [userStore]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const ticketData = {
            price,
            event: { id: eventId },
            user: { id: userId } // Добавляем user с корректным id
        };
        if (ticketId) {
            await ticketStore.updateTicket(ticketId, ticketData);
        } else {
            await ticketStore.addTicket(ticketData);
        }
        onClose();
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="number"
                placeholder="Ticket Price"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                required
            />
            <select value={eventId} onChange={(e) => setEventId(e.target.value)} required>
                <option value="">Select Event</option>
                {eventStore?.events.map(event => (
                    <option key={event.id} value={event.id}>{event.name}</option>
                ))}
            </select>
                {/*{JSON.stringify(userStore?.users)}*/}
            <select value={userId} onChange={(e) => setUserId(e.target.value)} required>
                <option value="">Select User</option>
                {userStore?.users.map(user => (
                    <option key={user.id} value={user.id}>{user?.username}</option>
                ))}
            </select>
            <button type="submit">{ticketId ? 'Update' : 'Create'} Ticket</button>
        </form>
    );
};

export default TicketForm;
