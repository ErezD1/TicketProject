<p align="center">
  <img src="https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/ec559a9f6bfd399b82bb44393651661b08aaf7ba/icons/folder-markdown-open.svg" width="100" alt="project-logo">
</p>
<p align="center">
    <h1 align="center">TICKETPROJECT</h1>
</p>
<p align="center">
    <em>Where Maven Meets Reliability and Security!</em>
</p>
<p align="center">
	<img src="https://img.shields.io/github/license/ErezD1/TicketProject?style=default&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
	<img src="https://img.shields.io/github/last-commit/ErezD1/TicketProject?style=default&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/ErezD1/TicketProject?style=default&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/ErezD1/TicketProject?style=default&color=0080ff" alt="repo-language-count">
<p>
<p align="center">
	<!-- default option, no dependency badges. -->
</p>

<br><!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary><br>

- [Overview](#overview)
- [Features](#features)
- [📂 Project Structure](#-project-structure)
- [Modules](#modules)
- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Tests](#tests)
- [Project Roadmap](#project-roadmap)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)
</details>
<hr>

##  Overview

TicketProject is an open-source software project with a robust backend component designed for managing and tracking tickets efficiently. The project comprises several key functionalities, including setting up a Maven Wrapper for seamless project configuration and execution, initializing the Spring Boot application with RSA key properties for secure communication, and defining comprehensive application configurations for API security, password encryption, and CORS settings. TicketProject aims to streamline ticket management processes by providing a secure and reliable platform that emphasizes data security and seamless communication. By leveraging industry-standard tools like ModelMapper and BCrypt, the project ensures efficient object mapping and password hashing, enhancing the overall user experience and system reliability. With its emphasis on security, flexibility, and performance, TicketProject offers immense value to organizations seeking a scalable and secure ticket management solution.

---

##  Features

|    |   Feature         | Description |
|----|-------------------|---------------------------------------------------------------|
| ⚙️ | **Architecture**  | The project follows a modular architecture with the frontend built using React and Tailwind CSS and the backend using Spring Boot. It leverages TypeScript for type safety and API communication. The project also utilizes Formik for form handling and routing via React Router. |
| 🔩 | **Code Quality**  | The codebase maintains good code quality standards with linting tools like ESLint and TypeScript. It follows best practices for code formatting and style, ensuring consistency and readability. The use of TypeScript enhances code maintainability and reduces errors. |
| 📄 | **Documentation** | The project includes decent documentation, especially within the backend codebase. It provides details on setting up the development environment, API endpoints, and basic usage instructions. However, more extensive documentation on architecture decisions and component interactions would be beneficial. |
| 🔌 | **Integrations**  | Key integrations include React libraries like Formik, react-hook-form, and react-router-dom for frontend functionality. Backend integrations include JWT for authentication and Axios for API calls. Additionally, libraries like Map and Heroicons are used for additional features. |
| 🧩 | **Modularity**    | The project demonstrates good modularity with clear separation between frontend and backend components. However, further enhancements could be made to improve code reusability by extracting common functionalities into separate modules or libraries. |
| 🧪 | **Testing**       | The project utilizes testing frameworks such as Jest and React Testing Library for frontend testing. Backend testing could be improved with the integration of testing tools like JUnit and Mockito for more comprehensive test coverage. |
| ⚡️ | **Performance**   | The project shows decent performance with efficient frontend rendering using React and optimized API calls. However, performance improvements could be made by implementing caching mechanisms, optimizing database queries, and utilizing server-side rendering for faster content delivery. |
| 🛡️ | **Security**      | Security measures include JWT for authentication, BCrypt for password encryption, and CORS configuration for secure API interaction. However, further security enhancements like input validation, CSRF protection, and secure cookie handling could strengthen the project's security posture. |
| 📦 | **Dependencies**  | Key external dependencies include React, TypeScript, Spring Boot, Formik, Axios, and JWT. Additional libraries like Map, Heroicons, and ESLint plugins contribute to the project's functionality and code quality. |
| 🚀 | **Scalability**   | The project showcases scalability potential by leveraging React's component-based architecture, Spring Boot's scalable backend, and TypeScript for code maintainability. However, scalability could be further enhanced by optimizing backend services for high traffic and load scenarios. |

---
## 📂 Project Structure

<details closed>
<summary>Structure</summary>

```sh
TicketProject
├── BackEndTicketProject
│   ├── .DS_Store 2
│   ├── .gitignore
│   ├── .mvn
│   ├── app.log
│   ├── hs_err_pid28924.log
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── README.md
│   └── src
├── FrontEndTicketProject
│   ├── .gitignore
│   ├── index.html
│   ├── package-lock.json
│   ├── package.json
│   ├── postcss.config.js
│   ├── README.md
│   ├── src
│   ├── tailwind.config.js
│   ├── ticketproject-front.zip
│   ├── tsconfig.json
│   ├── tsconfig.node.json
│   └── vite.config.ts
└── README.md

```

</details>

---

##  Modules

<details closed><summary>BackEndTicketProject</summary>

| File                                                                                          | Summary                                                                                                                                                                                                                                  |
| ---                                                                                           | ---                                                                                                                                                                                                                                      |
| [mvnw](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\mvnw)         | Enables Maven Wrapper for project setup, dynamically downloading wrapper jar if missing, ensures Java compatibility, sets environment variables, and launches Maven tasks based on project directory.                                    |
| [mvnw.cmd](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\mvnw.cmd) | Implements Maven Wrapper startup script handling Java environment setup, Maven configuration, and project directory detection. Ensures proper Maven execution and Maven Wrapper download, enhancing project portability and reliability. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject</summary>

| File                                                                                                                                                                    | Summary                                                                                                                                                                                                                                                          |
| ---                                                                                                                                                                     | ---                                                                                                                                                                                                                                                              |
| [ErezProjectApplication.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\ErezProjectApplication.java) | Launches the Erez Project application with RSA key properties configuration. The main purpose is to initialize the Spring Boot application, enabling secure communication using RSA keys. A fundamental component within the BackEndTicketProjects architecture. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.config</summary>

| File                                                                                                                                                               | Summary                                                                                                                                                                                                                       |
| ---                                                                                                                                                                | ---                                                                                                                                                                                                                           |
| [AppConfig.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\config\AppConfig.java)               | Defines application configuration for API security, password encryption, and CORS settings. Implements ModelMapper for object mapping, BCrypt for password hashing, and allows cross-origin resource sharing for APIs.        |
| [OpenApi3Config.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\config\OpenApi3Config.java)     | Defines OpenAPI 3 configuration for secure HTTP authentication in the Ticket Projects API, specifying title, version, description, contact info, and license details.                                                         |
| [RSAKeyProperties.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\config\RSAKeyProperties.java) | Defines RSA key properties for Spring Boot configuration, holding public and private keys.                                                                                                                                    |
| [SQLRunner.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\config\SQLRunner.java)               | Initializes default roles and users in the Spring Boot application by checking for existing roles. Uses repositories to create admin and user roles with encrypted passwords. This sets up initial user data for the project. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.controller</summary>

| File                                                                                                                                                                     | Summary                                                                                                                                                                                                                                                                                             |
| ---                                                                                                                                                                      | ---                                                                                                                                                                                                                                                                                                 |
| [AuthController.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\controller\AuthController.java)       | Defines endpoints for user authentication & registration, returning JWT tokens on login & user details on registration. Handles bad requests & conflicts gracefully. Organized & secure API in the repository.                                                                                      |
| [CommentController.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\controller\CommentController.java) | Implements CommentController managing ticket comments. Features create, update, delete comment functionalities with JWT authentication. Conforms to OpenAPI spec for API documentation in the TicketProject repository architecture.                                                                |
| [TicketController.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\controller\TicketController.java)   | Defines REST endpoints to manage tickets including creating, updating, opening, closing, fetching, and deleting. Enforces role-based access control and error handling. Implements Swagger annotations for API documentation.                                                                       |
| [UserController.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\controller\UserController.java)       | Defines REST endpoints to manage users, including user creation, update, retrieval, deletion, and role-based search. Utilizes Swagger annotations for API documentation. Supports authorization based on user roles. Promotes secure and structured user operations within the system architecture. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.dto.comment</summary>

| File                                                                                                                                                                        | Summary                                                                                                                                                                                                           |
| ---                                                                                                                                                                         | ---                                                                                                                                                                                                               |
| [CommentCreateDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\comment\CommentCreateDTO.java)     | Defines CommentCreateDTO structure with content validation for BackEndTicketProjects comment module.                                                                                                              |
| [CommentListDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\comment\CommentListDTO.java)         | Defines a data transfer object for a collection of comments in the parent repositorys backend component. The purpose is to structure and transfer comment data efficiently between different parts of the system. |
| [CommentRequestDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\comment\CommentRequestDTO.java)   | Defines CommentRequestDTO model for BackEndTicketProject. Ensures non-null content with size constraints. Supports Java Bean conventions for data transfer within the project architecture.                       |
| [CommentResponseDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\comment\CommentResponseDTO.java) | Defines CommentResponseDTO with ID, content, username, createdAt, and updatedAt fields for user comments in the Ticket Projects backend architecture.                                                             |
| [CommentUpdateDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\comment\CommentUpdateDTO.java)     | Defines CommentUpdateDTO structure for data validation, encapsulating content for backend validation in TicketProject repository.                                                                                 |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.dto.error</summary>

| File                                                                                                                                                  | Summary                                                                                                                      |
| ---                                                                                                                                                   | ---                                                                                                                          |
| [ErrorDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\error\ErrorDTO.java) | Message, status, error, path, timestamp, details, and field. Facilitates consistent error responses in BackendTicketProject. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.dto.login</summary>

| File                                                                                                                                                                  | Summary                                                                                                                                                                                 |
| ---                                                                                                                                                                   | ---                                                                                                                                                                                     |
| [LoginRequestDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\login\LoginRequestDTO.java)   | Defines a LoginRequestDTO record for username and password in the BackEndTicketProject. Facilitates structured data handling for user login information in the repository architecture. |
| [LoginResponseDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\login\LoginResponseDTO.java) | Encapsulates JWT token. Complements authentication processes in the overall repository structure.                                                                                       |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.dto.ticket</summary>

| File                                                                                                                                                                     | Summary                                                                                                                                                                                                                                       |
| ---                                                                                                                                                                      | ---                                                                                                                                                                                                                                           |
| [TicketCloseDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\ticket\TicketCloseDTO.java)       | Defines TicketCloseDTO with essential attributes for backend-ticket interaction, aligning data schema across BackEndTicketProject modules.                                                                                                    |
| [TicketCreateDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\ticket\TicketCreateDTO.java)     | Defines ticket creation data structure with subject, description, and status constraints for backend integration in TicketProject architecture.                                                                                               |
| [TicketListDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\ticket\TicketListDTO.java)         | Defines data structure for ticket list, encapsulating metadata like total, page info, and list of tickets. Facilitates efficient data handling between backend services and frontend UI.                                                      |
| [TicketResponseDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\ticket\TicketResponseDTO.java) | Defines essential data structure for ticket responses, including ID, status, user details, timestamps, comments, and last updates. Crucial for frontend-backend communication in managing ticket information in the TicketProject repository. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.dto.user</summary>

| File                                                                                                                                                               | Summary                                                                                                                                                                                                                                            |
| ---                                                                                                                                                                | ---                                                                                                                                                                                                                                                |
| [UserCreateDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\user\UserCreateDTO.java)     | Defines user creation data transfer object for roles and authentication in the repositorys backend.                                                                                                                                                |
| [UserRequestDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\user\UserRequestDTO.java)   | Defines user data transfer object structure for user registration in the backend system, enforcing validation rules and data constraints. Supports secure handling of user input and ensures data integrity within the TicketProject architecture. |
| [UserResponseDTO.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\dto\user\UserResponseDTO.java) | Defines user response data structure for role-based access control in BackEndTicketProject, facilitating communication with FrontEndTicketProject.                                                                                                 |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.entity</summary>

| File                                                                                                                                             | Summary                                                                                                                                                                                                                                          |
| ---                                                                                                                                              | ---                                                                                                                                                                                                                                              |
| [Comment.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\entity\Comment.java) | Defines Comment entity with fields for user, content, and timestamps; establishes relationships with User and Ticket entities for a TicketProject back-end.                                                                                      |
| [Role.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\entity\Role.java)       | Defines Role entity with validation and unique constraint for name in the backend. Contributes to data integrity and security in the TicketProject architecture.                                                                                 |
| [Status.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\entity\Status.java)   | Defines ticket status enum; vital for backend logic to manage ticket state transitions in the TicketProject architecture.                                                                                                                        |
| [Ticket.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\entity\Ticket.java)   | Defines Ticket entity with subject, description, status, user, comments, and timestamps for TicketProject. Enables relational mapping and cascading operations within the backend architecture.                                                  |
| [User.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\entity\User.java)       | Defines User entity with username, email, password fields, and roles association. Implements constraints for username/email uniqueness and validations. Establishes a one-to-many relationship with comments for the TicketProject architecture. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.exception</summary>

| File                                                                                                                                                                                      | Summary                                                                                                                                                                                                                                                                             |
| ---                                                                                                                                                                                       | ---                                                                                                                                                                                                                                                                                 |
| [AuthenticationException.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\AuthenticationException.java)       | Defines AuthenticationException handling to return UNAUTHORIZED status in the projects backend. Maintains error message consistency for unauthenticated requests in line with the repositorys architecture.                                                                         |
| [BadRequestException.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\BadRequestException.java)               | Defines `BadRequestException` for handling user input errors within the repositorys backend architecture. Informs about invalid requests without specific details in a concise manner.                                                                                              |
| [GlobalExceptionHandler.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\GlobalExceptionHandler.java)         | Handles global exceptions in the BackEndTicketProject by providing customized error responses based on exception types, enhancing user experience and system robustness.                                                                                                            |
| [PaginationException.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\PaginationException.java)               | Defines PaginationException for handling bad requests in the projects backend. Inherits from TicketException to provide custom error messages for pagination issues, enhancing backend service reliability.                                                                         |
| [ResourceNotFoundException.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\ResourceNotFoundException.java)   | Defines custom exception ResourceNotFoundException with specific resource details for error handling. Integrates with the architecture of BackendTicketProject to manage errors elegantly and provide clear feedback for missing resources.                                         |
| [TicketException.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\TicketException.java)                       | Defines a custom exception class `TicketException` for handling runtime errors related to tickets. It enhances error management within the projects backend structure.                                                                                                              |
| [TicketExceptionHandler.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\TicketExceptionHandler.java)         | Designs a flexible exception handling system in the parent repositorys backend. Captures and handles various exceptions, including custom, database constraint violations, and validation errors. Ensures consistent error responses with relevant details for different scenarios. |
| [UnauthorizedException.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\UnauthorizedException.java)           | Defines `UnauthorizedException` to handle authorization issues in the backend. Part of the `edu.erezd.erezproject.exception` package. Simplifies error handling in the repositorys architecture.                                                                                    |
| [UserAlreadyExistsException.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\exception\UserAlreadyExistsException.java) | Defines custom exception handling for duplicate user entries in the backend of the TicketProject repo. Inherits from TicketException and signals a BAD_REQUEST HTTP status. Promotes code readability and maintainability.                                                          |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.repository</summary>

| File                                                                                                                                                                     | Summary                                                                                                                                                                                                                                     |
| ---                                                                                                                                                                      | ---                                                                                                                                                                                                                                         |
| [CommentRepository.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\repository\CommentRepository.java) | Defines CommentRepository interface with methods to query comments by ticket and user, and find latest admin comment by ticket ID. Facilitates efficient retrieval and management of comments within the BackEndTicketProject architecture. |
| [RoleRepository.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\repository\RoleRepository.java)       | Enables role data retrieval by name, augmenting the parent repositorys backend architecture.                                                                                                                                                |
| [TicketRepository.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\repository\TicketRepository.java)   | Enables ticket management by defining essential database operations. Extends JpaRepository for easy access to Ticket entities. Key component within the BackEndTicketProject to handle ticket data storage and retrieval.                   |
| [UserRepository.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\repository\UserRepository.java)       | Facilitates user data management in the backend by providing methods to find users by username or email. Complements the architecture of BackEndTicketProject by extending JpaRepository for seamless data access.                          |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.security</summary>

| File                                                                                                                                                                                             | Summary                                                                                                                                                                                                           |
| ---                                                                                                                                                                                              | ---                                                                                                                                                                                                               |
| [CustomAuthenticationEntryPoint.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\security\CustomAuthenticationEntryPoint.java) | Defines CustomAuthenticationEntryPoint handling authentication failures by returning a JSON error response. Integrated into the backend architecture for managing unauthorized access requests.                   |
| [SecurityConfig.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\security\SecurityConfig.java)                                 | Implements secure API access with JWT authentication, CORS configuration, and role-based authorization. Enables method security and session management for a stateless backend in the TicketProject architecture. |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.service</summary>

| File                                                                                                                                                            | Summary                                                                                                                                                                                                                                    |
| ---                                                                                                                                                             | ---                                                                                                                                                                                                                                        |
| [AuthService.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\AuthService.java)       | Defines authentication service interface for user registration and login, extending UserDetailsService. Handles user registration and login functionality within the BackEndTicketProject, coordinating with the repositorys architecture. |
| [CommentService.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\CommentService.java) | Defines CommentService interface for creating, updating, and deleting comments tied to a ticket. Handles comment retrieval and modification authorization in BackEndTicketProject.                                                         |
| [JWTService.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\JWTService.java)         | Generates JWT token for user authentication based on roles using a JWT encoder in the main backend service of the TicketProject repository.                                                                                                |
| [TicketService.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\TicketService.java)   | Defines TicketService interface for the BackEndTicketProject. Enables creating, updating, closing, opening, getting all tickets, getting a specific ticket, and deleting a ticket while handling authentication.                           |
| [UserService.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\UserService.java)       | Defines essential user-related operations like creating, updating, and deleting users, providing access to user data entities. Central to the parent repositorys architecture, it manages user interactions seamlessly.                    |

</details>

<details closed><summary>BackEndTicketProject.src.main.java.edu.erezd.erezproject.service.impl</summary>

| File                                                                                                                                                                         | Summary                                                                                                                                                                                                                   |
| ---                                                                                                                                                                          | ---                                                                                                                                                                                                                       |
| [AuthServiceImpl.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\impl\AuthServiceImpl.java)       | Register, login, and Spring Security user loading based on UserRepository and RoleRepository. Controls user creation, login, and role mapping for the parent repositorys backend structure.                               |
| [CommentServiceImpl.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\impl\CommentServiceImpl.java) | Implements CommentService operations for commenting on tickets. Manages creation, updating, and deletion of comments with user permissions and ticket status checks. Retrieves and maps comments based on ticket ID.      |
| [TicketServiceImpl.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\impl\TicketServiceImpl.java)   | Create, update, close, open, get-all, and delete tickets. Utilizes Spring Data JPA, ModelMapper for DTO mapping, and User authentication handling for ticket management within the TicketProject repository architecture. |
| [UserServiceImpl.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\java\edu\erezd\erezproject\service\impl\UserServiceImpl.java)       | Manages user operations and permissions, including creating, reading, updating, and deleting users. Maps user-related data between DTOs and entities using Spring Security annotations.                                   |

</details>

<details closed><summary>BackEndTicketProject.src.main.resources</summary>

| File                                                                                                                                                     | Summary                                                                                                                                                                                                                               |
| ---                                                                                                                                                      | ---                                                                                                                                                                                                                                   |
| [http-client.private.env.json](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\http-client.private.env.json) | Provides JWT tokens for user and admin roles in the development environment.                                                                                                                                                          |
| [tickets.http](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\tickets.http)                                 | Enables user and ticket management operations. Facilitates user registration, ticket creation, updating, and commenting. Supports user authentication, role-based access, and ticket status management through various HTTP requests. |

</details>

<details closed><summary>BackEndTicketProject.src.main.resources.META-INF</summary>

| File                                                                                                                            | Summary                                                                                                                                                                             |
| ---                                                                                                                             | ---                                                                                                                                                                                 |
| [MANIFEST.MF](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\MANIFEST.MF) | Specifies metadata for the WebJar Swagger UI within the backend project. Describes version, licensing, and symbolic name for integration into the parent repository's architecture. |

</details>

<details closed><summary>BackEndTicketProject.src.main.resources.META-INF.resources.webjars.swagger-ui.5.10.3</summary>

| File                                                                                                                                                                                                                | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| ---                                                                                                                                                                                                                 | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [index.css](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\index.css)                                                     | Defines global styling for the Swagger UI interface in BackEndTicketProject. Implements consistent box-sizing and scroll behavior for a seamless user experience.                                                                                                                                                                                                                                                                                                                                                                                                                  |
| [index.html](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\index.html)                                                   | Defines Ticket Project API documentation interface styling through Swagger UI integration, enhancing user experience with custom top bar color and favicon.                                                                                                                                                                                                                                                                                                                                                                                                                        |
| [oauth2-redirect.html](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\oauth2-redirect.html)                               | Handles OAuth2 redirection for Swagger UI, verifying and extracting token or error details from the URL parameters. Validates the state and authentication flow, allowing callbacks for successful or error scenarios.                                                                                                                                                                                                                                                                                                                                                             |
| [swagger-initializer.js](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-initializer.js)                           | Initializes Swagger UI with configurable settings for displaying API documentation; supports deep linking, download URL, and standalone layout; ready for configuration updates via Docker in the parent repositorys backend structure.                                                                                                                                                                                                                                                                                                                                            |
| [swagger-ui-bundle.js](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-bundle.js)                               | This code file within the `FrontEndTicketProject` directory serves as the front-end interface for the TicketProject repository. It provides a user-friendly web application interface that allows users to interact with and manage tickets efficiently. The critical features include ticket creation, editing, deletion, and viewing functionalities, enhancing the overall user experience when working with tickets in the TicketProject system.                                                                                                                               |
| [swagger-ui-bundle.js.map](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-bundle.js.map)                       | This code file in the TicketProject repository serves to implement critical backend functionalities for managing tickets in the project. It focuses on handling ticket creation, assignment, and tracking within the system. The code ensures seamless ticket management operations, enhancing the overall efficiency and organization of the project.                                                                                                                                                                                                                             |
| [swagger-ui-es-bundle-core.js](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-es-bundle-core.js)               | Code SummaryThis code file contributes to the TicketProject repository by providing critical backend functionalities for managing tickets. It focuses on key features such as ticket creation, updating, and deletion. The code plays a vital role in ensuring seamless ticket management within the overall architecture of the project.                                                                                                                                                                                                                                          |
| [swagger-ui-es-bundle-core.js.map](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-es-bundle-core.js.map)       | Code SummaryThe code file in this repository is responsible for the backend functionality of the TicketProject application. It handles the core logic for managing tickets, users, and interactions within the system. By leveraging this code, the application can efficiently process and track tickets, ensuring a seamless user experience.                                                                                                                                                                                                                                    |
| [swagger-ui-es-bundle.js](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-es-bundle.js)                         | This code file in the BackEndTicketProject directory of the repository plays a crucial role in handling backend logic and data processing for the TicketProject application. It effectively manages ticket creation, updates, and retrieval, contributing significantly to the core functionality of the project. This code ensures smooth communication between the frontend and backend systems, enabling seamless ticket management within the overall architecture.                                                                                                            |
| [swagger-ui-es-bundle.js.map](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-es-bundle.js.map)                 | Code SummaryThis code file in the BackEndTicketProject directory plays a crucial role in managing and processing ticket-related data for the TicketProject repository. It focuses on handling the back-end logic and functionalities to ensure a seamless ticket management system. The code enables efficient creation, updating, and tracking of tickets while maintaining data integrity and security. It serves as the backbone for the ticketing system, facilitating smooth communication between the front-end user interface and the underlying database.                  |
| [swagger-ui-standalone-preset.js](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-standalone-preset.js)         | This code file in the `FrontEndTicketProject` directory plays a crucial role in rendering the user interface for the TicketProject repository. It serves as the entry point for the frontend application, providing the structure and layout for users to interact with the ticketing system. Through dynamic content generation and seamless navigation, this code enhances the overall user experience, aligning with the repositorys architecture to deliver a user-friendly interface for managing tickets effectively.                                                        |
| [swagger-ui-standalone-preset.js.map](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui-standalone-preset.js.map) | This code file within the TicketProject repository serves the critical function of handling user authentication and authorization for the backend of the TicketProject application. It ensures secure access control and user identity verification, essential for protecting sensitive data and maintaining system integrity.                                                                                                                                                                                                                                                     |
| [swagger-ui.css](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui.css)                                           | The code file in this repository focuses on the FrontEndTicketProject directory, specifically targeting the frontend implementation of the ticket project. It plays a crucial role in providing an interactive user interface for managing tickets efficiently. The code within this file contributes to creating a visually appealing and user-friendly experience for users interacting with the ticketing system.                                                                                                                                                               |
| [swagger-ui.css.map](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui.css.map)                                   | This code file in the FrontEndTicketProject directory of the TicketProject repository serves as the main user interface for a ticket management system. It provides an interactive web interface for users to create, view, and update tickets. The critical features include a user-friendly dashboard, ticket creation forms, real-time updates, and seamless integration with the backend ticket management system. This file plays a crucial role in enhancing user experience and streamlining ticket management processes within the overall architecture of the repository. |
| [swagger-ui.js](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui.js)                                             | This code file in the *FrontEndTicketProject* directory plays a crucial role in the parent repositorys architecture. It serves as the main entry point for the frontend of the ticket management system. The code contained within this file orchestrates the user interface components, interactions, and data management to provide a seamless and intuitive user experience. Through this code, users can view, create, update, and delete tickets efficiently, enhancing the overall functionality of the ticket management application.                                       |
| [swagger-ui.js.map](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\main\resources\META-INF\resources\webjars\swagger-ui\5.10.3\swagger-ui.js.map)                                     | This code file in the BackEndTicketProject directory serves as a crucial component for managing ticket-related functionalities in the TicketProject repository. It focuses on handling ticket creation, updates, and tracking within the project, enhancing overall project organization and collaboration. The code fosters efficient ticket management without delving into intricate technical intricacies, supporting streamlined development within the repository architecture.                                                                                              |

</details>

<details closed><summary>BackEndTicketProject.src.test.java.edu.erezd.erezproject</summary>

| File                                                                                                                                                                              | Summary                                            |
| ---                                                                                                                                                                               | ---                                                |
| [ErezProjectApplicationTests.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\test\java\edu\erezd\erezproject\ErezProjectApplicationTests.java) | Verifies Spring Boot application context in tests. |

</details>

<details closed><summary>BackEndTicketProject.src.test.java.edu.erezd.erezproject.controller</summary>

| File                                                                                                                                                                             | Summary                                                                                                                                                                                                                                                        |
| ---                                                                                                                                                                              | ---                                                                                                                                                                                                                                                            |
| [CommentControllerTest.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\test\java\edu\erezd\erezproject\controller\CommentControllerTest.java) | Tests RESTful API endpoints for creating, updating, and deleting comments. Validates success, error handling, authentication, and authorization responses. Enhances the repositorys backend functionality and ensures robustness of the CommentService module. |
| [JwtTokenUtil.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\test\java\edu\erezd\erezproject\controller\JwtTokenUtil.java)                   | Generates JWT tokens with user details and roles for authentication in the BackEndTicketProject, utilizing JwtEncoder from Spring Security.                                                                                                                    |
| [TicketControllerTest.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\test\java\edu\erezd\erezproject\controller\TicketControllerTest.java)   | Tests TicketController endpoints for creating, updating, closing, and deleting tickets. Implements authentication, error handling, and permission checks. Validates request payloads and responses.                                                            |
| [UserControllerTest.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\test\java\edu\erezd\erezproject\controller\UserControllerTest.java)       | Tests CRUD operations for user management, ensuring successful, bad request, conflict, and not found scenarios. Validates user creation, update, deletion, and retrieval with proper authorization and error handling.                                         |

</details>

<details closed><summary>BackEndTicketProject.src.test.java.edu.erezd.erezproject.security</summary>

| File                                                                                                                                                                 | Summary                                                                                                  |
| ---                                                                                                                                                                  | ---                                                                                                      |
| [TestRSAKeyConfig.java](https://github.com/ErezD1/TicketProject/blob/master/BackEndTicketProject\src\test\java\edu\erezd\erezproject\security\TestRSAKeyConfig.java) | Generates RSA key pair beans for encryption in Spring context, enhancing backend security functionality. |

</details>

<details closed><summary>FrontEndTicketProject</summary>

| File                                                                                                               | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| ---                                                                                                                | ---                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [index.html](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\index.html)                 | Defines the entry point for React application in the FrontEndTicketProject repository. Renders a dynamic web interface powered by Vite, React, and TypeScript, enhancing user interactivity and performance.                                                                                                                                                                                                                                                                                                        |
| [package-lock.json](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\package-lock.json)   | Ticket Project API IntegrationThis code file integrates the TicketProject backend service with external ticketing systems through a RESTful API. It facilitates seamless communication between the backend TicketProject application and various ticketing platforms, enabling ticket creation, updates, and deletions. The integration enhances the functionality of the parent repository by bridging the backend service with external ticketing services, streamlining the ticket management process for users. |
| [package.json](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\package.json)             | Implements Vite scripts for development, building, linting, and previewing the frontend app. Manages dependencies like React, Axios, and Formik. Facilitates seamless frontend development within the TicketProject repository.                                                                                                                                                                                                                                                                                     |
| [postcss.config.js](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\postcss.config.js)   | Configures PostCSS plugins Tailwind CSS and Autoprefixer in the FrontEndTicketProject to enhance styling and ensure cross-browser compatibility.                                                                                                                                                                                                                                                                                                                                                                    |
| [tailwind.config.js](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\tailwind.config.js) | Defines Tailwind CSS config for dark mode, content paths, and theme extensions. Configured to apply to HTML and various JS/TS file types in the FrontEndTicketProject, enhancing styling capabilities and maintainability.                                                                                                                                                                                                                                                                                          |
| [tsconfig.json](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\tsconfig.json)           | Enforce strict TypeScript settings for React project bundling and linting. Optimize for ES2020 targeting and JSX usage, with module resolution set to bundler mode. References additional config file for node-specific settings.                                                                                                                                                                                                                                                                                   |
| [tsconfig.node.json](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\tsconfig.node.json) | Enables TypeScript bundling for the FrontEndTicketProject using Node.js configuration. Achieves optimized code generation and strict type checking to enhance FrontEndTicketProject performance within the TicketProject repository.                                                                                                                                                                                                                                                                                |
| [vite.config.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\vite.config.ts)         | Defines Vite configuration for React plugins to optimize the FrontEndTicketProject build process.                                                                                                                                                                                                                                                                                                                                                                                                                   |

</details>

<details closed><summary>FrontEndTicketProject.src</summary>

| File                                                                                                         | Summary                                                                                                                                                                                              |
| ---                                                                                                          | ---                                                                                                                                                                                                  |
| [index.css](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\index.css)         | Applies Tailwind CSS styles for the front end. Integrates base, components, and utilities to enhance the user interface in the FrontEndTicketProject of the repository.                              |
| [main.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\main.tsx)           | Implements main application structure by wrapping routes with providers for theme and authentication contexts. Integrates router for seamless navigation within the TicketProject FrontEnd.          |
| [vite-env.d.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\vite-env.d.ts) | Defines Vite client types for frontend integration. Supporting rapid development in the TicketProject architecture by enabling efficient communication between the frontend and the Vite build tool. |

</details>

<details closed><summary>FrontEndTicketProject.src.@types</summary>

| File                                                                                                          | Summary                                                                                                                                                 |
| ---                                                                                                           | ---                                                                                                                                                     |
| [types.d.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\@types\types.d.ts) | Defines shared types for user data, authentication, and post/ticket structures, facilitating consistency between systems in a dual-platform repository. |

</details>

<details closed><summary>FrontEndTicketProject.src.components</summary>

| File                                                                                                                              | Summary                                                                                                                                                                                                                                                                         |
| ---                                                                                                                               | ---                                                                                                                                                                                                                                                                             |
| [Card.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\Card.tsx)                     | Defines a reusable Card component for displaying content with styling in the FrontEndTicketProject.                                                                                                                                                                             |
| [Footer.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\Footer.tsx)                 | Defines a footer component for the FrontEndTicketProject that displays attribution information in a visually appealing manner.                                                                                                                                                  |
| [Header.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\Header.tsx)                 | Defines a reusable header component with a dark background and white text for the FrontEndTicketProject.                                                                                                                                                                        |
| [InputField.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\InputField.tsx)         | Enables registration and validation of input fields in the frontend using React Hook Form. Handles field errors dynamically with custom styling and messages. Essential for interactive form functionality in the FrontEndTicketProject architecture.                           |
| [ProtectedRoute.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\ProtectedRoute.tsx) | Enforces authentication flow in frontend routing. Utilizes AuthContext to manage authentication state, rendering loading indicator, login redirection, or child components based on user authentication status. Vital for securing application routes in FrontEndTicketProject. |

</details>

<details closed><summary>FrontEndTicketProject.src.components.DarkModeToggle</summary>

| File                                                                                                                                                             | Summary                                                                                                                                                                                                                                                            |
| ---                                                                                                                                                              | ---                                                                                                                                                                                                                                                                |
| [DarkModeToggle.module.scss](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\DarkModeToggle\DarkModeToggle.module.scss) | Enables dynamic UI theme switching based on user preference through CSS class toggling. Enhances user experience by smoothly rotating UI elements. Complements the architecture of TicketProjects frontend by providing a seamless dark mode toggle functionality. |
| [DarkModeToggle.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\DarkModeToggle\DarkModeToggle.tsx)                 | Enables dynamic switching between dark and light themes in the FrontEndTicketProject. Utilizes React with context API to provide a DarkModeToggle component that changes icon and theme styles based on user interaction.                                          |

</details>

<details closed><summary>FrontEndTicketProject.src.components.Navbar</summary>

| File                                                                                                                     | Summary                                                                                                                                                                                                                     |
| ---                                                                                                                      | ---                                                                                                                                                                                                                         |
| [Navbar.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\components\Navbar\Navbar.tsx) | Manages navigation and user authentication in the frontend. Displays menu links based on user roles and provides options like logout, register, and dark mode toggle. Supports admin privileges with an admin console icon. |

</details>

<details closed><summary>FrontEndTicketProject.src.contexts</summary>

| File                                                                                                                        | Summary                                                                                                                                                                           |
| ---                                                                                                                         | ---                                                                                                                                                                               |
| [AuthContext.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\contexts\AuthContext.tsx)   | Defines user authentication context with login, logout, and token management. Handles user state with JWT decoding, local storage, and logout timer. Enhances React app security. |
| [ThemeContext.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\contexts\ThemeContext.tsx) | Enables dynamic theming in the frontend by managing dark/light mode with local storage synchronization and body class toggling in the TicketProject repositorys architecture.     |

</details>

<details closed><summary>FrontEndTicketProject.src.layout.root</summary>

| File                                                                                                           | Summary                                                                                                                                                                                              |
| ---                                                                                                            | ---                                                                                                                                                                                                  |
| [Root.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\layout\root\Root.tsx) | Defines the layout structure with header, footer, and navigation components for the frontend of the TicketProject. Orchestrates the main content display through react-router-doms Outlet component. |

</details>

<details closed><summary>FrontEndTicketProject.src.routes</summary>

| File                                                                                                                        | Summary                                                                                                                                                                                                                                                                 |
| ---                                                                                                                         | ---                                                                                                                                                                                                                                                                     |
| [Comment.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\Comment.tsx)             | Enables interactive comment management within the TicketProject web app. Allows users to edit or delete comments based on permissions, triggering real-time updates. Supports user authentication and dynamic UI interactions for enhanced user experience.             |
| [index.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\index.tsx)                 | Defines routes for app navigation in the FrontEndTicketProject, specifying components like Login, Register, Tickets, AdminConsole, and TicketTestComponent. Includes error handling for post ID errors and a protected route for authorized access.                     |
| [Login.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\Login.tsx)                 | Enables user authentication with form validation, password toggle, and error handling. Integrates context-based login method and navigation within the FrontendTicketProjects user interface.                                                                           |
| [NewTicketForm.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\NewTicketForm.tsx) | Enables creation of new tickets with validation. Displays a form for users to input subject and description. Handles form submission, displaying success/error messages. Includes cancel button to clear inputs and close form.                                         |
| [Register.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\Register.tsx)           | Enables user registration with form validation and error handling. Integrates authentication service and handles success or error messages. Supports navigation to login page. Optionally displays form data in development mode.                                       |
| [Ticket.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\Ticket.tsx)               | Enables interactive ticket management with comment functionality and status updates. Supports editing by admins, and auto-updates ticket info. Displays ticket details, creation data, status indicators, and user roles. Allows adding, editing, and viewing comments. |
| [Tickets.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\Tickets.tsx)             | Manages ticket display, sorting, and pagination. Retrieves tickets from backend, allows adding new tickets, and updates on interactions. Renders tickets based on user role. Enhances user experience with responsive UI elements.                                      |

</details>

<details closed><summary>FrontEndTicketProject.src.routes.about</summary>

| File                                                                                                              | Summary                                                                                                                                                                                                                                                                                                      |
| ---                                                                                                               | ---                                                                                                                                                                                                                                                                                                          |
| [About.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\about\About.tsx) | Showcases responsive design, secure authentication, dynamic content management, user-centric navigation, optimized load times, and modular architecture. Emphasizes React, TypeScript, and TailwindCSS usage for a full-stack CRUDE-app supporting seamless user experience and efficient code organization. |

</details>

<details closed><summary>FrontEndTicketProject.src.routes.adminConsole</summary>

| File                                                                                                                                     | Summary                                                                                                                                                                                                                                                                                |
| ---                                                                                                                                      | ---                                                                                                                                                                                                                                                                                    |
| [AdminConsole.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\adminConsole\AdminConsole.tsx)   | Manages user interactions, CRUD operations, and modal functionality in the admin console interface. Retrieves, creates, edits, and deletes user data while displaying user roles. Allows for user management with ease and provides quick access to relevant actions.                  |
| [UserFormModal.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\adminConsole\UserFormModal.tsx) | Enables user creation and editing with form validation. Interacts with the backend via axios and admin services. Displays a modal with input fields for username, email, password (if creating), and roles selection. Handles successful and error responses with informative dialogs. |

</details>

<details closed><summary>FrontEndTicketProject.src.routes.error</summary>

| File                                                                                                                              | Summary                                                                                                                                                                                                          |
| ---                                                                                                                               | ---                                                                                                                                                                                                              |
| [Error.module.scss](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\error\Error.module.scss) | Defines styling for error page, centering content vertically and horizontally. Employs large red fonts with shadow effect for the error heading. Crucial for enhancing user experience in the projects frontend. |
| [ErrorPage.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\error\ErrorPage.tsx)         | Generates error messages based on route errors for the frontend in the TicketProject repository.                                                                                                                 |
| [TicketIdError.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\error\TicketIdError.tsx) | Generates error messages for invalid Post IDs in React routes. Handles different error types and displays relevant details. Contributes to the FrontEndTicketProjects error handling architecture.               |

</details>

<details closed><summary>FrontEndTicketProject.src.routes.test</summary>

| File                                                                                                                                         | Summary                                                                                                                                                                                                                                                              |
| ---                                                                                                                                          | ---                                                                                                                                                                                                                                                                  |
| [TicketTestComponent.tsx](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\routes\test\TicketTestComponent.tsx) | Tests ticket creation functionality via API requests, displaying results dynamically. Validates inputs and handles success/failure scenarios. Employs Formik for form interactions. Essential for verifying backend functionality and ensuring proper data handling. |

</details>

<details closed><summary>FrontEndTicketProject.src.services</summary>

| File                                                                                                                            | Summary                                                                                                                                                                                                                   |
| ---                                                                                                                             | ---                                                                                                                                                                                                                       |
| [admin-service.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\services\admin-service.ts)     | Manages CRUD operations for users, delegates API requests to the backend, and handles error responses appropriately. Supports creating, reading, updating, and deleting user data with proper error handling mechanisms.  |
| [auth-service.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\services\auth-service.ts)       | Implements authentication services for ticket project frontend. Handles user registration, login, logout, and authentication status. Persists user data securely in local storage and communicates with backend APIs.     |
| [comment-service.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\services\comment-service.ts) | Implements functions to add, update, and delete comments on tickets. Handles authentication for each operation using a base URL. Helps manage ticket comments via API requests.                                           |
| [ticket-service.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\services\ticket-service.ts)   | Fetches, updates, and creates tickets using REST APIs. Manages ticket status, opening, and closing comments securely with proper error handling. Integrated with the backend API endpoint for a seamless user experience. |

</details>

<details closed><summary>FrontEndTicketProject.src.ui</summary>

| File                                                                                                      | Summary                                                                                                                                                      |
| ---                                                                                                       | ---                                                                                                                                                          |
| [dialogs.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\ui\dialogs.ts) | Defines UI dialogs for success and error messages using SweetAlert2 in the FrontEndTicketProject, enhancing user experience by providing intuitive feedback. |

</details>

<details closed><summary>FrontEndTicketProject.src.utils</summary>

| File                                                                                                                   | Summary                                                                                                                                                        |
| ---                                                                                                                    | ---                                                                                                                                                            |
| [axios-helper.ts](https://github.com/ErezD1/TicketProject/blob/master/FrontEndTicketProject\src\utils\axios-helper.ts) | Enables consistent token handling and error management for HTTP requests within the FrontEndTicketProject, ensuring secure communication with the backend API. |

</details>

---

##  Getting Started

**System Requirements:**

* **Java**: `version x.y.z`

###  Installation

<h4>From <code>source</code></h4>

> 1. Clone the TicketProject repository:
>
> ```console
> $ git clone https://github.com/ErezD1/TicketProject
> ```
>
> 2. Change to the project directory:
> ```console
> $ cd TicketProject
> ```
>
> 3. Install the dependencies:
> ```console
> $ mvn clean install
> ```

###  Usage

<h4>From <code>source</code></h4>

> Run TicketProject using the command below:
> ```console
> $ java -jar target/myapp.jar
> ```

###  Tests

> Run the test suite using the command below:
> ```console
> $ mvn test
> ```

---

##  Project Roadmap

- [X] `► INSERT-TASK-1`
- [ ] `► INSERT-TASK-2`
- [ ] `► ...`

---

##  Contributing

Contributions are welcome! Here are several ways you can contribute:

- **[Report Issues](https://github.com/ErezD1/TicketProject/issues)**: Submit bugs found or log feature requests for the `TicketProject` project.
- **[Submit Pull Requests](https://github.com/ErezD1/TicketProject/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.
- **[Join the Discussions](https://github.com/ErezD1/TicketProject/discussions)**: Share your insights, provide feedback, or ask questions.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/ErezD1/TicketProject
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
   <a href="https://github.com{/ErezD1/TicketProject/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=ErezD1/TicketProject">
   </a>
</p>
</details>

---

##  License

This project is protected under the [SELECT-A-LICENSE](https://choosealicense.com/licenses) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/) file.

---

##  Acknowledgments

- List any resources, contributors, inspiration, etc. here.

[**Return**](#-overview)

---
