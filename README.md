# ParkPlace 

The ParkPlace is a parking lot management system which is a backend application developed using Java and the Spring Boot framework. It provides functionalities for managing vehicle entries and exits in a parking lot, storing relevant vehicle information, and automatically calculating parking fees based on the duration of stay. The system utilizes SQL and Hibernate for persistent data storage. The current implementation assumes a manual cash collection process by an operator at the exit gate.

## Features

* **Vehicle Entry Management:** Allows recording details of vehicles entering the parking lot (e.g., license plate number, entry time).
* **Vehicle Information Storage:** Persists vehicle details using a relational database (SQL) managed by Hibernate.
* **Hourly Fee Calculation:** Automatically calculates parking fees based on the time difference between entry and exit.
* **Vehicle Exit Management:** Facilitates recording vehicle exit times.
* **Data Persistence:** Ensures data integrity and availability through SQL database and Hibernate ORM.
* **Operator Cash Collection:** Supports a workflow where the exit gate operator collects the calculated fee in cash.

## Technologies Used

* **Java:** The primary programming language.
* **Spring Boot:** A rapid application development framework for Java.
* **Hibernate:** An Object-Relational Mapping (ORM) framework for database interaction.
* **SQL:** Used for defining and interacting with the relational database (mention the specific database you used, e.g., MySQL, PostgreSQL, H2).
* **Maven:** Build automation tool.

## Getting Started

Follow these steps to get the application running on your local machine.

### Prerequisites

Ensure you have the following installed:

* **Java Development Kit (JDK):** Version 17 or higher is recommended. You can download it from [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.java.net/).
* **Maven:** Version 3.6.0 or higher. Installation instructions can be found [here](https://maven.apache.org/install.html). (Or Gradle, if you used Gradle).
* **SQL Database:** Ensure you have an instance of MySQL database running.
* **Database Client (Optional):** A tool to interact with your database (e.g.,  MySQL Workbench).

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Janhavi-Karande/parkinglot
    cd parkinglot
    ```

2.  **Navigate to the project directory:**
    ```bash
    cd parkinglot
    ```

    ### Running the Application

  * **Using Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```

    The application should now be running. You can check the console output for any startup errors.

    ## Future Enhancements

* Implement a user interface for operators.
* Support different vehicle types with varying hourly rates.
* Integrate payment gateway for online payments.
* Generate reports on parking activity.
