# FarmDataApp
Application for data of farms management

This FarmData web application was created with Java using Spring Boot. 
The UI is implemented with bootstrap. Backend uses MySQL-database. 

This application is made in Windows. It has not been tested with other operating systems. Spring Boot requires JDK 1.8 and Maven 3.2 or later. 
Before running the application, you need to install MySQL and create a new database. 
Then add the database name, username and password to the application.properties file. The path is FarmDataApp\src\main\resources\application.properties.

spring.datasource.url= jdbc:mysql://localhost:3306/*your database name here*
spring.datasource.username= *Your username here*
spring.datasource.password= *Your password here*

After that you can run the application. Select FarmDataAppApplication.java --> Run as --> Java Application

Type in your browser http://localhost:8080/

You have to add data to the database (Store new farms -link) for the application to start displaying farmdata.


This application displays data from different farms. User can filter data with different search criteria and see some statistical analysis with given criteria. 
User can store farmdata as csv file. Csv file is uploaded by clicking Store new farms -link. Validations have been added to the system, so it can also receive files 
that contain errors. Invalid values are discarded. Data is presented in table format. 

I have previously used Spring Boot with school projects and in one of my own projects. I have noticed Spring booot to a popular requirement in job advertisements
and found it to be an interesting tool. I wanted to use this opportunity to deepen my understanding of it.
 
I would have loved to use REACT for frontend because I have been excited about it lately through my studies. 
Unfortunately, I had limited time to work on this project so I had to settle for bootstrap. I prioritized working on backend. 
I chose MySQL as the database because I have been using PostgreSQL and SQLite so far, and had heard praise for the MySQL Workbench tool.

TODO
- Clearer and more versitile data filtering
- Visual data representation
- User management
- REACT frontend

