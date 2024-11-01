import {create} from 'zustand';
import axios from 'axios';

const apiUrl = 'http://localhost:8080/events'; // для GET запросов
const apiBaseUrl = 'http://localhost:8080/api/events'; // для POST, PUT и DELETE запросов

const useEventStore = create((set) => ({
    events: [],

    // Получение всех событий
    fetchEvents: async (page = 0, size = 20) => {
        const response = await axios.get('http://localhost:8080/events'); // Убедитесь, что это правильный URL для получения событий
        set({
            events: response.data._embedded.events.map(event => ({
                id: event._links.self.href.split('/').pop(), // Извлекаем ID события из ссылки
                name: event.name,
                description: event.description,
                date: event.date,
                // Добавьте другие необходимые поля
            }))
        });
    },

    // Добавление нового события
    addEvent: async (event) => {
        const response = await axios.post(apiBaseUrl, event);
        set((state) => ({events: [...state.events, response.data]}));
    },

    // Обновление существующего события
    updateEvent: async (id, event) => {
        const response = await axios.put(`${apiBaseUrl}/${id}`, event);
        set((state) => ({
            events: state.events.map((e) => (e.id === id ? response.data : e)),
        }));
    },

    // Удаление события
    deleteEvent: async (id) => {
        await axios.delete(`${apiBaseUrl}/${id}`);
        set((state) => ({events: state.events.filter((e) => e.id !== id)}));
    },

    // Поиск событий
    searchEvents: async (name, page = 0, size = 20) => {
        const response = await axios.get(`${apiBaseUrl}/search`, {params: {name, page, size}});
        set({events: response.data._embedded.events});
    },
}));

export default useEventStore;
