import React, { useState, useEffect } from 'react';
import { Tick } from '../services/ticket-service';
import Ticket from './Ticket';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import { Dialogs } from '../ui/dialogs';
import NewTicketForm from './NewTicketForm'; // Adjust the path as necessary

const Tickets: React.FC = () => {
  const [tickets, setTickets] = useState([]);
  const [showNewTicketForm, setShowNewTicketForm] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [pageSize, setPageSize] = useState(2);
  const [sortOrder, setSortOrder] = useState('desc');

  const navigate = useNavigate();
  const auth = useAuth();
  const userRole = auth.user?.roles[0]; // Assuming roles is an array and we take the first role.

  const fetchTickets = async (page: number, size: number, order: string) => {
    if (!auth.isAuthenticated()) {
      navigate("/login");
      return;
    }

    try {
      const response = await Tick.getTickets(size, page, order);
      if (response && response.tickets) { // Adjusted to match backend response
        setTickets(response.tickets);
        setTotalPages(response.totalPages);
      } else {
        console.warn("Expected response to contain tickets and pagination info.");
      }
    } catch (error) {
      Dialogs.error('Failed to fetch tickets:', error);
    }
  };

  useEffect(() => {
    fetchTickets(currentPage, pageSize, sortOrder);
  }, [currentPage, pageSize, sortOrder]);

  const handlePageChange = (newPage: number) => {
    setCurrentPage(newPage);
  };

  return (
    <div className="container mx-auto py-8 px-4 sm:px-6 lg:px-8">
      <div className="mb-4 flex flex-col sm:flex-row justify-between items-center">
        <div className="flex items-center space-x-2 mb-4 sm:mb-0">
          <label htmlFor="sortOrder" className="font-medium">Sort Order:</label>
          <select
            id="sortOrder"
            value={sortOrder}
            onChange={(e) => setSortOrder(e.target.value)}
            className="rounded-md border-gray-300 shadow-sm p-2"
          >
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
          </select>
        </div>
        <div className="flex items-center space-x-2">
          <label htmlFor="pageSize" className="font-medium">Tickets per Page:</label>
          <select
            id="pageSize"
            value={pageSize}
            onChange={(e) => setPageSize(Number(e.target.value))}
            className="rounded-md border-gray-300 shadow-sm p-2"
          >
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="6">6</option>
            <option value="12">12</option>
            <option value="24">24</option>
          </select>
        </div>
        <button
          onClick={() => setShowNewTicketForm(!showNewTicketForm)}
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          {showNewTicketForm ? 'Cancel' : 'Add New Ticket'}
        </button>
      </div>

      {showNewTicketForm && <NewTicketForm onSuccess={() => fetchTickets(currentPage, pageSize, sortOrder)} onClose={() => setShowNewTicketForm(false)} />}

      <div className="flex justify-between items-center mt-4 px-4 py-3 bg-white border-b border-gray-200 sm:px-6">
        <button
          onClick={() => handlePageChange(Math.max(0, currentPage - 1))}
          disabled={currentPage === 0}
          className={`py-2 px-4 rounded font-bold ${currentPage === 0 ? 'bg-gray-500 text-white' : 'bg-blue-500 hover:bg-blue-700 text-white'}`}
        >
          Previous
        </button>
        <div>
          <span>Page </span>
          <input
            type="number"
            className="w-12 text-center border-gray-300 shadow-sm rounded-md p-2"
            value={currentPage + 1}
            onChange={(e) => handlePageChange(Math.max(0, Math.min(Number(e.target.value) - 1, totalPages - 1)))}
            min="1"
            max={totalPages}
          />
          <span> of {totalPages}</span>
        </div>
        <button
          onClick={() => handlePageChange(Math.min(totalPages - 1, currentPage + 1))}
          disabled={currentPage >= totalPages - 1}
          className={`py-2 px-4 rounded font-bold ${currentPage >= totalPages - 1 ? 'bg-gray-500 text-white' : 'bg-blue-500 hover:bg-blue-700 text-white'}`}
        >
          Next
        </button>
      </div>

      <div className={`grid gap-4 ${pageSize === 1 ? 'grid-cols-1' : pageSize === 2 ? 'grid-cols-2' : 'grid-cols-3'}`}>
        {tickets.length > 0 ? tickets.map((ticket, index) => (
          <Ticket key={index} ticket={ticket} onTicketUpdate={() => fetchTickets(currentPage, pageSize, sortOrder)} userRole={userRole} />
        )) : (
          <div className="col-span-full text-center">No tickets found.</div>
        )}
      </div>
    </div>
  );
};

export default Tickets;
