![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.6-brightgreen?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Authentication-orange)
![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0.0-green?logo=openapiinitiative&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-C71A36?logo=apachemaven&logoColor=white)

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

### Create the Rental Database

1. **Set Up the Database**  
   Open a MySQL client in your terminal or use a tool like MySQL Workbench, and execute the following commands. Replace `rental_api_db`, `your_user_name`, and `your_password` with your desired database name, username, and password:

   ```sql
   CREATE DATABASE rental_api_db;
   CREATE USER 'your_user_name'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON rental_api_db.* TO 'your_user_name'@'localhost';
   FLUSH PRIVILEGES;
   ```

2. **Create Tables**  
   Go to the front-end project repository available [here](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git), locate the SQL script in `ressources/sql/script.sql`, and execute it in your MySQL client to create the required tables (`USERS`, `RENTALS`, and `MESSAGES`).

3. **Configure Database Access in the API Project**  
   The application uses environment variables to define database parameters. In the `src/main/resources/application.properties` file, ensure the following lines are present:

   ```properties
   # MySQL Database Configuration
   spring.datasource.url=${MYSQL_DATABASE_URL}
   spring.datasource.username=${MYSQL_DATABASE_USERNAME}
   spring.datasource.password=${MYSQL_DATABASE_PASSWORD}
   ```

4. **Set Environment Variables**  
   Configure the environment variables for your operating system:

   - **On Linux/macOS:**  
     Add the following lines to your `~/.bashrc` (or `~/.zshrc` for macOS with zsh) file:  
     ```bash
     export MYSQL_DATABASE_URL=jdbc:mysql://localhost:3306/rental_api_db
     export MYSQL_DATABASE_USERNAME=your_user_name
     export MYSQL_DATABASE_PASSWORD=your_password
     ```
     Apply the changes:  
     ```bash
     source ~/.bashrc
     ```

   - **On Windows (Command Prompt):**  
     Set the environment variables temporarily for the current session:  
     ```cmd
     set MYSQL_DATABASE_URL=jdbc:mysql://localhost:3306/rental_api_db
     set MYSQL_DATABASE_USERNAME=your_user_name
     set MYSQL_DATABASE_PASSWORD=your_password
     ```
     For persistent variables, use the *System Properties* panel in Windows to add them under *Environment Variables*.

   - **On Windows (PowerShell):**  
     Use the following commands to set variables for the current session:  
     ```powershell
     $env:MYSQL_DATABASE_URL="jdbc:mysql://localhost:3306/rental_api_db"
     $env:MYSQL_DATABASE_USERNAME="your_user_name"
     $env:MYSQL_DATABASE_PASSWORD="your_password"
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
