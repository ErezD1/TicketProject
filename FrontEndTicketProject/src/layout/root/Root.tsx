import { Outlet } from "react-router-dom";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import Navbar from "../../components/Navbar/Navbar";

const Root: React.FC = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <Navbar />
      <Header />
      <main className="flex-1">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default Root;
