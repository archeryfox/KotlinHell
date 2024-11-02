import React, { useEffect, useState } from 'react';
import useTicketStore from '../stores/ticketStore';
import TicketForm from './forms/TicketForm.jsx';

const TicketList = () => {
    const ticketStore = useTicketStore();
    const [isEditing, setEditing] = useState(false);
    const [selectedTicketId, setSelectedTicketId] = useState(null); // Используем null вместо 0

    useEffect(() => {
        ticketStore.fetchTickets();
    }, []); // Добавляем ticketStore в зависимости

    const handleAddClick = () => {
        setEditing(true);
        setSelectedTicketId(null); // При добавлении нового тикета, id будет null
    };

    const handleEditClick = (id) => {
        setEditing(true);
        setSelectedTicketId(id);
    };

    return (
        <div style={{ margin: '2em' }}>
            <h1>Tickets</h1>
            <button onClick={handleAddClick}>Add Ticket</button>
            <ul>
                {ticketStore.tickets.map(ticket => (
                    <li key={ticket.id}>
                        {ticket.event?.name} - {ticket.price} Руб {/* Используем безопасный доступ для event */}
                        <p>Для: {ticket.user?.username}</p>
                        <button onClick={() => handleEditClick(ticket.id)}>Edit</button>
                        <button onClick={() => ticketStore.deleteTicket(ticket.id)}>Delete</button>
                    </li>
                ))}
            </ul>
            {isEditing && (
                <TicketForm
                    ticketId={selectedTicketId}
                    onClose={() => setEditing(false)}
                />
            )}
        </div>
    );
};

export default TicketList;
