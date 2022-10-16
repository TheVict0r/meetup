# Meetup
Test task for the Java internship for [Modsen software](https://www.modsen-software.com/) 

## Project description
CRUD Rest API for working with meetups.

## Meetup entity
- ID
- Topic
- Description
- Host
- Date and time
- Venue

## Endpoints
- Get a list of all meetups (with filtering feature - by topic, vendor and date in any combinations)
- Get a specific meetup by ID 
- Register (create) a new meetup
- Change information about an existing meetup
- Delete meetup

## Technologies used
- Spring Boot 2.7.4
- Hibernate (as a core JPA implementation not as Spring Data)
- Apache Maven 3.8.6 (wrapper provided)
- PostgreSQL (Scripts for DB creation and test data provided)

## Installation
1. Clone this git repository  
   `git clone https://github.com/TheVict0r/meetup.git`  
2. Use file [create_db.sql](https://github.com/TheVict0r/meetup/blob/main/PostgreSQL_scripts/create_db.sql) for PostgreSQL database creation
3. Use file [data_entry](https://github.com/TheVict0r/meetup/blob/main/PostgreSQL_scripts/data_entry.sql) for entering meetups data 
4. In the file **meetup\\src\\main\\resources\\application.properties**  
   enter your username and password for PostgreSQL database as well as port  
   see lines:  
   `spring.datasource.url=jdbc:postgresql://localhost:5432/meetup`  
   `spring.datasource.username=`  
   `spring.datasource.password=`  
5. To build and run the app in the *meetup* directory (where **pom.xml** and **mvnw.cmd** files are situated) run:  
   `.\mvnw clean install`  
   `java -jar target\meetup-0.0.1-SNAPSHOT.jar`  





