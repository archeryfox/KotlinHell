import React, { useEffect, useState } from 'react';
import useEventStore from '../../stores/eventStore';
import useVenueStore from '../../stores/venueStore';
import useSpeakerStore from '../../stores/speakerStore';

const EventForm = ({ eventId, onClose }) => {
    const { addEvent, updateEvent, events } = useEventStore();
    const { venues, fetchVenues } = useVenueStore();
    const { speakers, fetchSpeakers } = useSpeakerStore();

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [date, setDate] = useState('');
    const [venueId, setVenueId] = useState('');
    const [selectedSpeakers, setSelectedSpeakers] = useState([]);

    useEffect(() => {
        if (eventId) {
            const event = events.find(e => e.id === eventId);
            if (event) {
                setName(event.name);
                setDescription(event.description);
                setDate(event.date);
                setVenueId(event.venue?.id || '');
                setSelectedSpeakers(event.speakers ? event.speakers.map(s => s.id) : []);
            }
        } else {
            setName('');
            setDescription('');
            setDate('');
            setVenueId('');
            setSelectedSpeakers([]);
        }
    }, [eventId, events]);

    useEffect(() => {
        fetchVenues();
        fetchSpeakers();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const eventData = {
            name,
            description,
            date,
            venue: { id: venueId },
            speakers: selectedSpeakers.map(id => ({ id }))
        };

        if (eventId) {
            await updateEvent(eventId, eventData);
        } else {
            await addEvent(eventData);
        }
        onClose();
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Название события"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
            />
            <textarea
                placeholder="Описание"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                required
            />
            <input
                type="datetime-local"
                value={date}
                onChange={(e) => setDate(e.target.value)}
                required
            />
            <select
                value={venueId}
                onChange={(e) => setVenueId(e.target.value)}
                required
            >
                <option value="">Выберите место</option>
                {venues.map(venue => (
                    <option key={venue.id} value={venue.id}>{venue.name}</option>
                ))}
            </select>
            <select
                multiple
                value={selectedSpeakers}
                onChange={(e) => {
                    const options = Array.from(e.target.options);
                    const selected = options.filter(option => option.selected).map(option => option.value);
                    setSelectedSpeakers(selected);
                }}
            >
                {speakers.map(speaker => (
                    <option key={speaker.id} value={speaker.id}>{speaker.name}</option>
                ))}
            </select>
            <button type="submit">{eventId ? 'Обновить событие' : 'Создать событие'}</button>
            <button type="button" onClick={onClose}>Отменить</button>
        </form>
    );
};

export default EventForm;
