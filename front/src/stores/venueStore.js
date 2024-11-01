import {create} from 'zustand';
import axios from 'axios';

const useVenueStore = create((set) => ({
    venues: [],
    fetchVenues: async () => {
        const response = await axios.get('http://localhost:8080/api/venues');
        set({ venues: response.data.content });
    },
    addVenue: async (venue) => {
        const response = await axios.post('http://localhost:8080/api/venues', venue);
        set((state) => ({ venues: [...state.venues, response.data] }));
    },
    updateVenue: async (id, venue) => {
        const response = await axios.put(`http://localhost:8080/api/venues/${id}`, venue);
        set((state) => ({
            venues: state.venues.map((v) => (v.id === id ? response.data : v)),
        }));
    },
    deleteVenue: async (id) => {
        await axios.delete(`http://localhost:8080/api/venues/${id}`);
        set((state) => ({ venues: state.venues.filter((v) => v.id !== id) }));
    },
    searchVenues: async (name) => {
        const response = await axios.get(`http://localhost:8080/api/venues/search?name=${name}`);
        set({ venues: response.data.content });
    },
}));

export default useVenueStore;
