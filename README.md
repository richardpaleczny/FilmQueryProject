## FilmQueryProject

### Description
Using the FilmQueryApp.java class, the application is able to query a database schema called "sdvid", which contains various tables. In particular, it utilizes the film, language, film_actor, and actor tables in the schema. The user is presented a menu which enables them to search for films either by a film ID or search term keyword. The application calls on a class called DatabaseAccessorObject.java which has a mySQL driver and connection to the schema with JDBC API methods. This class parses the request, sending the query to mySQL, and getting the results returned to the application class. The user is shown which films meet their criteria with a system output of film title, year, rating, description, language, and its list of actors. Furthermore, the user has the option to view just the list of actors or to search an actor in particular via an actor ID.

This is a program which primarily utilizes JDBC API methods to query a database using mySQL commands. Additionally, there are elements of polymorphism, OO, and interface implementation. An EER diagram was consulted to determine the right mySQL commands to send. Maven (built into Eclipse) was used to set the dependencies in the pom.xml file, which allowed the source folder to pull necessary resources to enable the mySQL driver to work with the JDBC methods.

### Skill Distillery Week 6 Homework
This project was assigned after Week 6 of Skill Distillery's Java Coding
School.

#### Technologies Used

* Java
* mySQL
* Interfaces, OO, Polymorphism
* EER Diagrams
* JDBC via java.sql library
* Maven, pom.xml file, with mySQL driver

#### Lessons Learned

* Using JDBC API methods with a mySQL driver to access a database schema.
* Using PreparedStatements to avoid poor coding via mySQL injections.
* Setting dependencies in Maven and updating the source folder with the required libraries.
* mySQL commands to pull the right data from tables and also translating those commands to work with Java via the java.sql library.
