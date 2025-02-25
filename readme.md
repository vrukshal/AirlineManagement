<p align="center">
    <img src="https://readme-typing-svg.demolab.com?font=Source+Code+Pro&weight=900&size=32&duration=4000&pause=500&color=F0C38E&background=181b28&center=true&vCenter=true&width=900&height=200&lines=Airline+Management+System;Backend+Database+and+Frontend+in+JavaFX" alt="Typing SVG" />
</p>

<details open> 
  <summary><h2>📚 About The Project</h2></summary>
<p align="left">
This **Airline Management System** is a comprehensive solution that helps manage flights, passengers, pilots, planes, and ticket bookings. It features a backend built using MySQL (MariaDB) and a frontend developed with JavaFX. The system allows airline operators to manage flight details, book tickets, manage passengers, pilots, and airplanes, and keep track of all transactions.

💻 **Technologies Used**:
- Backend: **MySQL** (MariaDB)
- Frontend: **JavaFX**
- Database Management: MySQL (MariaDB)
  
💡 **Core Features**:
- Manage Flights: Add, edit, or delete flight details.
- Passenger Management: Store passenger information and associate them with booked tickets.
- Ticket Booking: Issue, track, and cancel tickets with details of class, amount, source, and destination.
- Pilot Management: Add pilot details and assign pilots to flights.
- Plane Management: Track and manage details of all planes, including their names and capacities.

</p>
<!-- </details> -->

#

<details open> 
  <summary><h2>🔧 Languages & Tools</h2></summary>

<br>

  <img align="left" alt="Java" width="40px" style="padding-right:15px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"/>
  <img align="left" alt="Java" width="40px" style="padding-right:15px;" src="https://www.svgrepo.com/show/303251/mysql-logo.svg"/>
  <img align="left" alt="Java" width="40px" style="padding-right:15px;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"/>

<br/>
<br/>
<br/>

</details>

#

<details open> 
  <summary><h2>🖼 Entity Relationship Diagram (ERD)</h2></summary>
  <p align="center">
    <img src="image.png" alt="ERD Diagram"/>
  </p>
</details>

#

<details open> 
  <summary><h2>⚙️ Setup & Installation</h2></summary>

### Prerequisites:
- **MySQL** (MariaDB) installed.
- **Java** installed along with **JavaFX** setup.
- You will also need an IDE like IntelliJ IDEA or Eclipse to run the JavaFX application.

### Steps to Run:
1. **Set Up Database**:
    - Create a database in MySQL:
    ```sql
    CREATE DATABASE airline;
    USE airline;
    ```
2. **Import SQL Dump**:
    - Import the provided SQL file to create the necessary tables and insert sample data into the database.
3. **Run JavaFX Application**:
    - Open the project in an IDE like IntelliJ IDEA or Eclipse.
    - Ensure that your MySQL connection credentials are properly configured in the JavaFX app.
    - Run the project to start managing flights, passengers, and tickets.

</details>

#

<details open> 
  <summary><h2>📈 Database Structure</h2></summary>

### Tables:
- **flighttable**: Contains flight details such as name, plane name, source, destination, departure, and arrival time.
- **passengertable**: Holds passenger information, linked to ticket details.
- **pilottable**: Stores pilot details such as name, contact, and address.
- **planetable**: Contains airplane details including plane name and capacity.
- **tickettable**: Manages ticket details, including flight information, class, and fare.

### Example Data:
- **Flight**: "smith flight", "mumbai", "delhi", Economy Fare: 400, Business Fare: 600.
- **Passenger**: Arnold, Mark, Age: 50, Gender: Male.
- **Ticket**: Source: Mumbai, Destination: Delhi, Amount: 800.

</details>

#

<details open> 
  <summary><h2>🔮 Future Improvements</h2></summary>

- Implement **User Authentication** for Admin.
- Add **Real-time Flight Tracking** functionality.
- Extend the system to allow for **Flight Cancellations** and **Reservation Modifications**.
  
</details>

#

<details open> 
  <summary><h2>📬 Contact</h2></summary>
Feel free to reach out for any inquiries or suggestions related to the project!

**LinkedIn**: [Vrukshal Patel](https://www.linkedin.com/in/vrukshal)

</details>

