# Rental App API

Rental App API is a backend application designed for managing rental locations.

## Requirements

For building and running the application, you need:
- **JDK 17**: Required for compiling and running the application.
- **Maven**: To manage dependencies and build the project.
- **MySQL**: To serve as the application's database.

## Features
- Register and log users to connect to the application
- Handle rentals (get all rentals, create or modify one)
- Send messages to other rental owners

The application can work with the Angular front project available here :
https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git

## Installation

Clone this repository :

```
git clone https://github.com/leaandredev/P3-RentalApi.git
```

Go in your project directory and install depedencies with maven :

```
mvn install
```

### Run the server

Run the Spring Boot application in your IDE or in the terminal using maver:

```
mvn spring-boot:run
```

## Usage

The API will be available on your local machine at http://localhost:9000/

The API documentation is avaible http://localhost:9000/swagger-ui/index.html

## Technologies

**Spring Boot** - Main framework for building the application with modules :
- Spring Data JPA
- Spring Security
- Spring Web
- Spring Validation
- Spring OAuth2 Resource Server

**Java 17** - Programming language

**MySQL** - Database to store application data

**Springdoc OpenAPI** - Interactive API Documentation

**Maven**: Build and dependency management tool.

## Contributing

Rental APP APU is an open source project. Feel free to fork the source and contribute with your own features.

## Authors

- Lea ANDRE

## Licensing

This project was built under the Creative Commons licence.
