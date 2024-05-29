import React, { useState, useEffect } from 'react';
import AdminService from '../../services/admin-service';
import { FormData, UserResponseDTO } from '../../@types/types';
import UserFormModal from './UserFormModal'; // Assuming UserFormModal is in the same directory

const initialFormData: FormData = {
    id: null,
    username: '',
    password: '',
    email: '',
    roles: [],
};

const AdminConsole: React.FC = () => {
    const [formData, setFormData] = useState<FormData>(initialFormData);
    const [users, setUsers] = useState<UserResponseDTO[]>([]);
    const [isModalOpen, setModalOpen] = useState<boolean>(false);
    const [editMode, setEditMode] = useState<boolean>(false);
    const [highlightedUserId, setHighlightedUserId] = useState<number | null>(null);

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = () => {
        AdminService.getAllUsers().then(response => {
            console.log("Fetched users:", response);
            setUsers(response);
        }).catch(error => {
            console.error('Error fetching users:', error);
        });
    };

    const openModal = (user?: UserResponseDTO) => {
        if (user) {
            setFormData({
                id: user.id,
                username: user.username,
                password: '', // Password is not fetched or edited directly
                email: user.email,
                roles: user.roles,
            });
            setEditMode(true);
            setHighlightedUserId(user.id);
        } else {
            setFormData(initialFormData);
            setEditMode(false);
            setHighlightedUserId(null);
        }
        setModalOpen(true);
    };

    const closeModal = () => {
        setModalOpen(false);
        setHighlightedUserId(null);
    };

    const confirmDeleteUser = async (userId: number) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this user?");
        if (confirmDelete) {
            try {
                await AdminService.deleteUserById(userId);
                fetchUsers();
            } catch (error) {
                console.error('Error deleting user:', error);
            }
        }
    };

    return (
        <div className="p-4">
            <button onClick={() => openModal()} className="mb-4 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700">
                Create New User
            </button>

            <div>
                <h3 className="mb-2 text-lg font-medium text-gray-700">Users List</h3>
                <ul>
                    {users.map(user => (
                        <li key={user.id} className={`mb-2 p-2 border rounded ${highlightedUserId === user.id ? 'bg-yellow-100' : ''}`}>
                            {user.username} ({user.email}) - Roles: {user.roles.join(', ')}
                            <button onClick={() => openModal(user)} className="ml-2 text-white bg-green-500 hover:bg-green-600 px-3 py-1 rounded">Edit</button>
                            <button onClick={() => confirmDeleteUser(user.id)} className="ml-2 text-white bg-red-500 hover:bg-red-600 px-3 py-1 rounded">Delete</button>
                        </li>
                    ))}
                </ul>
                <button onClick={fetchUsers} className="mt-4 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700">
                    Refresh
                </button>
            </div>

            {isModalOpen && (
                <UserFormModal formData={formData} setFormData={setFormData} closeModal={closeModal} fetchUsers={fetchUsers} editMode={editMode} />
            )}
        </div>
    );
};

export default AdminConsole;
