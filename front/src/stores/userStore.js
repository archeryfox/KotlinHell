import { create } from 'zustand';
import axios from 'axios';

const useUserStore = create((set) => ({
    users: [],
    fetchUsers: async () => {
        const response = await axios.get('http://localhost:8080/users'); // Исправлено на правильный URL
        set({ users: response.data._embedded.users.map(user => ({
                id: user._links.self.href.split('/').pop(), // Извлекаем id из ссылки
                username: user.username,
                email: user.email,
            })) });
    },
    addUser: async (user) => {
        const response = await axios.post('http://localhost:8080/api/users', user);
        set((state) => ({
            users: [...state.users, {
                id: response.data._links?.self.href.split('/').pop(), // Извлекаем id из ссылки
                ...response.data
            }]
        }));
    },
    updateUser: async (id, user) => {
        const response = await axios.put(`http://localhost:8080/api/users/${id}`, user);
        set((state) => ({
            users: state.users.map((u) => (u.id === id ? {
                id: response.data._links.self.href.split('/').pop(), // Обновляем id
                ...response.data
            } : u)),
        }));
    },
    deleteUser: async (id) => {
        await axios.delete(`http://localhost:8080/api/users/${id}`); // Исправлено на правильный URL
        set((state) => ({ users: state.users.filter((u) => u.id !== id) }));
    },
    searchUsers: async (name) => {
        const response = await axios.get(`http://localhost:8080/users/search?name=${name}`); // Исправлено на правильный URL
        set({ users: response.data._embedded.users.map(user => ({
                id: user._links.self.href.split('/').pop(), // Извлекаем id из ссылки
                name: user.name,
                email: user.email,
            })) });
    },
}));

export default useUserStore;
