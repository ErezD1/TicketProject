import { FC } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate

const About: FC = () => {
  const navigate = useNavigate(); // Initialize useNavigate

  const handleTestButtonClick = () => {
    navigate("/ticket-test"); // Navigate to the ticket-test route
  };

  return (
    <div className="flex flex-col items-center justify-center p-4 h-screen text-center">
      <h1 className="text-3xl font-bold mb-4">About Our Project</h1>
      
      <h2 className="text-xl font-semibold">Project Overview:</h2>
      <p className="mb-4">
        Our application is a sophisticated web solution, designed using the latest technologies including
        React, TypeScript, and TailwindCSS to ensure a seamless and responsive user experience. This
        project is structured to support both client and server-side functionalities, demonstrating our
        commitment to full-stack development capabilities Using CRUDE.
      </p>
      
      <h2 className="text-xl font-semibold">Core Features:</h2>
      <ul className="list-disc list-inside mb-4 text-left">
        <li><strong>Responsive Design:</strong> Our application adapts smoothly to various screen sizes,
        ensuring accessibility on desktops, tablets, and mobile devices.</li>
        <li><strong>Secure Authentication:</strong> We have implemented a secure login system, safeguarded
        by our custom ProtectedRoute components. This setup ensures that only authenticated users can
        access certain areas of the application, enhancing security and user management.</li>
        <li><strong>Dynamic Content Management:</strong> While we strive to include real-world content,
        our focus is on demonstrating dynamic content manipulation which includes viewing, adding, and
        editing data seamlessly within the application interface.</li>
        <li><strong>User-Centric Navigation:</strong> A comprehensive navigation system is integrated,
        featuring a responsive navbar and footer, guiding users through different sections with ease.</li>
      </ul>
      
      <h2 className="text-xl font-semibold">Technical Achievements:</h2>
      <ul className="list-disc list-inside mb-4 text-left">
        <li><strong>Optimized Load Times:</strong> Utilizing efficient loading states within components
        like ProtectedRoute to manage user authentication checks without sacrificing user experience.</li>
        <li><strong>Modular Architecture:</strong> Our codebase is organized into modules, making it easier
        to maintain and scale. This organization extends to our use of contexts and services to manage
        application state and business logic.</li>
      </ul>
      
      <h2 className="text-xl font-semibold">Documentation and Setup:</h2>
      <p>
        Complete setup and operational instructions are provided in our README.md, tailored to help new
        developers understand and contribute to the project effectively.
      </p>

      <button
        onClick={handleTestButtonClick}
        className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
      >
        Start Ticket Test
      </button>
    </div>
  );
};

export default About;
