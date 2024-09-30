

# Cravings - A Food Ordering Application

**Cravings** is a backend service for a food ordering application built
using **Java Spring Boot**, with authentication managed via **JWT (JSON
Web Tokens)**. The service includes APIs for user management, restaurant
services, menu items, cart, and order management. The system allows
users to sign up, browse restaurants, select items, add them to the
cart, and place orders.

## Table of Contents

-   [Features](#features)
-   [Tech Stack](#tech-stack)
-   [Setup & Installation](#setup--installation)
-   [Project Structure](#project-structure)
-   [Usage](#usage)
-   [API Endpoints](#api-endpoints)
-   [Future Enhancements](#future-enhancements)
-   [License](#license)

------------------------------------------------------------------------

## Features

-   **User Authentication & Authorization**: Secure user signup and
    login using JWT.
-   **Role-Based Access Control**: Admin and user roles with specific
    permissions.
-   **Restaurant Management**: APIs to view restaurants and their
    respective food items.
-   **Cart Management**: Add/remove items from the cart and manage
    quantities.
-   **Order Management**: Place and manage orders based on restaurants.
-   **Ingredient Categories & Items**: View detailed ingredient
    categories and items.

------------------------------------------------------------------------

## Tech Stack

-   **Backend**: Java, Spring Boot, Spring Security (JWT)
-   **Database**: MySQL
-   **API Documentation**: Swagger
-   **Build Tool**: Maven
-   **Version Control**: Git

------------------------------------------------------------------------

## Setup & Installation

To get the **Cravings** backend service running locally, follow these
steps:

### 1. Prerequisites

Ensure you have the following software installed on your machine:

-   **Java Development Kit (JDK)**: Version 8 or later
-   **Maven**: For managing dependencies and building the project
-   **MySQL**: For the relational database
-   **Git**: For version control
-   **Postman or Curl**: To test the APIs

### 2. Clone the Repository

First, clone the project to your local machine using the following
command:

``` bash
git clone https://github.com/charankasanagottu/Cravings.git
cd Cravings
```

### 3. Setup MySQL Database

You need to set up a MySQL database for the project:

-   Open MySQL Workbench or command-line interface (CLI).
-   Create a new database:

``` sql
CREATE DATABASE cravings_db;
```

-   Update the **application.properties** file (located in
    `src/main/resources`) with your MySQL credentials:

``` properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/cravings_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

### 4. Build and Package the Application

Use Maven to install the required dependencies and package the project:

``` bash
mvn clean install
```

### 5. Run the Application

After the project has been built, run the Spring Boot application:

``` bash
mvn spring-boot:run
```

The application should start on `localhost:8080`.

### 6. Testing the APIs

You can test the API using **Postman** or **cURL**. Make sure to hit the
`/auth/login` endpoint to get the JWT token and use it for accessing
other secured routes.

#### Example:

-   Login with a POST request:

``` bash
POST http://localhost:8080/auth/login
Body: 
{
  "username": "user@example.com",
  "password": "password123"
}
```

The response will contain a JWT token. Use this token in the
`Authorization` header for further API requests.

------------------------------------------------------------------------

## Project Structure

Here is a brief overview of the project structure:

``` plaintext
Cravings
│   README.md
│   pom.xml                 # Maven build configuration
└───src
    └───main
        ├───java
        │   └───com
        │       └───cravings
        │           ├───controller  # API Controllers
        │           ├───model       # Data Models (Entities)
        │           ├───repository  # JPA Repositories
        │           ├───service     # Business Logic Services
        │           └───security    # JWT Security Configuration
        └───resources
            ├───application.properties  # App Configurations (Database, JWT, etc.)
            └───static                  # Static Resources
```

------------------------------------------------------------------------

## Usage

Once the application is running, you can perform the following
operations:

1.  **User Management**:
    -   Register a new user via `/auth/signup`
    -   Login via `/auth/login` to receive a JWT token for accessing
        protected routes
2.  **Restaurant Management**:
    -   View restaurants and their menus via `/restaurants`
    -   Add items to the cart, view and manage the cart
3.  **Order Management**:
    -   Place orders via `/orders`
    -   View past orders

------------------------------------------------------------------------

## API Endpoints

Here is a list of some important API endpoints:

### User Authentication

-   **POST** `/auth/signup`: Register a new user
-   **POST** `/auth/login`: Login and obtain a JWT token

### Restaurant Services

-   **GET** `/restaurants`: View all available restaurants
-   **GET** `/restaurants/{id}`: View details of a specific restaurant

### Cart Services

-   **POST** `/cart`: Add items to the cart
-   **GET** `/cart`: View the cart
-   **DELETE** `/cart/{itemId}`: Remove items from the cart

### Order Services

-   **POST** `/orders`: Place an order
-   **GET** `/orders`: View all orders

------------------------------------------------------------------------

## Future Enhancements

-   **User Profiles**: Add functionality for users to manage their
    profiles.
-   **Payment Gateway**: Integrate payment gateway services like Stripe
    or Razorpay.
-   **Admin Panel**: Create a dashboard for restaurant owners to manage
    menus and orders.
-   **Notification Service**: Implement real-time notifications for
    order status.

------------------------------------------------------------------------

## License

This project is licensed under the MIT License. See the `LICENSE` file
for more details.

------------------------------------------------------------------------

Feel free to contribute by creating a pull request or reporting issues.
Check out the project on
[GitHub](https://github.com/charankasanagottu/Cravings).


