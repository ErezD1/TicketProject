import axios, { AxiosError, AxiosRequestConfig } from "axios";
import { baseUrl } from "../services/auth-service";

export type HttpError401 = { status: number; message: string };

// Set the base URL and default headers just once
axios.defaults.baseURL = baseUrl; // Ensure `baseUrl` is correctly imported and set
axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem('authToken')}`;
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.put['Content-Type'] = 'application/json';

// Type guard for HTTP 401 error
export function isHttpError401(e: unknown): e is HttpError401 {
  return (
    e != null &&
    typeof e === "object" &&
    "status" in e &&
    "message" in e &&
    (e as { status: number }).status === 401
  );
}

// Type guard for any error with a message
export function isErrorWithMessage(e: unknown): e is { message: string } {
  return (
    e != null &&
    typeof e === "object" &&
    "message" in e &&
    typeof (e as { message: unknown }).message === "string"
  );
}

// Enhanced request function with consistent token usage and error handling
export const request = async (requestConfig: AxiosRequestConfig) => {
  const token = localStorage.getItem("authToken"); // Corrected the key used
  if (!token) {
    throw new Error("Authentication token is missing, user must be logged in");
  }

  // Setting the authorization header directly on the request config
  requestConfig.headers = {
    ...requestConfig.headers,
    Authorization: `Bearer ${token}`
  };

  try {
    return await axios(requestConfig);
  } catch (e) {
    if (axios.isAxiosError(e)) {
      if (e.response) {
        const status = e.response.status;
        const message = e.response.data?.message || e.message;
        throw { status, message, name: e.name, original: e };
      } else {
        throw { status: 500, message: "No response received", original: e };
      }
    } else if (isErrorWithMessage(e)) {
      throw { message: e.message, status: 500 };
    } else {
      throw { message: "An unknown error occurred", status: 500 };
    }
  }
};
