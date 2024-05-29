
export const baseUrl = `http://localhost:8080/api/v1`;

async function getTickets(pageSize = 5, currentPage = 0, sortOrder = "desc") {
    const token = localStorage.getItem("authToken");
    if (!token) throw new Error("No authentication token available");

    const url = new URL(`${baseUrl}/tickets`);
    url.searchParams.append("pageSize", pageSize);
    url.searchParams.append("pageNo", currentPage);
    url.searchParams.append("sortDir", sortOrder);

    try {
        const response = await fetch(url.toString(), {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
        });

        if (!response.ok) {
            const errorBody = await response.json();
            throw new Error(`Failed to get tickets: ${response.statusText} - ${errorBody.message}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching tickets:", error);
        throw error;
    }
}

async function editTicket(id: number, updatedTicket: { subject: string; description: string; status: string; closingComment: string }): Promise<void> {
    const token = localStorage.getItem("authToken");
    if (!token) throw new Error("No authentication token available");

    try {
        const response = await fetch(`${baseUrl}/tickets/${id}/update`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(updatedTicket),
        });

        if (!response.ok) {
            const errorBody = await response.text();
            throw new Error(`Failed to update ticket with ID ${id}: ${errorBody}`);
        }

        console.log("Ticket updated successfully.");
    } catch (error) {
        console.error("Error updating ticket:", error);
        throw error;
    }
}

async function editTicketStatus(id: number, status: string): Promise<void> {
	try {
		const tokenString = localStorage.getItem("authToken");
		if (!tokenString) {
			throw new Error("No authentication token available");
		}

		const { value: token } = JSON.parse(tokenString);

		const response = await fetch(`${baseUrl}/tickets/${id}/status`, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${token}`,
			},
			body: JSON.stringify({ status }),
		});

		if (!response.ok) {
			throw new Error(`Failed to update status of ticket with ID ${id}`);
		}

		//console.log(`Status of ticket with ID ${id} updated successfully.`);
	} catch (error) {
		console.error("Error updating ticket status:", error);
		throw error;
	}
}

async function createTicket(ticketData: { subject: string; description: string }): Promise<any> {
    try {
        const token = localStorage.getItem("authToken");
        if (!token) throw new Error("Authentication required");

        const response = await fetch(`${baseUrl}/tickets`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify(ticketData),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Could not create ticket");
        }

        return await response.json();
    } catch (error) {
        console.error("Create ticket error:", error);
        throw error;
    }
}

async function editTicketClosingComment(id: number, closingComment: string): Promise<any> {
    const tokenString = localStorage.getItem("authToken");
    const headers = {
        "Content-Type": "application/json",
        Authorization: `Bearer ${tokenString}`,
    };

    const body = JSON.stringify({ closingComment });

    try {
        const response = await fetch(`${baseUrl}/tickets/${id}/close`, {
            method: "PUT",
            headers: headers,
            body: body
        });

        if (!response.ok) {
            const errorBody = await response.json();
            throw new Error(`Failed to close ticket with ID ${id}: ${errorBody.message}`);
        }

        return await response.json();
    } catch (error) {
        console.error("Error closing ticket:", error);
        throw error;
    }
}


async function editTicketOpeningComment(
	id: number,
	closingComment: string
): Promise<void> {
	try {
		const tokenString = localStorage.getItem("authToken");
		if (!tokenString) {
			throw new Error("No authentication token available");
		}

		const response = await fetch(`${baseUrl}/tickets/${id}/open`, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${tokenString}`,
			},
			body: JSON.stringify({closingComment}),
		});

		if (!response.ok) {
			const errorBody = await response.json();
			throw new Error(
				errorBody.message || `Failed to close ticket with ID ${id}`
			);
		}
	} catch (error) {
		console.error("Error closing ticket:", error);
		throw error; // This will now contain the more descriptive error message
	}
}


export const Tick = {
	getTickets,
	editTicket,
	createTicket,
	editTicketClosingComment,
	editTicketOpeningComment,
	editTicketStatus,
};