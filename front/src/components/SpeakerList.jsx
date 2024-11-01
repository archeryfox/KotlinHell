import React, { useEffect, useState } from 'react';
import useSpeakerStore from '../stores/speakerStore';
import SpeakerForm from './forms/SpeakerForm.jsx';

const SpeakerList = () => {
    const speakerStore = useSpeakerStore();
    const [isEditing, setEditing] = useState(false);
    const [selectedSpeakerId, setSelectedSpeakerId] = useState(null);

    useEffect(() => {
        speakerStore.fetchSpeakers();
    }, []);

    return (
        <div style={{margin: `2em`}}>
            <h1>Speakers</h1>
            <button onClick={() => { setEditing(true); setSelectedSpeakerId(null); }}>Add Speaker</button>
            <ul>
                {speakerStore.speakers.map(speaker => (
                    <li key={speaker.id}>
                        {speaker.name} {speaker.lastName}
                        <button onClick={() => { setEditing(true); setSelectedSpeakerId(speaker.id); }}>Edit</button>
                        <button onClick={() => speakerStore.deleteSpeaker(speaker.id)}>Delete</button>
                    </li>
                ))}
            </ul>
            {isEditing && <SpeakerForm speakerId={selectedSpeakerId} onClose={() => setEditing(false)} />}
        </div>
    );
};

export default SpeakerList;
