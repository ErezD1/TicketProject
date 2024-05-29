import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import { Auth, AuthResponse } from '../services/auth-service';
import { jwtDecode } from "jwt-decode"; // Corrected import

interface User {
  username: string;
  token: string;
  roles?: string[];
}

interface JWTToken {
  sub: string;  // Username
  exp: number;  // Expiry time
  iat: number;  // Issued at
  scope?: string;  // Optional scope or roles
}


interface AuthContextType {
  user: User | null;
  isLoading: boolean;
  login: (data: { username: string; password: string }) => Promise<void>;
  logout: () => void;
  isAuthenticated: () => boolean;
}

export const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);
  const [isLoading, setIsLoading] = useState(true);  // Track loading state

  useEffect(() => {
    rehydrateUser();
  }, []);

  const setUserState = (username: string, token: string, decodedToken: JWTToken) => {
    if (!decodedToken.sub || !decodedToken.exp || !decodedToken.iat) {
      throw new Error("JWT token is missing necessary claims");
    }
    setUser({
      username: decodedToken.sub,  // Assuming sub is the username
      token,
      sub: decodedToken.sub,
      exp: decodedToken.exp,
      iat: decodedToken.iat,
      roles: decodedToken.scope ? decodedToken.scope.split(" ") : []
    });
  };

  
const rehydrateUser = () => {
  const token = localStorage.getItem('authToken');
  if (token) {
    try {
      const decodedToken: JWTToken = jwtDecode(token);
      if (decodedToken.exp * 1000 > new Date().getTime()) {
        setUserState(localStorage.getItem('username') || '', token, decodedToken);
        setLogoutTimer(decodedToken.exp * 1000 - new Date().getTime());
      } else {
        logout();
      }
    } catch (error) {
      console.error('Failed to decode token:', error);
      logout();  // Log out the user if the token is invalid
    }
  }
  setIsLoading(false);
}

  const setLogoutTimer = (delay: number) => {
    setTimeout(() => {
      logout();
    }, delay);
  };


  const login = async (data: { username: string; password: string }) => {
    try {
      const authResponse = await Auth.login(data);
      const decodedToken: JWTToken = jwtDecode(authResponse.token);
      setUserState(data.username, authResponse.token, decodedToken);
      localStorage.setItem('authToken', authResponse.token);
      localStorage.setItem('username', data.username);
      setLogoutTimer(decodedToken.exp * 1000 - new Date().getTime());
    } catch (error) {
      console.error('Login failed:', error);
      throw error;
    } finally {
      setIsLoading(false);
    }
  };

  const logout = () => {
    clearTimeout(setLogoutTimer as unknown as number);  // Clear the logout timer
    localStorage.removeItem('authToken');
    localStorage.removeItem('username');
    setUser(null);
  };

  const isAuthenticated = () => !!user;

  return (
    <AuthContext.Provider value={{ user, isLoading, login, logout, isAuthenticated }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
