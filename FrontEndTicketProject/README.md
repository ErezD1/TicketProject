<p align="center">
  <img src="https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/ec559a9f6bfd399b82bb44393651661b08aaf7ba/icons/folder-markdown-open.svg" width="100" alt="project-logo">
</p>
<p align="center">
    <h1 align="center">FRONTENDTICKETPROJECT</h1>
</p>
<p align="center">
    <em></em>
</p>
<p align="center">
	<!-- default option, no dependency badges. -->
</p>

<br><!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary><br>

- [📍 Overview](#-overview)
- [🧩 Features](#-features)
- [🗂️ Repository Structure](#️-repository-structure)
- [📦 Modules](#-modules)
- [🚀 Getting Started](#-getting-started)
  - [⚙️ Installation](#️-installation)
  - [🤖 Usage](#-usage)
  - [🧪 Tests](#-tests)
- [🛠 Project Roadmap](#-project-roadmap)
- [🤝 Contributing](#-contributing)
- [🎗 License](#-license)
- [🔗 Acknowledgments](#-acknowledgments)
</details>
<hr>

## 📍 Overview

The FrontEndTicketProject is a comprehensive and user-centric application that provides a platform for managing tickets, users, authentication, and error handling. The projects architecture consists of several components, routes, services, and utils that work together to create a seamless experience. The AdminConsole component manages user information, while the ErrorPage component handles error scenarios with grace and transparency. Other components include TicketIdError, Test, and About, each serving a specific purpose in the projects functionality. Additionally, the application utilizes Axios-helper for handling HTTP requests, providing a consistent and efficient way of retrieving data. Overall, FrontEndTicketProject is an innovative solution that provides a robust and reliable ticket management system.

---

## 🧩 Features

|    |   Feature         | Description |
|----|-------------------|---------------------------------------------------------------|
⚙️ Architecture | *Quick facts about the project's architecture.* | The project's architecture primarily consists of React and TypeScript as the frontend framework and scripting language, respectively. It utilizes various third-party libraries, including PostCSS, Autoprefixer, and Sass, for CSS preprocessing. Additionally, there are integration with package managers like Yarn and npm, along with TypeScript configuration files (tsconfig.json) to aid in the development process. |
🔩 Code Quality & Style | *Quick facts about the code quality and style.* | The project's codebase displays good maintainability and organization through proper naming conventions, consistent formatting, and adherence to ESLint configurations. There is a combination of TypeScript interfaces and type annotations throughout the codebase for improving code quality and reducing errors during development. However, there could be more extensive use of React Hooks instead of class components |
📄 Documentation | *Discuss the extent and quality of documentation.* | The project's documentation is somewhat comprehensive with ReadMe files in both English and French. The README files provide a general overview of the project, its features, and installation guides. However, there are limited technical details, and no additional resources such as API references or developer guides are offered. |
🔌 Integrations | *List key integrations and external dependencies.* | The project relies on several third-party libraries/dependencies: PostCSS, Autoprefixer, Sass, React-dom, React-hook-form, Formik, and others. There's also a mention of the package manager, Yarn, in the README files. While there are some external dependencies, the project's architecture still appears well-structured with internal utilities. |
🧩 Modularity & Reusability | *Discuss the modularity and reusability of the codebase.* | The codebase features a modular structure with individual components, utilities, and helpers. Most components are organized into folders with descriptive names, allowing for easy recognition and reuse. TypeScript interfaces and type annotations also enhance modularity by providing better organization and maintainability throughout the project. However, some loose dependencies within components could be better defined through more extensive use of React's higher-order components (HOC) or similar approaches. |

---

## 🗂️ Repository Structure

<details closed>

```sh
└── FrontEndTicketProject/
┣ 📂.idea
┃ ┣ 📂inspectionProfiles
┃ ┗ 📂libraries
┣ 📂public
┣ 📂src
┃ ┣ 📂@types
┃ ┃ ┗ 📜types.d.ts
┃ ┣ 📂assets
┃ ┃ ┗ 📜react.svg
┃ ┣ 📂components
┃ ┃ ┣ 📂DarkModeToggle
┃ ┃ ┃ ┣ 📜DarkModeToggle.module.scss
┃ ┃ ┃ ┗ 📜DarkModeToggle.tsx
┃ ┃ ┣ 📂Navbar
┃ ┃ ┃ ┗ 📜Navbar.tsx
┃ ┃ ┣ 📜Card.tsx
┃ ┃ ┣ 📜Footer.tsx
┃ ┃ ┣ 📜Header.tsx
┃ ┃ ┣ 📜InputField.tsx
┃ ┃ ┗ 📜ProtectedRoute.tsx
┃ ┣ 📂contexts
┃ ┃ ┣ 📜AuthContext.tsx
┃ ┃ ┗ 📜ThemeContext.tsx
┃ ┣ 📂layout
┃ ┃ ┗ 📂root
┃ ┃   ┗ 📜Root.tsx
┃ ┣ 📂routes
┃ ┃ ┣ 📂about
┃ ┃ ┃ ┗ 📜About.tsx
┃ ┃ ┣ 📂adminConsole
┃ ┃ ┃ ┣ 📜AdminConsole.tsx
┃ ┃ ┃ ┗ 📜UserFormModal.tsx
┃ ┃ ┣ 📂error
┃ ┃ ┃ ┣ 📜Error.module.scss
┃ ┃ ┃ ┣ 📜ErrorPage.tsx
┃ ┃ ┃ ┗ 📜TicketIdError.tsx
┃ ┃ ┣ 📂test
┃ ┃ ┃ ┗ 📜TicketTestComponent.tsx
┃ ┃ ┣ 📜Comment.tsx
┃ ┃ ┣ 📜index.tsx
┃ ┃ ┣ 📜Login.tsx
┃ ┃ ┣ 📜NewTicketForm.tsx
┃ ┃ ┣ 📜Register.tsx
┃ ┃ ┣ 📜Ticket.tsx
┃ ┃ ┗ 📜Tickets.tsx
┃ ┣ 📂services
┃ ┃ ┣ 📜admin-service.ts
┃ ┃ ┣ 📜auth-service.ts
┃ ┃ ┣ 📜comment-service.ts
┃ ┃ ┗ 📜ticket-service.ts
┃ ┣ 📂ui
┃ ┃ ┗ 📜dialogs.ts
┃ ┣ 📂utils
┃ ┃ ┗ 📜axios-helper.ts
┃ ┣ 📜index.css
┃ ┣ 📜main.tsx
┃ ┗ 📜vite-env.d.ts
┣ 📜.gitattributes
┣ 📜.gitignore
┣ 📜index.html
┣ 📜package.json
┣ 📜postcss.config.js
┣ 📜README.md
┣ 📜tailwind.config.js
┣ 📜tsconfig.json
┣ 📜tsconfig.node.json
┗ 📜vite.config.ts

```

</details>

---

## 📦 Modules

<details closed><summary>.</summary>

| File                                                                                                 | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| ---                                                                                                  | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| [index.html](https://github.com/ErezD1/FrontEndTicketProject/blob/master/index.html)                 | Achieves the primary function of serving as the entry point for the user interface of a ticket project, loading the root component through script inclusion.This code files purpose is to facilitate the rendering of the main application by providing the necessary scripts and links to the HTML document.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [package-lock.json](https://github.com/ErezD1/FrontEndTicketProject/blob/master/package-lock.json)   | Ticket displayThe card component displays the ticket information, including the ticket title, description, and labels.2. **Editing functionalityUsers can edit the ticket information by clicking on the card and modifying the relevant fields.3. **Dark mode toggleThe dark mode toggle is integrated into the card component, allowing users to easily switch between light and dark modes.4. **Responsive designThe card component is designed to be responsive, ensuring that the ticket information is easily readable and accessible on various devices.In summary, this code file plays a crucial role in providing a seamless user experience for managing tickets within the projects frontend. By effectively displaying and editing ticket information, while also incorporating dark mode toggle functionality and responsive design, this code file enhances the overall usability of the frontend ticket project. |
| [package.json](https://github.com/ErezD1/FrontEndTicketProject/blob/master/package.json)             | The file defines the configuration for the blog frontend project, including dependencies, scripts, and bundling configurations. It plays a crucial role in managing the applications structure and ensuring that all components are properly organized and functional. The script section sets up various commands for development, building, linting, and previewing, providing a streamlined process for developers to work on the project.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [postcss.config.js](https://github.com/ErezD1/FrontEndTicketProject/blob/master/postcss.config.js)   | The postcss.config.js file at the heart of this repository orchestrates crucial styling components for FrontEndTicketProject, granting a unified look and feel to the applications visual elements. Through the clever configuration of plugins, the file leverages the power of TailwindCSS and Autoprefixer, ensuring consistent prefixing and responsive design across all interface elements. This succinct configuration enables developers to focus on enhancing the applications functionality without worrying about presentation layer aesthetics.                                                                                                                                                                                                                                                                                                                                                                      |
| [tailwind.config.js](https://github.com/ErezD1/FrontEndTicketProject/blob/master/tailwind.config.js) | Class`, developers can easily switch between light and dark modes using a class, simplifying the user experience. The configuration file specifies which files to include in the build (via content) and defines customizable theme settings through extend. Finally, plugins are disabled by default.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [tsconfig.json](https://github.com/ErezD1/FrontEndTicketProject/blob/master/tsconfig.json)           | Organizes configuration for TypeScript compiler, determining how TypeScript code is transformed into JavaScript. Specifies settings for target version, modules, libs, and more to control compilation process. Allows for efficient development workflow by optimizing build process through bundle mode.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [tsconfig.node.json](https://github.com/ErezD1/FrontEndTicketProject/blob/master/tsconfig.node.json) | Organizes FrontEndTicketProject files for efficient development. The tsconfig.node.json file sets crucial configuration options, enabling composite compilation, skipping library checks, and using ESNext module resolution with a bundler-based module resolution. This optimizes development efficiency.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [vite.config.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/vite.config.ts)         | Architecturally, this Vite configuration file is at the top-level directory of a frontend project, serving as a foundation for various components and services that make up the applications UI and functionality. The file defines a single configuration setting for the `vite` plugin, which in turn enables the use of React in the project.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |

</details>

<details closed><summary>src</summary>

| File                                                                                           | Summary                                                                                                                                                                                                                                                                                                                                                                                               |
| ---                                                                                            | ---                                                                                                                                                                                                                                                                                                                                                                                                   |
| [index.css](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\index.css)         | This file contributes to the FrontEndTicketProjects architecture by defining the CSS styles for the entire application. It leverages Tailwind Utilities, components, and base styles to establish a consistent visual language across the project. By organizing rules in a structured manner, this file helps maintain a uniform look and feel for users interacting with the FrontEndTicketProject. |
| [main.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\main.tsx)           | Centralizes core functionalities for the FrontEndTicketProjects user interface through imports, provider chains, and component rendering, ensuring a seamless user experience.                                                                                                                                                                                                                        |
| [vite-env.d.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\vite-env.d.ts) | Defines references for Vite client components, connecting the front-end to the parent repositorys architecture.                                                                                                                                                                                                                                                                                       |

</details>

<details closed><summary>src.@types</summary>

| File                                                                                            | Summary                                                                                                                                                                                                                                                                                                                                                                                                        |
| ---                                                                                             | ---                                                                                                                                                                                                                                                                                                                                                                                                            |
| [types.d.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\@types\types.d.ts) | This file defines various types and interfaces for two interconnected systems-a posts system and a ticketing system. It provides the common functional component definition used by both systems, as well as type definitions for user, post, ticket, comment, and other objects. These definitions align with the parent repositorys architecture and facilitate smooth interactions between the two systems. |

</details>

<details closed><summary>src.components</summary>

| File                                                                                                                | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| ---                                                                                                                 | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| [Card.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\Card.tsx)                     | The `Card.tsx` file is a crucial component in the FrontEndTicketProject repository, serving as a flexible and customizable card layout for displaying information. By using TypeScript interfaces, the file imports and exports a functional component (FC) that can be easily extended or modified to fit various design requirements.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [Footer.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\Footer.tsx)                 | The Footer component serves as the foundation for creating an aesthetically pleasing and visually consistent user interface. By leveraging Tailwind CSS classes and styling guidelines, the codebase achieves a clean and organized design. This file path within the FrontEndTicketProject repository enables developers to create a cohesive visual identity across various pages by utilizing the predefined classes and configuration defined in the tailwind.config.js file.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [Header.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\Header.tsx)                 | The Header component is a crucial element in the FrontEndTicketProjects architecture, serving as the top section of the website. It provides a stylish and professional appearance through its use of tailwind CSS, with a slate-colored background and white text. The component exports a function that returns a header element with a simple design, making it easy to integrate into the overall layout.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [InputField.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\InputField.tsx)         | Label management: The InputField allows developers to easily manage labels for each input field, ensuring clear communication to users.2. Register functionality: This component utilizes React Hook Form's `UseFormRegister` feature to handle form registration and validation.3. Field errors management: It displays any error messages or feedback to users through the use of the `FieldErrors` component from React Hook Form.4. Customizable input types: InputField provides flexibility by allowing developers to specify input types such as text, checkboxes, radio buttons, and more, using the `type` prop.5. Pattern validation: This component allows developers to set custom pattern validation for each input field through the `pattern` prop, enhancing the overall user experience.6. Consistent styling: The `className` property ensures a consistent look and feel across all input fields within the FrontEndTicketProject repository. |
| [ProtectedRoute.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\ProtectedRoute.tsx) | ProtectedRoute is a crucial component in the FrontEndTicketProject, serving as a gateway to restricted pages. It employs the use of context to verify user authentication before granting access to designated areas. When the login process is underway, it displays a loading indicator. In all other situations, it directs unauthorized users to the login page. The component plays a vital role in maintaining the security and integrity of the projects sensitive content.                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |

</details>

<details closed><summary>src.components.DarkModeToggle</summary>

| File                                                                                                                                               | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| ---                                                                                                                                                | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| [DarkModeToggle.module.scss](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\DarkModeToggle\DarkModeToggle.module.scss) | DarkModeToggle ModuleControlled Toggling with Transitions=================================The DarkModeToggle module in the FrontEndTicketProject repository offers smooth transitions when switching between light and dark modes. With a straightforward implementation, it adds visual interest to the UI while maintaining ease of use.                                                                                                                                                                                                                                                                                                      |
| [DarkModeToggle.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\DarkModeToggle\DarkModeToggle.tsx)                 | Activates dark mode toggle button.The `DarkModeToggle` component is responsible for rendering a button that changes the theme of the application based on user preference. It uses the `useContext` hook from `react` to access the `ThemeContext` and retrieve the current theme state, as well as the `FaMoon` and `FaSun` icons from `react-icons/fa`. The component renders a button with a class name that includes the current theme, allowing the button to change appearance depending on the users preference. When clicked, the `toggleTheme` function is called to update the theme state and trigger the reload of the application. |

</details>

<details closed><summary>src.components.Navbar</summary>

| File                                                                                                       | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| ---                                                                                                        | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [Navbar.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\components\Navbar\Navbar.tsx) | Define the purpose and essential features of the `Navbar` component within the `TicketProject` repositorys architecture.The `Navbar` component is crucial to the projects user interface, serving as a centralized navigation point for users to access various routes and utilize important features like logging out and checking their role as an admin. It imports React components, utilizes AuthContext for authenticated functionality, and employs DarkModeToggle for user customization. |

</details>

<details closed><summary>src.contexts</summary>

| File                                                                                                          | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| ---                                                                                                           | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| [AuthContext.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\contexts\AuthContext.tsx)   | Auth Context FileThe AuthContext file in the frontend ticket project is a crucial component that enables user authentication and authorization. It utilizes the React context library to manage user sessions, allowing for efficient communication between components. The file defines an AuthContext interface, which represents the state of the user, including their username, token, and expiration time. The login and logout functions allow for seamless authentication and termination of sessions when needed. This component plays a vital role in ensuring secure and authorized access to app resources. |
| [ThemeContext.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\contexts\ThemeContext.tsx) | FileContext.tsx establishes a context for the theme of the application, providing an initial value based on local storage and offering a toggle function to change the theme upon user interaction. It defines a context with a state of isDark and a function to update that state, which in turn updates the document body class and local storage.                                                                                                                                                                                                                                                                   |

</details>

<details closed><summary>src.layout.root</summary>

| File                                                                                             | Summary                                                                                                                                                                                                                                                                                                        |
| ---                                                                                              | ---                                                                                                                                                                                                                                                                                                            |
| [Root.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\layout\root\Root.tsx) | Organizes Root layout component in FrontEndTicketProject repository by combining headers and footers with the main content area through Navbar and Outlet components from React-Router-Dom library. This structure helps to create an intuitive user interface and seamless navigation within the application. |

</details>

<details closed><summary>src.routes</summary>

| File                                                                                                          | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| ---                                                                                                           | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| [Comment.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\Comment.tsx)             | The updated comment component includes several enhancements to improve user experience. The following are the key modifications made:1. Format date and time:In the `formatDate` function, we added options to format the date and time in a more readable format. Now, the creation and update dates are displayed in a user-friendly manner.2. Add cancel button in edit mode:When the user is in edit mode, we've added a cancel button to allow them to cancel changes without saving. This feature provides a more intuitive user experience by allowing the user to easily revert back to the original content without having to manually input everything again.3. Improved confirmation message for deletion:We've updated the deletion functionality by adding a more informative confirmation message when the user clicks on the Confirm Delete button. This message clarifies that deleting the comment will remove it permanently and cannot be undone.4. Better button styling and placement:The buttons in the comment editor have been given a consistent look and feel by modifying their colors, shapes, and sizes. Additionally, we've improved the layout of the buttons to make them more accessible and easier to use.5. Improved user experience for editing and deleting:By adding a cancel button and making sure the delete functionality provides clear confirmation messages, weve enhanced the overall user experience for comment editing and deletion. These changes make it easier for users to navigate the application without feeling overwh |
| [index.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\index.tsx)                 | The index.tsx file is a crucial component of the FrontEndTicketProject repositorys architecture, serving as the primary routing mechanism for the application. It defines the various routes that users can take within the app and handles the appropriate display of each routes associated components. By importing various components like Login, Register, About, and Tickets, this file ensures seamless navigation throughout the app.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| [Login.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\Login.tsx)                 | Login.tsx is a crucial component in the FrontEndTicketProject repository, responsible for handling user authentication through login functionality. The file uses React hooks and context to utilize the login method from the AuthContext, which calls the auth service. It also includes a form for users to input their credentials and submit for verification. The summary highlights the main purpose of the code and the critical features involved, steering clear of technical implementation details.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [NewTicketForm.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\NewTicketForm.tsx) | NewTicketForm.tsxImportance Level: HighCreate a form to submit a new ticket, validating fields before submission and displaying errors if invalid. Handles submit and cancel events.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| [Register.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\Register.tsx)           | In this code file, the `Register` component is responsible for handling user registrations. It uses React hook form to handle form submissions and Tailwind CSS classes to style the UI. The component imports services from the parent repositorys `services` folder and utilizes the `useNavigate` function from `react-router-dom` to navigate users to the login page upon successful registration.The code achieves the purpose of allowing users to create an account by providing a simple form with input fields for username, email, and password. When the form is submitted, the `register` function from `useForm` hook is called, which in turn calls the `Auth.register()` function to perform the actual registration logic. The `onSubmit` function handles the response from the server and navigates users to the login page if the registration was successful.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| [Ticket.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\Ticket.tsx)               | Manage ticket details, including title, description, tags, and comments. Edit mode displays editing controls. Closing comment and updated at fields are available for edits. Cancel or save edits button present.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [Tickets.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\Tickets.tsx)             | Fetching tickets...                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |

</details>

<details closed><summary>src.routes.about</summary>

| File                                                                                                | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| ---                                                                                                 | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| [About.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\about\About.tsx) | The About component showcases the projects technical achievements, highlighting responsive design, secure authentication, dynamic content management, and user-centric navigation. It also provides a comprehensive overview of the applications architecture, including modular structure and efficient loading states. Complete setup and operational instructions are available in the README.md for new developers to understand and contribute effectively. |

</details>

<details closed><summary>src.routes.adminConsole</summary>

| File                                                                                                                       | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| ---                                                                                                                        | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| [AdminConsole.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\adminConsole\AdminConsole.tsx)   | The AdminConsole component in this repository is responsible for managing users information and details within the FrontEndTicketProject. The file contains React components that fetch users data from the AdminService, display them in a list with edit and delete options, and handle form data changes during editing. Additionally, the file includes an openModal function to open a modal window where new user creation or editing can occur, and closeModal to close the modal when needed. |
| [UserFormModal.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\adminConsole\UserFormModal.tsx) | Update/Create User=====================================Submit User Details for Update/Creation--------------------------------------Fill in user details and submit for update/creation. Select roles for user. Click Update' or Create to save changes. Cancel to close modal.                                                                                                                                                                                                                       |

</details>

<details closed><summary>src.routes.error</summary>

| File                                                                                                                | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| ---                                                                                                                 | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [Error.module.scss](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\error\Error.module.scss) | Error Module.In this file, a flexible display layout is created using Flexbox, with the h1 element centered and bolded for emphasis.                                                                                                                                                                                                                                                                                                                                                                          |
| [ErrorPage.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\error\ErrorPage.tsx)         | ErrorPage.tsx is a crucial component of the FrontEndTicketProjects architecture, handling error scenarios with grace and transparency. It leverages React Routers `useRouteError` hook to retrieve error information from the parent route and display it to users in an informative and empathetic manner. By implementing this critical feature, the ErrorPage component enhances user experience during errors, ensuring a smooth transition between different routes and maintaining project reliability. |
| [TicketIdError.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\error\TicketIdError.tsx) | The PostIdError component provides an error message for invalid post IDs in the Ticket Id Error page. It uses the `useRouteError` hook from `react-router-dom` to retrieve the error message and styles it with CSS from the `Error.module.scss` file. The component returns a simple error message with a heading, paragraph, and an icon.                                                                                                                                                                   |

</details>

<details closed><summary>src.routes.test</summary>

| File                                                                                                                           | Summary                                                                                                                                                                                                                                                                                                                                              |
| ---                                                                                                                            | ---                                                                                                                                                                                                                                                                                                                                                  |
| [TicketTestComponent.tsx](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\routes\test\TicketTestComponent.tsx) | Explore Ticket Creation FunctionalityTest Results:1. Create Ticket Success: Passed* Subject: Test Ticket* Description: Test Description2. Create Ticket BadRequest: Failed* Subject:, Description:3. Create Ticket Missing JWT: Failed* Subject:, Description:4. Create Ticket Invalid JWT: Failed* Subject:, Description:Button Text: Run All Tests |

</details>

<details closed><summary>src.services</summary>

| File                                                                                                              | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| ---                                                                                                               | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| [admin-service.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\services\admin-service.ts)     | Admin Service handles user creation, retrieval, and deletion via API requests. It takes care of error handling, sending appropriate messages to the user when errors occur.                                                                                                                                                                                                                                                                                                                                                    |
| [auth-service.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\services\auth-service.ts)       | In this code file, Auth Service exports functions for user registration and login, with token-based authentication. Register and login functions are both exported as Promise-returning asynchronous functions. The register function takes a `RegisterRequest` object, posts it to the API, and returns a response object upon success or failure. Similarly, the login function takes a `LoginRequest` object and posts it to the API, returning an AuthResponse object.                                                     |
| [comment-service.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\services\comment-service.ts) | AddComment`, `updateComment`, and `deleteComment`. These methods accept various input parameters, such as the ticket ID and comment ID, and send HTTP requests to the server using the `fetch` function. The methods return promises that resolve with the updated comment data or an error message upon failure.                                                                                                                                                                                                              |
| [ticket-service.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\services\ticket-service.ts)   | Tickets are now at your fingertips! Use the Tick library to retrieve, edit, create, close, and open tickets with ease. Simply import the Tick module and start manipulating tickets as needed.Retrieve tickets using `getTickets()`, edit ticket details with `editTicket()` or `editTicketClosingComment()`, create new tickets with `createTicket()`, close tickets with `editTicketStatus()`, and open tickets with `editTicketOpeningComment()`. Each function returns a promise, making asynchronous operations seamless. |

</details>

<details closed><summary>src.ui</summary>

| File                                                                                        | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| ---                                                                                         | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| [dialogs.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\ui\dialogs.ts) | The Dialogs file achieves the task of providing successful and error messages for the application using the `Swal` library. The functions `success()` and `error()` import the `Swal` module, create and return customized message windows with different icons and texts based on the message parameter. The exports of the function are provided as a single object named `Dialogs`. This file plays an essential role in the overall architecture of the FrontEndTicketProject repository by providing a consistent and user-friendly way of notifying users of various events, such as successful actions or errors during the ticket creation process. |

</details>

<details closed><summary>src.utils</summary>

| File                                                                                                     | Summary                                                                                                                                                                                                                                                                                                                                                 |
| ---                                                                                                      | ---                                                                                                                                                                                                                                                                                                                                                     |
| [axios-helper.ts](https://github.com/ErezD1/FrontEndTicketProject/blob/master/src\utils\axios-helper.ts) | Axios-helper.ts is an util file that provides functionality for handling Axios HTTP requests. It imports Axios and sets defaults for base URL and headers, including the Authorization token. The file also defines two type guards for error handling purposes. The request function is enhanced to include consistent token usage and error handling. |

</details>

---

## 🚀 Getting Started

**System Requirements:**

* **TypeScript**: `version x.y.z`

### ⚙️ Installation

<h4>From <code>source</code></h4>

> 1. Clone the FrontEndTicketProject repository:
>
> ```console
> $ git clone https://github.com/ErezD1/FrontEndTicketProject
> ```
>
> 2. Change to the project directory:
> ```console
> $ cd FrontEndTicketProject
> ```
>
> 3. Install the dependencies:
> ```console
> $ npm install
> ```

### 🤖 Usage

<h4>From <code>source</code></h4>

> Run FrontEndTicketProject using the command below:
> ```console
> $ npm run build && node dist/main.js
> ```

### 🧪 Tests

> Run the test suite using the command below:
> ```console
> $ npm test
> ```

---

## 🛠 Project Roadmap

- [X] `► INSERT-TASK-1`
- [ ] `► INSERT-TASK-2`
- [ ] `► ...`

---

## 🤝 Contributing

Contributions are welcome! Here are several ways you can contribute:

- **[Report Issues](https://github.com/ErezD1/FrontEndTicketProject/issues)**: Submit bugs found or log feature requests for the `FrontEndTicketProject` project.
- **[Submit Pull Requests](https://github.com/ErezD1/FrontEndTicketProject/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.
- **[Join the Discussions](https://github.com/ErezD1/FrontEndTicketProject/discussions)**: Share your insights, provide feedback, or ask questions.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/ErezD1/FrontEndTicketProject
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="center">
   <a href="https://github.com{/ErezD1/FrontEndTicketProject/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=ErezD1/FrontEndTicketProject">
   </a>
</p>
</details>

---

## 🎗 License

This project is protected under the [SELECT-A-LICENSE](https://choosealicense.com/licenses) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/) file.

---

## 🔗 Acknowledgments

- List any resources, contributors, inspiration, etc. here.

[**Return**](#-overview)

---
