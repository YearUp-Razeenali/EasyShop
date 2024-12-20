# E-Commerce API and Client Web Application

## Overview
This project consists of an E-Commerce API (`easyshop` backend) and a client-side web application (`capstone-client-web-application`). Together, they provide functionality for managing users, products, categories, and more. The backend is built with Spring Boot, while the frontend is designed as a separate web application.

## Technologies Used
- **Backend**: Java, Spring Boot, Spring Security, Maven
- **Frontend**: `capstone-client-web-application` (details not included here)
- **Database**: MySQL
- **Testing**: Postman, JUnit

## Installation and Setup

### Prerequisites
- Java 17+
- Maven
- IntelliJ IDEA (for IDE setup)
- MySQL Server
- Node.js (for frontend if applicable)

## API Endpoints (Backend)

### Authentication
- **POST** `/auth/login`: Logs in a user and returns a JWT token.

### Categories
- **GET** `/categories`: Retrieve all categories.
- **GET** `/categories/{id}`: Retrieve a category by its ID.
- **POST** `/categories`: Add a new category (Admin only).
- **PUT** `/categories/{id}`: Update a category by ID (Admin only).
- **DELETE** `/categories/{id}`: Delete a category by ID (Admin only).

### Products
- **GET** `/products`: Search for products using filters like category, price range, and color.
- **GET** `/products/{id}`: Retrieve a product by its ID.
- **POST** `/products`: Add a new product (Admin only).
- **PUT** `/products/{id}`: Update a product by ID (Admin only).
- **DELETE** `/products/{id}`: Delete a product by ID (Admin only).

### Profiles
- **GET** `/profile`: Retrieve the profile of the logged-in user.
- **PUT** `/profile`: Update the profile of the logged-in user.

## Testing
1. Use Postman to test all endpoints.
2. **Sample tests:**
   - Retrieve all products: **GET** `/products`
   - Add a new product (Admin): **POST** `/products`
   - Update a product (Admin): **PUT** `/products/{id}`

## Future Enhancements
- **Phase 3: Shopping Cart**
  - Implement full shopping cart functionality where users can add, update, and remove products.
  - Include quantity adjustments and real-time stock validation.
  - Provide persistent storage for shopping cart items for logged-in users.
- **Phase 5: Checkout**
  - Develop a secure checkout process for users to place orders.
  - Integrate payment gateway options for seamless transactions.
  - Generate order confirmations and save order details for future reference.
  - Include address validation and shipping calculations.
