import React, { useEffect, useState } from 'react';
import useUserStore from '../stores/userStore';
import UserForm from './forms/UserForm.jsx';

const UserList = () => {
    const userStore = useUserStore();
    const [isEditing, setEditing] = useState(false);
    const [selectedUserId, setSelectedUserId] = useState(null);

    useEffect(() => {
        userStore.fetchUsers();
    }, []);

    return (
        <div>
            <h1>Users</h1>
            <button onClick={() => { setEditing(true); setSelectedUserId(null); }}>Add User</button>
            <ul>
                {userStore.users.map(user => (
                    <li style={{margin:`1em`}} key={user.id}>

                        <p>{user.name} {user.lastName}</p>
                        <button onClick={() => { setEditing(true); setSelectedUserId(user.id); }}>Edit</button>
                        <button onClick={() => userStore.deleteUser(user.id)}>Delete</button>
                    </li>
                ))}
            </ul>
            {isEditing && <UserForm userId={selectedUserId} onClose={() => setEditing(false)} />}
        </div>
    );
};

export default UserList;
