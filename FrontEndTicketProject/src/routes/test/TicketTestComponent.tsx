import React, { useState } from 'react';
import axios from 'axios';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

interface TicketCreateDTO {
    subject: string;
    description: string;
}

interface TestResult {
    name: string;
    status: string;
    response: any;
}

const TicketTestComponent: React.FC = () => {
    const initialValues: TicketCreateDTO = {
        subject: '',
        description: '',
    };

    const validationSchema = Yup.object({
        subject: Yup.string()
            .min(2, 'Subject must be between 2 and 128 characters')
            .max(128, 'Subject must be between 2 and 128 characters')
            .required('Subject is mandatory and must be between 2 and 128 characters'),
        description: Yup.string()
            .min(2, 'Description must be between 2 and 2147483647 characters')
            .required('Description is mandatory and must be between 2 and 2147483647 characters'),
    });

    const [testResults, setTestResults] = useState<TestResult[]>([]);

    const runTest = async (testName: string, config: any) => {
        try {
            const response = await axios(config);
            setTestResults((prev) => [
                ...prev,
                { name: testName, status: 'Passed', response: response.data },
            ]);
        } catch (error: any) {
            setTestResults((prev) => [
                ...prev,
                {
                    name: testName,
                    status: 'Failed',
                    response: error.response ? error.response.data : 'No response',
                },
            ]);
        }
    };

    const runAllTests = () => {
        const token = localStorage.getItem("authToken");
        if (!token) {
            alert("No authentication token available");
            return;
        }

        setTestResults([]);
        runTest('Create Ticket Success', {
            method: 'post',
            url: 'http://localhost:8080/api/v1/tickets',
            headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            data: { subject: 'Test Ticket', description: 'Test Description' },
        });

        runTest('Create Ticket BadRequest', {
            method: 'post',
            url: 'http://localhost:8080/api/v1/tickets',
            headers: {
                Authorization: `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            data: { subject: '', description: '' },
        });

        runTest('Create Ticket Missing JWT', {
            method: 'post',
            url: 'http://localhost:8080/api/v1/tickets',
            headers: { 'Content-Type': 'application/json' },
            data: { subject: 'Test Ticket', description: 'Test Description' },
        });

        runTest('Create Ticket Invalid JWT', {
            method: 'post',
            url: 'http://localhost:8080/api/v1/tickets',
            headers: {
                Authorization: 'Bearer invalid_token',
                'Content-Type': 'application/json',
            },
            data: { subject: 'Test Ticket', description: 'Test Description' },
        });

        // Add more tests as necessary
    };

    return (
        <div className="max-w-4xl mx-auto p-6 bg-white shadow-md rounded-lg">
            <h1 className="text-2xl font-bold mb-4 text-center">Ticket Creation Tests</h1>
            <button
                onClick={runAllTests}
                className="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4"
            >
                Run All Tests
            </button>
            <ul className="space-y-2">
                {testResults.map((result, index) => (
                    <li key={index} className="border p-4 rounded bg-gray-100">
                        <strong>{result.name}:</strong> {result.status}
                        <pre className="bg-gray-200 p-2 rounded mt-2 overflow-auto">
                            {JSON.stringify(result.response, null, 2)}
                        </pre>
                    </li>
                ))}
            </ul>
            <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={(values, { setSubmitting }) => {
                    const token = localStorage.getItem("authToken");
                    if (!token) {
                        alert("No authentication token available");
                        setSubmitting(false);
                        return;
                    }

                    runTest('Manual Create Ticket', {
                        method: 'post',
                        url: 'http://localhost:8080/api/v1/tickets',
                        headers: {
                            Authorization: `Bearer ${token}`,
                            'Content-Type': 'application/json',
                        },
                        data: values,
                    });
                    setSubmitting(false);
                }}
            >
                {() => (
                    <Form className="space-y-4">
                        <div>
                            <label htmlFor="subject" className="block text-sm font-medium text-gray-700">
                                Subject
                            </label>
                            <Field
                                type="text"
                                id="subject"
                                name="subject"
                                className="mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
                            />
                            <ErrorMessage name="subject" component="div" className="text-red-500 text-sm mt-1" />
                        </div>
                        <div>
                            <label htmlFor="description" className="block text-sm font-medium text-gray-700">
                                Description
                            </label>
                            <Field
                                as="textarea"
                                id="description"
                                name="description"
                                rows="4"
                                className="mt-1 block w-full p-2 border border-gray-300 rounded shadow-sm focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
                            />
                            <ErrorMessage name="description" component="div" className="text-red-500 text-sm mt-1" />
                        </div>
                        <button
                            type="submit"
                            className="w-full bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                        >
                            Create Ticket
                        </button>
                    </Form>
                )}
            </Formik>
        </div>
    );
};

export default TicketTestComponent;
