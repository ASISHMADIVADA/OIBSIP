# Online Reservation System

## Project Overview

The Online Reservation System is a Java Swing desktop application developed as part of the Oasis Infobyte Java Development Internship.

The application allows users to:

- Login using a username and password
- Book train tickets
- Automatically fetch train names from train numbers
- Generate a unique PNR number
- Store reservation details in a MySQL database
- Search reservations using a PNR number
- Cancel reservations after confirmation

---

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- VS Code
- MySQL Connector/J

---

## Project Structure

```
OnlineReservationSystem
│
├── DBConnection.java
├── Reservation.java
├── ReservationDAO.java
├── TrainData.java
├── PNRGenerator.java
├── LoginFrame.java
├── ReservationFrame.java
├── CancellationFrame.java
├── Main.java
├── Utils.java
├── README.md
```

---

## Database Name

```
online_reservation_system
```

---

## Database Tables

### users

| Column | Type |
|---------|------|
| username | VARCHAR(50) |
| password | VARCHAR(50) |

Default Login

Username

```
admin
```

Password

```
admin123
```

---

### reservations

| Column | Type |
|---------|------|
| pnr | VARCHAR(20) |
| passenger_name | VARCHAR(100) |
| train_number | VARCHAR(20) |
| train_name | VARCHAR(100) |
| class_type | VARCHAR(30) |
| journey_date | DATE |
| source_station | VARCHAR(100) |
| destination_station | VARCHAR(100) |

---

## Features

### Login

- Username validation
- Password validation
- Invalid login detection

### Reservation

- Passenger Name
- Train Number
- Automatic Train Name Fetch
- Class Selection
- Journey Date
- Source Station
- Destination Station
- PNR Generation
- Save Booking to Database

### Cancellation

- Search Booking by PNR
- Display Booking Details
- Confirmation Dialog
- Delete Booking from Database

---

## Validation

- Empty fields are not allowed.
- Train number must contain only digits.
- Journey date must be in YYYY-MM-DD format.
- Login credentials are verified using the database.

---

## How to Run

### Step 1

Create the MySQL database.

```sql
CREATE DATABASE online_reservation_system;
```

---

### Step 2

Run the SQL script to create the tables.

---

### Step 3

Update the database credentials inside

```
DBConnection.java
```

Example

```java
private static final String URL = "jdbc:mysql://localhost:3306/online_reservation_system";
private static final String USER = "root";
private static final String PASSWORD = "Rajim@85";
```

---

### Step 4

Add the MySQL Connector JAR to the project.

Example

```
mysql-connector-j-9.x.x.jar
```

---

### Step 5

Compile the project.

```
javac *.java
```

---

### Step 6

Run the application.

```
java Main
```

---

## Future Enhancements

- Seat Availability
- Fare Calculation
- Admin Panel
- User Registration
- Train Search from Database
- Email Confirmation
- PDF Ticket Generation
- Passenger History

---

## Author

**MADIVADA ASISH**

Java Development Internship Project

Oasis Infobyte

2026