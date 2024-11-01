import React, { useEffect, useState } from 'react';
import useVenueStore from '../stores/venueStore';
import VenueForm from './forms/VenueForm.jsx';

const VenueList = () => {
    const venueStore = useVenueStore();
    const [isEditing, setEditing] = useState(false);
    const [selectedVenueId, setSelectedVenueId] = useState(null);

    useEffect(() => {
        venueStore.fetchVenues();
    }, []);

    return (
        <div style={{margin: `2em`}}>
            <h1>Venues</h1>
            <button onClick={() => { setEditing(true); setSelectedVenueId(null); }}>Add Venue</button>
            <ul>
                {venueStore.venues.map(venue => (
                    <li key={venue.id}>
                        {venue.name}
                        <button onClick={() => { setEditing(true); setSelectedVenueId(venue.id); }}>Edit</button>
                        <button onClick={() => venueStore.deleteVenue(venue.id)}>Delete</button>
                    </li>
                ))}
            </ul>
            {isEditing && <VenueForm venueId={selectedVenueId} onClose={() => setEditing(false)} />}
        </div>
    );
};

export default VenueList;
