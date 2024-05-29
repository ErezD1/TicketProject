import React, { useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";
import { Dialogs } from "../ui/dialogs";
import { TicketType, TicketComment } from "../@types/types";
import { Tick } from "../services/ticket-service";
import { Comm } from "../services/comment-service";
import Comment from "./Comment";
interface Props {
  ticket: TicketType;
  onTicketUpdate: () => Promise<void>;
  userRole: string;
}

const Ticket: React.FC<Props> = ({ ticket, onTicketUpdate, userRole }) => {
  const [editMode, setEditMode] = useState(false);
  const [editedTicket, setEditedTicket] = useState<Omit<TicketType, "id" | "createdAt" | "updatedAt" | "username" | "comments">>({
    subject: ticket.subject,
    description: ticket.description,
    status: ticket.status || "UPDATE", // Ensure status is not null and default to "UPDATE"
    closingComment: ticket.closingComment || "",
  });

  const [newComment, setNewComment] = useState("");
  const [comments, setComments] = useState<TicketComment[]>(ticket.comments || []);

  useEffect(() => {
    setEditedTicket({
      subject: ticket.subject,
      description: ticket.description,
      status: ticket.status || "UPDATE", // Ensure status is not null
      closingComment: ticket.closingComment || "",
    });
  }, [ticket]);

  const handleEditChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setEditedTicket(prev => ({ ...prev, [name]: value }));
  };

  const handleStatusChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setEditedTicket(prev => ({ ...prev, status: e.target.value }));
  };

  const handleAddComment = async () => {
    if (!newComment.trim()) {
      Dialogs.error("Comment cannot be empty.");
      return;
    }
    try {
      const response = await Comm.addComment(ticket.id, { content: newComment });
      if (response && response.id) {
        setComments(prev => [...prev, response]);
        setNewComment("");
        Dialogs.success("Comment added successfully");
      } else {
        throw new Error("Failed to add comment due to invalid response data.");
      }
    } catch (error) {
      console.error("Failed to add comment:", error);
      Dialogs.error("Failed to add comment. Please try again.");
    }
  };

  const saveEdits = async () => {
    try {
      const updateData = {
        subject: editedTicket.subject,
        description: editedTicket.description,
        status: editedTicket.status,
        closingComment: editedTicket.closingComment,
      };

      if (userRole !== "ROLE_ADMIN") {
        // For regular users, don't change the status
        updateData["status"] = ticket.status || "OPEN";
        await Tick.editTicket(ticket.id, updateData);
      } else {
        // Admin specific actions
        if (editedTicket.status === "CLOSE") {
          await Tick.editTicketClosingComment(ticket.id, editedTicket.closingComment);
        } else if (editedTicket.status === "OPEN") {
          await Tick.editTicketOpeningComment(ticket.id, editedTicket.closingComment);
        } else {
          await Tick.editTicket(ticket.id, { ...updateData, status: editedTicket.status });
        }
      }

      setEditMode(false);
      Dialogs.success("Ticket updated successfully!");
      await onTicketUpdate(); // Ensure the component re-renders by re-fetching ticket data
    } catch (error) {
      console.error("Failed to save ticket edits:", error);
      Dialogs.error("Failed to update ticket. Please try again.");
    }
  };

  const updateCommentContent = (commentId: number, updatedComment: TicketComment) => {
    setComments(currentComments => {
      if (updatedComment.content === "") {
        return currentComments.filter(comment => comment.id !== commentId);
      } else {
        return currentComments.map(comment => comment.id === commentId ? updatedComment : comment);
      }
    });
  };

  const formatDate = (dateString: string | number | Date | undefined) => {
    if (!dateString) return 'N/A'; // Handle undefined case
    const options = { year: "numeric", month: "long", day: "numeric", hour: "2-digit", minute: "2-digit", second: "2-digit", hour12: false };
    return new Date(dateString).toLocaleDateString("en-US", options);
  };

  const token = localStorage.getItem("authToken");
  let decodedToken: any = null;
  if (token) {
    decodedToken = jwtDecode(token);
  }

  const canEdit = userRole === "ROLE_ADMIN" || (decodedToken && decodedToken.sub === ticket.username);

  return (
    <div className="bg-white shadow rounded-lg my-4">
      <div className="p-6 border-b">
        <div className="flex justify-between items-center mb-4">
          <div>
            <h1 className="text-xl font-semibold">Ticket ID: {ticket.id} - {ticket.subject}</h1>
            <p className="text-gray-500">Created At: {formatDate(ticket.createdAt)}</p>
            <p className="text-gray-700">Created By: {ticket.username}</p>
          </div>
          <span
            className={`px-3 py-1 text-xs font-semibold rounded-full ${ticket.status === "OPEN" ? "bg-green-200 text-green-900" :
              ticket.status === "IN_PROGRESS" ? "bg-yellow-200 text-yellow-900" :
                ticket.status === "CLOSED" ? "bg-red-200 text-red-900" :
                  "bg-gray-200 text-gray-900"
              }`}
          >
            {ticket.status ? ticket.status.replace('_', ' ') : 'N/A'}
          </span>
        </div>

        <div className="mb-6 border p-4 rounded">
          {editMode ? (
            <>
              <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700">Subject</label>
                <input
                  type="text"
                  value={editedTicket.subject}
                  onChange={handleEditChange}
                  name="subject"
                  className="mt-1 block w-full p-2 rounded border border-gray-300 focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700">Description</label>
                <textarea
                  name="description"
                  value={editedTicket.description}
                  onChange={handleEditChange}
                  className="mt-1 block w-full p-2 rounded border border-gray-300 focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
                  rows="4"
                />
              </div>
            </>
          ) : (
            <p className="text-gray-600">{ticket.description}</p>
          )}
        </div>

        {editMode && userRole === "ROLE_ADMIN" && (
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700">Status</label>
            <select
              value={editedTicket.status}
              onChange={handleStatusChange}
              name="status"
              className="mt-1 block w-full p-2 bg-gray-50 border border-gray-300 rounded focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
            >
              <option value="UPDATE">Update</option>
              <option value="CLOSE">Close</option>
              <option value="OPEN">Open</option>
            </select>

            {(editedTicket.status === "CLOSE" || editedTicket.status === "OPEN") && (
              <div className="mt-4">
                <label className="block text-sm font-medium text-gray-700">Closing Comment</label>
                <input
                  type="text"
                  name="closingComment"
                  value={editedTicket.closingComment}
                  onChange={handleEditChange}
                  className="mt-1 block w-full p-2 bg-gray-50 border border-gray-300 rounded focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
                  placeholder="Add a closing comment"
                />
              </div>
            )}
          </div>
        )}

        <div className="flex justify-between">
          <p className="text-gray-700">Updated At: {formatDate(ticket.updatedAt)}</p>
        </div>
        <div>
          <p className="text-gray-500">Last Updated By: {ticket.lastUpdatedBy}</p> {/* Add this line */}
        </div>

        <div className="mt-4">
          <h3 className="text-lg font-semibold text-gray-800">Closing Comment:</h3>
          {ticket.closingComment !== null && ticket.closingComment !== "" ? (
            <p className="text-gray-600">{ticket.closingComment}</p>
          ) : (
            <p className="text-gray-500 italic">No closing comment yet.</p>
          )}
        </div>

        {userRole !== "ROLE_ADMIN" && ticket.status === "CLOSED" && (
          <div className="mt-4 text-red-600 font-semibold">
            This ticket is closed and cannot be updated.
          </div>
        )}

        <div className="flex items-center justify-end mt-4 space-x-2">
          {canEdit && (
            <>
              {editMode ? (
                <>
                  <button
                    onClick={() => setEditMode(false)}
                    className="text-gray-700 bg-white hover:bg-gray-100 border border-gray-300 rounded py-2 px-4"
                  >
                    Cancel
                  </button>
                  <button
                    onClick={saveEdits}
                    className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded"
                  >
                    Save
                  </button>
                </>
              ) : (
                <>
                  <button
                    onClick={() => setEditMode(true)}
                    className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded"
                  >
                    Edit
                  </button>
                </>
              )}
            </>
          )}
        </div>
      </div>

      <div className="p-6">
        <h3 className="text-lg font-semibold text-gray-800">Comments:</h3>
        {comments.length > 0 ? (
          <ul className="mt-2">
            {comments.map(comment => (
              <Comment
                key={comment.id}
                comment={comment}
                ticketId={ticket.id}
                onCommentUpdate={(updatedComment) => updateCommentContent(comment.id, updatedComment)}
              />
            ))}
          </ul>
        ) : (
          <p className="text-gray-500 italic">No comments yet</p>
        )}
        <div className="mt-4">
          <textarea
            placeholder="Add a comment..."
            className="block w-full p-3 rounded border border-gray-300 focus:border-blue-500 focus:ring focus:ring-blue-500 focus:ring-opacity-50"
            value={newComment}
            onChange={e => setNewComment(e.target.value)}
          />
          <button
            onClick={handleAddComment}
            className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded mt-2"
          >
            Add Comment
          </button>
        </div>
      </div>
    </div>
  );
}

export default Ticket;