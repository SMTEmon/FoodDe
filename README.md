# FooDDe - Food Delivery Application

FooDDe is a modular Java-based web application for food delivery, built for the lab final project with a focus on Object-Oriented Design principles (SOLID) and clean architecture.

## Project Features

### User Experience
- **Browse Restaurants:** A list of nearby restaurants can be viewed with their details.
- **Menu Selection:** Items can be browsed, including prices and descriptions.
- **Shopping Cart:** Items can be added to a cart that persists throughout the session.
- **Checkout:** A simple flow is provided to capture delivery details and place an order.

### Restaurant Management (Admin)
- **Registration:** New restaurants can be registered to the platform.
- **Menu Management:** Menu items can be added to existing restaurants in real-time.
- **Persistence:** All data is immediately saved to XML files within the data folder.

### Technical API
- **WSDL/SOAP Service:** An external API is provided at a separate URL to query restaurants by area name, fulfilling the requirement for a WSDL-exposed service.

## Architecture and Design (SOLID)

The SOLID principles have been applied throughout this project to ensure a modular and maintainable codebase:

- **Single Responsibility Principle (SRP):** Each package has a specific role. Models are restricted to data representation, Repositories handle storage, and Controllers manage web requests.
- **Dependency Inversion Principle (DIP):** The controllers depend on the `StorageRepository` interface rather than the specific `XmlRepository` implementation, allowing for flexibility in data source selection.
- **Open/Closed Principle (OCP):** The project is structured to allow the addition of new features without necessitating modifications to the core logic.

## Technical Stack

- **Java 21 (LTS):** Modern Java features such as Records and Streams are utilized.
- **Javalin:** A lightweight web framework is used for handling routes.
- **Thymeleaf:** A template engine is employed to render HTML with Java data.
- **JAXB:** XML data storage is managed without manual parsing.
- **Jakarta XML WS:** The SOAP/WSDL service is generated and published using this library.

## How to Run

1. Java 21 and Maven should be installed on the system.
2. The project can be started from the root folder:
   ```bash
   mvn compile exec:java -Dexec.mainClass="com.foodde.Main"
   ```
3. The application can be accessed in the browser:
   - **Main App:** http://localhost:7070
   - **Admin Dashboard:** http://localhost:7070/admin
   - **WSDL API:** http://localhost:8081/services/restaurant?wsdl

## Project Organization
```text
src/main/java/com/foodde/
├── controller/   # Web logic for Cart, Users, Restaurants, and Admin
├── model/        # Data Entities
├── repository/   # Storage interfaces and XML implementation
├── service/      # SOAP/WSDL API implementation
└── Main.java     # Application entry point
```
