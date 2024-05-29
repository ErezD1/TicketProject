import React from 'react';
import { FieldErrors, UseFormRegister, Path, RegisterOptions, FieldValues } from 'react-hook-form';

type InputFieldProps<T extends FieldValues> = {
    register: UseFormRegister<T>;
    errors: FieldErrors<T>;
    name: Path<T>;
    label?: string;
    type?: React.HTMLInputTypeAttribute;
    pattern?: RegisterOptions<T>[Path<T>]['pattern'];
} & React.InputHTMLAttributes<HTMLInputElement>;

const InputField: React.FC<InputFieldProps<any>> = ({ label, name, register, errors, type = 'text', pattern, ...rest }) => {
    return (
        <div className="relative p-2">
            {label && <label htmlFor={name} className="block text-sm font-medium text-gray-700">{label}</label>}
            <input
                id={name}
                type={type}
                {...register(name, { pattern })}
                {...rest}
                className={`mt-1 block w-full px-3 py-2 border rounded-md shadow-sm focus:outline-none ${errors[name] ? 'border-red-500 focus:border-red-600' : 'border-gray-300 focus:border-blue-400'}`}
            />
            {errors[name] && (
                <div className="absolute top-full mt-1 right-0 bg-red-100 border border-red-500 text-red-700 px-3 py-1 rounded-lg text-sm shadow-lg">
                    {errors[name].message}
                </div>
            )}
        </div>
    );
};

export default InputField;
