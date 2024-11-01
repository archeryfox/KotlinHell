import React from 'react';
import EventList from './components/EventList';
import UserList from './components/UserList';
import TicketList from './components/TicketList';
import SpeakerList from './components/SpeakerList';
import VenueList from './components/VenueList';

const App = () => {
    return (
        <div style={{margin: `2em`}}>
            <EventList/>
            <TicketList/>
            <SpeakerList/>
            <VenueList/>
            <UserList/>
        </div>
    );
};

export default App;
