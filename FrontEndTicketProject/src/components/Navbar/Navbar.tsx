import React from 'react';
import { NavLink } from "react-router-dom";
import { BsHouse } from "react-icons/bs";
import { AiFillSetting } from "react-icons/ai";  // Admin console icon
import DarkModeToggle from "../DarkModeToggle/DarkModeToggle";
import { useAuth } from '../../contexts/AuthContext'; // Adjust the path as necessary

const Navbar = () => {
  const { user, logout } = useAuth(); // Access the current user and logout function

  const handleLogout = async () => {
    await logout(); // Ensure logout logic is awaited, in case it's asynchronous
  };

  // Check if the user is an admin
  const isAdmin = user && user.roles && user.roles.includes('ROLE_ADMIN'); // Adjusted to check user and user.roles existence and corrected role string

  return (
    <nav className="flex flex-row justify-between bg-slate-100 text-xl text-slate-900 dark:bg-slate-700 dark:text-white shadow-lg mb-1 p-4">
      <div className="flex flex-row gap-2">
        <NavLink to="/">
          <BsHouse />
        </NavLink>
        <NavLink to="/tickets">Tickets</NavLink>
        <NavLink to="/about">About</NavLink>
        {isAdmin && <NavLink to="/admin"><AiFillSetting /></NavLink>}
      </div>
      <div className="flex flex-row gap-2">
        {user && user.username ? (
          <>
            <span>Welcome, {user.username}</span>
            <button onClick={handleLogout}>Logout</button>
            {user.roles && !user.roles.includes('ROLE_ADMIN') && <NavLink to="/register">Register</NavLink>}
          </>
        ) : (
          <>
            <NavLink to="/login">Login</NavLink>
            <NavLink to="/register">Register</NavLink>
          </>
        )}
        <DarkModeToggle />
      </div>
    </nav>
  );
};

export default Navbar;
