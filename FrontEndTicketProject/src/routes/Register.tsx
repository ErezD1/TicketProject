import React from 'react';
import { useForm } from 'react-hook-form';
import InputField from '../components/InputField';
import { Dialogs } from '../ui/dialogs';
import { RegisterRequest } from '../@types/types';
import { useNavigate } from 'react-router-dom';
import { Auth } from '../services/auth-service'; // Ensure this path matches where your auth-service is located
import { DevTool } from '@hookform/devtools'; // Ensure this is imported correctly

const Register: React.FC = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { errors }, control } = useForm<RegisterRequest>();

  const onSubmit = async (data: RegisterRequest) => {
    try {
      const response = await Auth.register(data);
      // Handle success, e.g., navigate to login or show success message
      console.log("Registration successful:", response);
      navigate("/login");
    } catch (error) {
      // Handle error, e.g., show error message
      Dialogs.error(error.message || "Registration failed. Please try again.");
    }
  };


  return (
    <>
      <h1 className="text-center text-lg my-2">Sign up</h1>
      <form onSubmit={handleSubmit(onSubmit)} noValidate className="flex flex-col gap-5 w-full mx-auto shadow-xl rounded-xl p-5 text-lg">
        <InputField register={register} errors={errors} name="username" label="Username" />
        <InputField register={register} errors={errors} name="email" label="Email" />
        <InputField register={register} errors={errors} name="password" label="Password" type="password" />
        <button className="rounded-md bg-blue-500 text-white p-2" type="submit">Register</button>
      </form>

      {/* Render DevTool conditionally */}
      {process.env.NODE_ENV === 'development' && <DevTool control={control} />}
    </>
  );
};

export default Register;