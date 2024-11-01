import React, { useState, useEffect } from 'react';
import useVenueStore from '../../stores/venueStore';

const VenueForm = ({ venueId, onClose }) => {
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const venueStore = useVenueStore();

    useEffect(() => {
        if (venueId) {
            const venue = venueStore.venues.find(v => v.id === venueId);
            if (venue) {
                setName(venue.name);
                setLocation(venue.location);
            }
        }
    }, [venueId]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const venueData = { name, location };
        if (venueId) {
            await venueStore.updateVenue(venueId, venueData);
        } else {
            await venueStore.addVenue(venueData);
        }
        onClose();
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Venue Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
            />
            <input
                type="text"
                placeholder="Location"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
                required
            />
            <button type="submit">{venueId ? 'Update' : 'Create'} Venue</button>
        </form>
    );
};

export default VenueForm;
