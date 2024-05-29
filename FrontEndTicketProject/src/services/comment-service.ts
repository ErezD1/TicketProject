import { TicketType, RegisterRequest, TicketComment } from "../@types/types";

export const baseUrl = `http://localhost:8080/api/v1`;



async function addComment(
	ticketId: number,
	newComment: Comment
): Promise<Comment> {
	try {
		const token = localStorage.getItem("authToken");
		if (!token) {
			throw new Error("No authentication token available");
		}

		const res = await fetch(`${baseUrl}/tickets/${ticketId}/comments`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: `Bearer ${token}`,
			},
			body: JSON.stringify(newComment),
		});

		if (!res.ok) {
			throw new Error(`Failed to add comment to ticket with ID ${ticketId}`);
		}

		const json = await res.json();
		return json as Comment;
	} catch (error) {
		console.error("Error adding comment:", error);
		throw error;
	}
}

async function updateComment(
	ticketId: number,
	commentId: number,
	content: string
): Promise<void> {
	const token = localStorage.getItem("authToken");
	if (!token) throw new Error("Authentication required");

	console.log(
		`Updating comment - Ticket ID: ${ticketId}, Comment ID: ${commentId}, Content: ${content}`
	);

	try {
		const response = await fetch(
			`${baseUrl}/tickets/${ticketId}/comments/${commentId}`,
			{
				method: "PUT",
				headers: {
					"Content-Type": "application/json",
					Authorization: `Bearer ${token}`,
				},
				body: JSON.stringify({ content }),
			}
		);

		if (!response.ok) {
			const errorBody = await response.json();
			throw new Error(
				errorBody.message || `Failed to update comment with ID ${commentId}`
			);
		}

		console.log("Comment updated successfully");
	} catch (error) {
		console.error("Error updating comment:", error);
		throw error;
	}
}

async function deleteComment(
	ticketId: number,
	commentId: number
): Promise<void> {
	const token = localStorage.getItem("authToken");
	if (!token) throw new Error("Authentication required");

	console.log(
		`Deleting comment - Ticket ID: ${ticketId}, Comment ID: ${commentId}`
	);

	try {
		const response = await fetch(
			`${baseUrl}/tickets/${ticketId}/comments/${commentId}`,
			{
				method: "DELETE",
				headers: {
					Authorization: `Bearer ${token}`,
				},
			}
		);

		if (!response.ok) {
			throw new Error(`Failed to delete comment with ID ${commentId}`);
		}

		console.log("Comment deleted successfully");
	} catch (error) {
		console.error("Error deleting comment:", error);
		throw error;
	}
}

export const Comm = {
	addComment,
	updateComment,
	deleteComment,
};