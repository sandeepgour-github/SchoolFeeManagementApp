# School Fee Management System

This is a full-stack web application built with **Spring Boot (Java)** for the backend and **HTML/CSS/JS** for the frontend. It allows easy management of student records and fee payments.

# Project Structure

```
SchoolFeeManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── in/
│   │   │       └── sandeep/
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── entity/
│   │   │           ├── exception/
│   │   │           └── repository/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           ├── index.html
│   │           ├── student-register.html
│   │           ├── student-search.html
│   │           ├── fee-pay.html
│   │           ├── fee-history.html
│   │           ├── student-list.html
│   │           ├── admin-dashboard.html
│   │           ├── css/
│   │           │   └── style.css
│   │           └── js/
│   │               ├── register.js
│   │               ├── search.js
│   │               ├── pay.js
│   │               └── history.js
├── receipts/                  
├── target/                    
├── pom.xml                   
├── mvnw, mvnw.cmd            
├── README.md             
└── HELP.md        


```

# Features

## Student Module

- Register a new student (auto-generates unique student ID)
- Search student by name, ID, or mobile number
- View list of all students

## Fee Module

- Record new fee payments
- Prevent overpayment or invalid entries
- Generate PDF receipt automatically after each payment
- Show remaining balance and next due date
- View detailed fee payment history

# Tech Stack

- **Backend:** Java, Spring Boot, REST API
- **Frontend:** HTML, CSS, JavaScript, Bootstrap, SweetAlert2
- **Database:** MySQL
- **IDE:** Spring Tool Suite (STS), Visual Studio Code (VS Code)
- **Build Tool:** Maven

# Prerequisites

- Java JDK 17+ (JDK 21 recommended)
- Maven installed (`mvn -version`)
- MySQL server running (or modify for SQLite)

# How to Run

## MySQL Setup

Create the database:
CREATE DATABASE fee_db;

## Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/fee_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

## Backend (Spring Boot)

```bash
cd SchoolFeeManagement
mvn spring-boot:run
```

Visit: [http://localhost:8081/api/students/...](http://localhost:8081/api/students/...)

## Frontend (Static Pages)

Spring Boot automatically serves files placed in src/main/resources/static/
You can access the pages directly via the browser once the server is running

`http://localhost:8081/index.html` <- Home Page

`http://localhost:8081/student-register.html` <- Register new students

`http://localhost:8081/student-search.html` <- Search students by name, ID, or mobile

`http://localhost:8081/fee-pay.html` <- Make fee payments

## Receipts

All payment receipts are saved as PDFs in the `/receipts` folder and can be downloaded after payment.

## Tips

- Set `JAVA_HOME` and `MAVEN_HOME` in your system environment variables.
- Frontend fetches API from backend at `http://localhost:8081/`

📌 Future Enhancements

Admin login and authentication
SMS Remider Module
Export student/fee data to Excel
Mobile-friendly responsive design
