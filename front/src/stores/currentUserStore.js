// src/stores/currentUserStore.js
import {create} from 'zustand';

const useCurrentUserStore = create((set) => ({
    user: JSON.parse(localStorage.getItem('currentUser')) ?? null,
    setUser: (newUser) => {
        set({ user: newUser });
        localStorage.setItem('currentUser', JSON.stringify(newUser)); // Сохраняем в localStorage
    },
    clearUser: () => {
        set({ user: null });
        localStorage.removeItem('currentUser'); // Удаляем из localStorage
    },
}));

export default useCurrentUserStore;
