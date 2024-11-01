// src/components/EventList.jsx
import React, { useEffect, useState } from 'react';
import useEventStore from '../stores/eventStore';
import EventForm from './forms/EventForm';

const EventList = () => {
    const eventStore = useEventStore();
    const [isEditing, setEditing] = useState(false);
    const [selectedEventId, setSelectedEventId] = useState(null);

    useEffect(() => {
        eventStore.fetchEvents();
    }, []);

    return (
        <div style={{ margin: '2em' }}>
            <h1>События</h1>
            <button onClick={() => { setEditing(true); setSelectedEventId(null); }}>Добавить событие</button>
            <ul>
                {eventStore.events.map(event => (
                    <li style={{margin:`1em`}} key={event.id}>
                        {event.name} - {event.description} - {new Date(event.date).toLocaleString()}
                        <button onClick={() => { setEditing(true); setSelectedEventId(event.id); }}>Редактировать</button>
                        <button onClick={() => eventStore.deleteEvent(event.id)}>Удалить</button>
                    </li>
                ))}
            </ul>
            {isEditing && <EventForm eventId={selectedEventId} onClose={() => setEditing(false)} />}
        </div>
    );
};

export default EventList;
