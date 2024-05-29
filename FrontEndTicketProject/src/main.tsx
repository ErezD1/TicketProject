import ReactDOM from "react-dom/client";
import "./index.css";

import { RouterProvider, createBrowserRouter } from "react-router-dom";
import { routes } from "./routes";
import { ThemeProvider } from "./contexts/ThemeContext";
import { AuthProvider } from "./contexts/AuthContext"; // Import AuthProvider

const router = createBrowserRouter(routes);

const div = document.getElementById("root")!;

ReactDOM.createRoot(div).render(
  <>
    <ThemeProvider>
      <AuthProvider> {/* Wrap RouterProvider with AuthProvider */}
        <RouterProvider router={router} />
      </AuthProvider>
    </ThemeProvider>
  </>
);
