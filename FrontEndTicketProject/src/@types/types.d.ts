import { ReactNode } from 'react';

// Common functional component definition used by both systems
export type FC = (props: { children: ReactNode }) => ReactNode;

// Theme interface common to both systems
export interface Theme {
  isDark: boolean;
  toggleTheme: () => void;
}

export interface UserCreateDTO {
  username: string;
  password: string;
  email: string;
  roles: string[];
}

export interface UserResponseDTO {
  id: number;
  username: string;
  email: string;
  roles: string[];
}

export interface FormData {
  id: number | null;
  username: string;
  password: string;
  email: string;
  roles: string[];
}

export interface JWTToken {
  sub: string; // Username
  exp: number;
  iat: number;
  scope: string; // Role information under "scope"
}

// User definitions slightly differ but are compatible
export interface User {
  id?: number; // Optional for compatibility with the ticketing system where only username is used
  username: string;
  email?: string; // Email is specific to the posts system and optional here for compatibility
  token?: string; // Specific to the ticketing system for authentication purposes
  roles?: string[]; // Specific to the ticketing system for authentication purposes
}

// Post-related types for the posting system
export type PostsPage = {
  totalPosts: number;
  pageNo: number;
  pageSize: number;
  totalPages: number;
  posts: Array<Post>;
};

export type Post = {
  id: number;
  title: string;
  content: string;
  description: string;
  createdAt: string;
  comments: Array<Comment>; // Note: In the ticketing system, comments are structured differently
};

// Ticket-related types for the ticketing system
export interface AuthContextType {
  user: User | null;
  login: (data: LoginRequest) => Promise<void>;
  logout: () => void;
  isAuthenticated: () => boolean;
}

export interface TicketType {
  id: number | string;
  subject: string;
  description: string;
  status: string;
  closingComment?: string;
  createdAt: string;
  updatedAt?: string;
  username: string; // Directly included in the ticketing system, differing from the posts system where User is a separate object
  comments?: Array<TicketComment>; // Adjusted to match the structure in the ticketing system
  lastUpdatedBy?: string;
}

// Adjusting the Comment type to fit the ticketing system, and comparing to the posts system
export type TicketComment = {
  id: number;
  content: string; // 'comment' in the posts system is 'content' here
  createdAt: string;
  updatedAt?: string;
  username: string; // Direct inclusion of 'username' here differs from the 'user' object in the posts system
};

// LoginRequest type specific to the ticketing system's authentication needs
export interface LoginRequest {
  username: string;
  password: string;
}

export type RegisterRequest = {
  username: string;
  email: string;
  password: string;
};

declare module 'jwt-decode' {
  function jwtDecode<T = any>(token: string): T;
  export default jwtDecode;
}

/* Additional types, such as those for handling requests and responses,
   should be defined here if they are common to both systems or if
   there are equivalent needs that can be aligned. */
