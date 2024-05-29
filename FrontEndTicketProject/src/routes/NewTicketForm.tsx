import React, { useState } from 'react';
import { Tick } from "../services/ticket-service";
import { Dialogs } from '../ui/dialogs';

const NewTicketForm: React.FC<{ onSuccess: () => void; onClose: () => void }> = ({ onSuccess, onClose }) => {
    const [subject, setSubject] = useState('');
    const [description, setDescription] = useState('');
    const [formErrors, setFormErrors] = useState({ subject: '', description: '' });

    const validateForm = () => {
        let errors = { subject: '', description: '' };
        if (!subject) errors.subject = 'Subject is required';
        if (!description) errors.description = 'Description is required';
        setFormErrors(errors);
        return !errors.subject && !errors.description;
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!validateForm()) return;
        try {
            await Tick.createTicket({ subject, description });
            Dialogs.success('Ticket created successfully');
            onSuccess();
            onClose(); // Close the form after successful submission
        } catch (error) {
            Dialogs.error('Failed to create ticket. Please try again.');
        }
    };

    const handleCancel = () => {
        setSubject('');
        setDescription('');
        setFormErrors({ subject: '', description: '' });
        onClose();
    };

    return (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50">
            <div className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md mx-auto">
                <h2 className="text-2xl font-bold mb-4">New Ticket</h2>
                <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                        <label htmlFor="subject" className="block text-gray-800 font-semibold mb-2">
                            Subject
                        </label>
                        <input
                            type="text"
                            id="subject"
                            className="w-full border border-gray-300 p-2 rounded-lg"
                            placeholder="Enter the subject"
                            value={subject}
                            onChange={(e) => setSubject(e.target.value)}
                        />
                        {formErrors.subject && <p className="text-red-500 text-xs italic">{formErrors.subject}</p>}
                    </div>
                    <div>
                        <label htmlFor="description" className="block text-gray-800 font-semibold mb-2">
                            Description
                        </label>
                        <textarea
                            id="description"
                            rows={5}
                            className="w-full border border-gray-300 p-2 rounded-lg"
                            placeholder="Enter the description"
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />
                        {formErrors.description && <p className="text-red-500 text-xs italic">{formErrors.description}</p>}
                    </div>
                    <div className="flex justify-end">
                        <button
                            type="button"
                            onClick={handleCancel}
                            className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded mr-4"
                        >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        >
                            Create Ticket
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default NewTicketForm;
