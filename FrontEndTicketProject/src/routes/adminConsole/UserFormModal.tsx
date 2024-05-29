import React, { useEffect } from 'react';
import { useForm, SubmitHandler } from 'react-hook-form';
import axios from 'axios';
import AdminService from '../../services/admin-service';
import { FormData } from '../../@types/types';
import InputField from '../../components/InputField';
import { Dialogs } from '../../ui/dialogs';

interface UserFormModalProps {
    formData: FormData;
    setFormData: (formData: FormData) => void;
    closeModal: () => void;
    fetchUsers: () => void;
    editMode: boolean;
}

const UserFormModal: React.FC<UserFormModalProps> = ({ formData, setFormData, closeModal, fetchUsers, editMode }) => {
    const { register, handleSubmit, setValue, formState: { errors } } = useForm<FormData>({
        defaultValues: formData
    });

    useEffect(() => {
        setValue('username', formData.username);
        setValue('email', formData.email);
        setValue('password', formData.password);
        setValue('roles', formData.roles);
    }, [formData, setValue]);

    const onSubmit: SubmitHandler<FormData> = async data => {
        try {
            let response;
            if (editMode && data.id) {
                response = await AdminService.updateUser(data.id, data);
                Dialogs.success("User updated successfully!");
            } else {
                response = await AdminService.createUser(data);
                Dialogs.success("User created successfully!");
            }
            fetchUsers();
            closeModal();
        } catch (error) {
            console.error('Error saving user:', error.message);
            if (axios.isAxiosError(error) && error.response) {
                const message = error.response.data.message || "Error saving user.";
                Dialogs.error(message);
            } else {
                Dialogs.error("An unexpected error occurred.");
            }
        }
    };

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded-lg max-w-md w-full">
                <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
                    <InputField label="Username" name="username" register={register} errors={errors} required type="text" />
                    <InputField label="Email" name="email" register={register} errors={errors} required type="email" />
                    {!editMode && (
                        <InputField
                            label="Password"
                            name="password"
                            type="password"
                            register={register}
                            errors={errors}
                            required
                            pattern={{
                                value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,32}$/,
                                message: "Password must contain at least 8 characters, one uppercase, one lowercase, one number, and one special character."
                            }}
                        />
                    )}
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Roles</label>
                        <select
                            multiple
                            name="roles"
                            className={`mt-1 block w-full p-2 border border-gray-300 rounded-md ${editMode ? 'bg-yellow-100' : ''}`}
                            {...register("roles")}
                        >
                            <option value="ROLE_USER">User</option>
                            <option value="ROLE_ADMIN">Admin</option>
                        </select>
                    </div>
                    <div className="flex justify-end space-x-2">
                        <button type="submit" className="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700">
                            {editMode ? 'Update User' : 'Create User'}
                        </button>
                        <button type="button" onClick={closeModal} className="inline-flex justify-center py-2 px-4 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50">
                            Cancel
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default UserFormModal;
