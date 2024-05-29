import { Navigate, RouteObject } from "react-router-dom";
import Root from "../layout/root/Root";
import ProtectedRoute from "../components/ProtectedRoute";
import Login from "./Login";
import Register from "./Register";
import Tickets from "./Tickets";
import Ticket from "./Ticket";
import About from "./about/About";
import ErrorPage from "./error/ErrorPage";
import PostIdError from "./error/TicketIdError";
import AdminConsole from "./adminConsole/AdminConsole";
import TicketTestComponent from "./test/TicketTestComponent"; // Import the TicketTestComponent

export const routes: RouteObject[] = [
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      { path: "login", element: <Login /> },
      { path: "register", element: <Register /> },
      { path: "about", element: <About /> },
      {
        path: "tickets",
        element: (
          <ProtectedRoute>
            <Tickets />
          </ProtectedRoute>
        ),
      },
      {
        path: "tickets/:id",
        element: (
          <ProtectedRoute>
            <Ticket />
          </ProtectedRoute>
        ),
        errorElement: <PostIdError />,
      },
      {
        path: "admin",
        element: (
          <ProtectedRoute>
            <AdminConsole />
          </ProtectedRoute>
        ),
      },
      {
        path: "ticket-test", // Add the route for TicketTestComponent
        element: (
          <ProtectedRoute>
            <TicketTestComponent />
          </ProtectedRoute>
        ),
      },
      { index: true, element: <Navigate to="/tickets" /> },
    ],
  },
];
