import React, { useState } from "react";
import { Comm } from "../services/comment-service";
import { TicketComment } from '../@types/types';
import { useAuth } from '../contexts/AuthContext';

interface Props {
  comment: TicketComment;
  ticketId: number;
  onCommentUpdate: (updatedComment: TicketComment) => void; // Function to trigger after update or delete
}

const Comment: React.FC<Props> = ({ comment, ticketId, onCommentUpdate }) => {
  const [editMode, setEditMode] = useState(false);
  const [content, setContent] = useState(comment.content);
  const [showConfirm, setShowConfirm] = useState(false);
  const auth = useAuth();
  const currentUser = auth.user;

  const handleUpdate = async () => {
    try {
      await Comm.updateComment(ticketId, comment.id, content);
      setEditMode(false);
      onCommentUpdate({ ...comment, content, updatedAt: new Date().toISOString() });  // Call the parent's update function to trigger re-render
    } catch (error) {
      console.error("Failed to update comment:", error);
    }
  };

  const handleDelete = async () => {
    try {
      await Comm.deleteComment(ticketId, comment.id);
      onCommentUpdate({ ...comment, content: "" });  // Call the parent's update function to trigger re-render
      setShowConfirm(false);
    } catch (error) {
      console.error("Failed to delete comment:", error);
    }
  };

  const formatDate = (dateString: string | number | Date | undefined) => {
    if (!dateString) return 'N/A';
    const options = { year: "numeric", month: "long", day: "numeric", hour: "2-digit", minute: "2-digit", second: "2-digit", hour12: false };
    return new Date(dateString).toLocaleDateString("en-US", options);
  };

  const canEditOrDelete = currentUser?.roles.includes('ROLE_ADMIN') || currentUser?.username === comment.username;

  return (
    <div className="bg-gray-100 rounded-md p-2 mb-2">
      {editMode ? (
        <textarea
          className="w-full border border-gray-300 p-2 rounded-lg"
          value={content}
          onChange={e => setContent(e.target.value)}
        />
      ) : (
        <p>{content}</p>
      )}
      <p className="text-sm text-gray-500">By {comment.username}</p>
      <p className="text-sm text-gray-500">Created At: {formatDate(comment.createdAt)}</p>
      <p className="text-sm text-gray-500">Updated At: {formatDate(comment.updatedAt)}</p>
      {canEditOrDelete && (
        <div className="flex justify-end space-x-2">
          {editMode ? (
            <>
              <button onClick={handleUpdate} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded">
                Save
              </button>
              <button onClick={() => setEditMode(false)} className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-1 px-2 rounded">
                Cancel
              </button>
            </>
          ) : (
            <>
              <button onClick={() => setEditMode(true)} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded">
                Edit
              </button>
              {showConfirm ? (
                <>
                  <button onClick={handleDelete} className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded">
                    Confirm Delete
                  </button>
                  <button onClick={() => setShowConfirm(false)} className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-1 px-2 rounded">
                    Cancel
                  </button>
                </>
              ) : (
                <button onClick={() => setShowConfirm(true)} className="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded">
                  Delete
                </button>
              )}
            </>
          )}
        </div>
      )}
    </div>
  );
};

export default Comment;
