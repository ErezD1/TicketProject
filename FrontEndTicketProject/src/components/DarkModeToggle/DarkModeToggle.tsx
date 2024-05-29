import { useContext} from "react";
import { FaMoon, FaSun } from "react-icons/fa";
import styles from "./DarkModeToggle.module.scss";
import { ThemeContext } from "../../contexts/ThemeContext";
const DarkModeToggle = () => {
  const { isDark, toggleTheme } = useContext(ThemeContext);
  const theme = isDark ? "dark" : "light";
  return (
    <button
      className={`${styles["toggle-button"]} ${styles[theme]}`}
      onClick={toggleTheme}
    >
      {isDark ? <FaMoon /> : <FaSun />}
    </button>
  );
};

export default DarkModeToggle;
