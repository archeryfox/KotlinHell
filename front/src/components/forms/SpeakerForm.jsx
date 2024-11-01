import React, { useState, useEffect } from 'react';
import useSpeakerStore from '../../stores/speakerStore';

const SpeakerForm = ({ speakerId, onClose }) => {
    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [bio, setBio] = useState('');
    const speakerStore = useSpeakerStore();

    useEffect(() => {
        if (speakerId) {
            const speaker = speakerStore.speakers.find(s => s.id === speakerId);
            if (speaker) {
                setName(speaker.name);
                setLastName(speaker.lastName);
                setBio(speaker.bio);
            }
        }
    }, [speakerId, speakerStore.speakers]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const speakerData = { name, lastName, bio };
        if (speakerId) {
            await speakerStore.updateSpeaker(speakerId, speakerData);
        } else {
            await speakerStore.addSpeaker(speakerData);
        }
        onClose();
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="First Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
            />
            <input
                type="text"
                placeholder="Last Name"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                required
            />
            <textarea
                placeholder="Bio"
                value={bio}
                onChange={(e) => setBio(e.target.value)}
            />
            <button type="submit">{speakerId ? 'Update' : 'Create'} Speaker</button>
        </form>
    );
};

export default SpeakerForm;
