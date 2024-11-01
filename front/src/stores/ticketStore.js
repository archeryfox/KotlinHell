import { create } from 'zustand';
import axios from 'axios';

const apiUrl = 'http://localhost:8080/tickets'; // Для GET запросов
const apiBaseUrl = 'http://localhost:8080/api/tickets'; // Для POST, PUT и DELETE запросов

const useTicketStore = create((set) => ({
    tickets: [],
    fetchTickets: async (page = 0, size = 20) => {
        const response = await axios.get(apiUrl, { params: { page, size } });
        const tickets = response.data._embedded.tickets;

        const updatedTickets = await Promise.all(tickets.map(async (ticket) => {
            const eventResponse = await axios.get(ticket._links.event.href);
            const userResponse = await axios.get(ticket._links.user.href); // Получаем данные пользователя
            return {
                id: ticket._links.self.href.split('/').pop(), // Извлекаем ID тикета из ссылки
                ...ticket,
                event: eventResponse.data,
                user: userResponse.data // Добавляем информацию о пользователе в тикет
            };
        }));

        set({ tickets: updatedTickets });

    },
    addTicket: async (ticket) => {
        const response = await axios.post(apiBaseUrl, ticket); // Изменено на apiBaseUrl
        const eventResponse = await axios.get(response.data._links.event.href);
        const userResponse = await axios.get(response.data._links.user.href); // Получаем данные пользователя
        set((state) => ({ tickets: [...state.tickets, { ...response.data, event: eventResponse.data, user: userResponse.data }] }));
    },
    updateTicket: async (id, ticket) => {
        const response = await axios.put(`${apiBaseUrl}/${id}`, ticket); // Изменено на apiBaseUrl
        const eventResponse = await axios.get(response.data._links.event.href);
        const userResponse = await axios.get(response.data._links.user.href); // Получаем данные пользователя
        set((state) => ({
            tickets: state.tickets.map((t) => (t.id === id ? { ...response.data, event: eventResponse.data, user: userResponse.data } : t)),
        }));
    },
    deleteTicket: async (id) => {
        await axios.delete(`${apiBaseUrl}/${id}`); // Изменено на apiBaseUrl
        set((state) => ({ tickets: state.tickets.filter((t) => t.id !== id) }));
    },
    searchTickets: async (query, page = 0, size = 20) => {
        const response = await axios.get(`${apiUrl}/search`, { params: { query, page, size } });
        const tickets = response.data._embedded.tickets;
        const updatedTickets = await Promise.all(tickets.map(async (ticket) => {
            const eventResponse = await axios.get(ticket._links.event.href);
            const userResponse = await axios.get(ticket._links.user.href); // Получаем данные пользователя
            return {
                ...ticket,
                event: eventResponse.data,
                user: userResponse.data
            };
        }));
        set({ tickets: updatedTickets });
    },
    fetchUserByTicketId: async (ticketId) => { // Новый метод для получения пользователя по ID тикета
        const response = await axios.get(`http://localhost:8080/tickets/${ticketId}/user`);
        return response.data; // Возвращаем данные пользователя
    },
}));

export default useTicketStore;
