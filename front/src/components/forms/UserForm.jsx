import React, { useState, useEffect } from 'react';
import useUserStore from '../../stores/userStore.js';

const UserForm = ({ userId, onClose }) => {
    const [name, setName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const userStore = useUserStore();

    useEffect(() => {
        if (userId) {
            const user = userStore.users.find(u => u.id === userId);
            if (user) {
                setName(user.name);
                setLastName(user.lastName);
                setEmail(user.email);
            }
        }
    }, [userId, userStore.users]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const userData = { name, lastName, email };
        if (userId) {
            await userStore.updateUser(userId, userData);
        } else {
            await userStore.addUser(userData);
        }
        onClose();
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="First Name"
                value={name}
                className={`p-3`}
                onChange={(e) => setName(e.target.value)}
                required
            />

            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
            />
            <button type="submit">{userId ? 'Update' : 'Create'} User</button>
        </form>
    );
};

export default UserForm;
