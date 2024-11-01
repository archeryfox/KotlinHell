import {create} from 'zustand';
import axios from 'axios';

const useSpeakerStore = create((set) => ({
    speakers: [],
    fetchSpeakers: async () => {
        const response = await axios.get('http://localhost:8080/api/speakers');
        set({ speakers: response.data.content });
    },
    addSpeaker: async (speaker) => {
        const response = await axios.post('http://localhost:8080/api/speakers', speaker);
        set((state) => ({ speakers: [...state.speakers, response.data] }));
    },
    updateSpeaker: async (id, speaker) => {
        const response = await axios.put(`http://localhost:8080/api/speakers/${id}`, speaker);
        set((state) => ({
            speakers: state.speakers.map((s) => (s.id === id ? response.data : s)),
        }));
    },
    deleteSpeaker: async (id) => {
        await axios.delete(`http://localhost:8080/api/speakers/${id}`);
        set((state) => ({ speakers: state.speakers.filter((s) => s.id !== id) }));
    },
    searchSpeakers: async (name) => {
        const response = await axios.get(`http://localhost:8080/api/speakers/search?name=${name}`);
        set({ speakers: response.data.content });
    },
}));

export default useSpeakerStore;
