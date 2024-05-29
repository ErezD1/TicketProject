import { LoginRequest, RegisterRequest, JWTToken } from "../@types/types";
import { jwtDecode } from "jwt-decode"; // Corrected import

export const baseUrl = `http://localhost:8080/api/v1`;

export interface AuthResponse {
  token: string;
  username: string;
  roles?: string[]; // Include roles in the AuthResponse if applicable
}

async function register(body: RegisterRequest): Promise<any> {
  try {
    const response = await fetch(`${baseUrl}/auth/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });

    if (!response.ok) {
      const errorResponse = await response.json();
      console.error("Registration failed with response:", errorResponse);
      throw new Error(errorResponse.message || "Failed to register");
    }

    const json = await response.json();
    console.log("Registration successful", json);
    return json;
  } catch (error) {
    console.error("Registration error:", error);
    throw error;
  }
}

export async function login(body: LoginRequest): Promise<AuthResponse> {
  const response = await fetch(`${baseUrl}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  if (!response.ok) {
    const errorResponse = await response.json();
    console.error("Login failed with response:", errorResponse);
    throw new Error(errorResponse.message || "Failed to login");
  }

  const responseData = await response.json();
  const { jwt: token } = responseData;

  if (!token) {
    console.error("JWT token is missing in the login response.");
    throw new Error("JWT token is missing in login response");
  }

  const decodedToken: JWTToken = jwtDecode<JWTToken>(token);
  if (!decodedToken.sub || !decodedToken.scope) {
    console.error("Decoded JWT token is missing necessary claims:", decodedToken);
    throw new Error("JWT token is missing necessary claims");
  }

  const roles = decodedToken.scope.split(" ");
  localStorage.setItem("authToken", token);
  localStorage.setItem("username", decodedToken.sub);
  localStorage.setItem("roles", JSON.stringify(roles)); // Store roles as a JSON string

  console.log("Login successful, roles assigned:", roles);
  return { token, username: decodedToken.sub, roles };
}

function logout() {
  console.log("Logging out user.");
  localStorage.removeItem("authToken");
  localStorage.removeItem("username");
  localStorage.removeItem("roles"); // Remove roles from localStorage
}

function isAuthenticated() {
  const tokenString = localStorage.getItem("authToken");
  if (!tokenString) {
    console.log("No authToken found, user is not authenticated.");
    return false;
  }
  try {
    const tokenObj: JWTToken = jwtDecode<JWTToken>(tokenString);
    const isAuth = tokenObj.exp * 1000 > new Date().getTime();
    console.log("User is authenticated:", isAuth);
    return isAuth;
  } catch (error) {
    console.error("Error parsing authentication token:", error);
    return false;
  }
}

export const Auth = {
  register,
  login,
  isAuthenticated,
  logout,
};