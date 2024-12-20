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

# Diagram

This diagram represents the architecture of our E-Commerce API, specifically focusing on how the different components interact.

![E-Commerce API Architecture](path/to/your/diagram/image.png)

---

## **Client Layer**

- Represents the end-users or front-end applications interacting with the API.
- Sends **HTTP requests** to the API endpoints via web browsers, mobile apps, or Postman.

**Example**:
- A user fetches all products using `GET /products`.

---

## **Controller Layer**

- This layer handles incoming requests from the **Client** and maps them to appropriate business logic.
- Acts as the entry point into the backend system.

**Example**:
- The `ProductsController` processes a request to fetch products and calls the **Service/DAO Layer**.

---

## **Service/DAO Layer**

- The **Service** contains the core **business logic** of the application.
- Communicates with the **Database** through DAO (Data Access Object) classes to retrieve or update data.
- Ensures data integrity and implements rules like checking user permissions.

**Example**:
- The `ProductDao` fetches a list of products based on filters like price range and category.

---

## **Database Layer (MySQL)**

- Stores all persistent data for the application, such as user profiles, product details, categories, etc.
- Ensures relationships between entities are maintained, such as categories linked to products.

**Example**:
- A query fetches all products in a specific category.
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
