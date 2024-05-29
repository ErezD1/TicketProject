import axios, { AxiosError } from "axios";
import { UserCreateDTO, UserResponseDTO } from "../@types/types";

const API_URL = "http://localhost:8080/api/v1/users";

class AdminService {
    private static headers = {
        Accept: "application/json",
        Authorization: `Bearer ${localStorage.getItem("authToken")}`,
    };

    private handleError(error: AxiosError): never {
        if (error.response) {
            const message = error.response.data?.message || "An error occurred.";
            console.error(`HTTP error: ${error.response.status}`, error.response.data);
            throw new Error(`Server responded with error: ${message}`);
        } else if (error.request) {
            console.error("No response received:", error.request);
            throw new Error("No response received from the server.");
        } else {
            console.error("Request setup error:", error.message);
            throw new Error("Error setting up the request.");
        }
    }

    async createUser(user: UserCreateDTO): Promise<UserResponseDTO> {
        try {
            const response = await axios.post<UserResponseDTO>(
                `${API_URL}/create`,
                user,
                { headers: AdminService.headers }
            );
            return response.data;
        } catch (error) {
            if (axios.isAxiosError(error)) this.handleError(error);
            throw new Error("Network error, please try again later.");
        }
    }

    async getUserById(id: number): Promise<UserResponseDTO> {
        try {
            const response = await axios.get<UserResponseDTO>(`${API_URL}/${id}`, {
                headers: AdminService.headers,
            });
            //console.log("User fetched successfully:", response.data);
            return response.data;
        } catch (error) {
            if (axios.isAxiosError(error)) this.handleError(error);
            throw new Error("Failed to fetch user.");
        }
    }

    async updateUser(id: number, user: UserCreateDTO): Promise<UserResponseDTO> {
        try {
            const response = await axios.put<UserResponseDTO>(
                `${API_URL}/${id}`,
                user,
                { headers: AdminService.headers }
            );
            return response.data;
        } catch (error) {
            if (axios.isAxiosError(error)) this.handleError(error);
            throw new Error("Failed to update user.");
        }
    }

    async deleteUserById(id: number): Promise<void> {
        try {
            await axios.delete<void>(`${API_URL}/${id}`, {
                headers: AdminService.headers,
            });
            //console.log("User deleted successfully.");
        } catch (error) {
            if (axios.isAxiosError(error)) this.handleError(error);
            throw new Error("Failed to delete user.");
        }
    }

    async getAllUsers(): Promise<UserResponseDTO[]> {
        try {
            const response = await axios.get<{ users: UserResponseDTO[] }>(
                `${API_URL}/all`,
                {
                    headers: AdminService.headers,
                }
            );
            //console.log("Users fetched successfully:", response.data);

            // Log the raw roles data from the response
            response.data.users.forEach(user => {
                //console.log(`User: ${user.username}, Roles: ${user.roles}`);
            });

            // Directly return the users with their roles as provided by the backend
            return response.data.users;
        } catch (error) {
            if (axios.isAxiosError(error)) this.handleError(error);
            throw new Error("Failed to fetch users.");
        }
    }
}

export default new AdminService();
