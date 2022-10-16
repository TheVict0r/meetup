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
| **METHOD** | **URL** | **DESCRIPTION** |
|---|---|---|
| GET | http://localhost:8080/meetups/ | Get a list of all meetups |
| GET | http://localhost:8080/meetups?topic={Topic}&host={Host}&date={Date} | Get a list of meetups with filtering feature: by topic, host and date in any combinations |
| GET | http://localhost:8080/meetups/{id} | Get a specific meetup by ID |
| POST | http://localhost:8080/meetups/ | Register (create) a new meetup |
| PUT | http://localhost:8080/meetups/{id} | Change information about an existing meetup |
| DELETE | http://localhost:8080/meetups/{id} | Delete meetup |

## Technologies used
- Java 17
- Spring Boot 2.7.4
- Hibernate 5.6.11 (as a core JPA implementation not as Spring Data)
- JUnit Jupiter 5.8.2
- Mockito 4.5.1
- Apache Maven 3.8.6 
- PostgreSQL 15 (Scripts for DB creation and test data provided)

## Installation
1. Clone this git repository  
   `git clone https://github.com/TheVict0r/meetup.git`  
2. Use file [create_db.sql](./create_db.sql) for PostgreSQL database creation
3. Use file [data_entry.sql](./data_entry.sql) for entering meetups data 
4. In the file **meetup\\src\\main\\resources\\application.properties**  
   enter your username and password for PostgreSQL database as well as port  
   see lines:  
   `spring.datasource.url=jdbc:postgresql://localhost:5432/meetup`  
   `spring.datasource.username=`  
   `spring.datasource.password=`  
5. To build and run the app in the *meetup* directory (where **pom.xml** and **mvnw.cmd** files are situated) run:  
   `.\mvnw clean install`  
   `java -jar target\meetup-0.0.1-SNAPSHOT.jar`  





