Like Hero To Zero
Case Study – IPWA02
Advanced JavaEE Web Application for CO₂ Data Management

Based on Previous Module
This project is a continuation of the CO₂ Footprint project (IPWA01), which provided a basic frontend for visualizing CO₂ emissions. The current case study enhances it with a full backend, login system, and persistent data handling using JavaEE technologies.

Project Description
"Like Hero To Zero" is a JavaEE web application that displays and manages global CO₂ emission data.

Public area: View latest emissions by country
Scientific area (login required): Add and delete emission entries
The application was developed as part of the module "IPWA02 – Programming Industrial Information Systems with JavaEE".

Technologies Used
Frontend: JSF (JavaServer Faces)
Backend: JavaEE (CDI, Managed Beans)
Persistence: JPA (Hibernate)
Database: MySQL
Deployment: Apache Tomcat 11
Project Structure
hero2zero-app/
├── src/
│   └── main/
│       ├── java/
│       │   ├── beans/        # Managed Beans
│       │   └── model/        # JPA Entities
│       └── webapp/
│           ├── pages/        # JSF Views
│           └── WEB-INF/      # Configuration files
└── pom.xml                   # Maven build file

How to Run
Clone this repository
git clone https://github.com/clia7/IPWA02-CaseStudy-LikeHeroToZero.git

Create MySQL database
CREATE DATABASE hero2zero_db;

Configure database connection in context.xml

Deploy the .war file to Apache Tomcat

Start the server and open the application in your browser

Test Login
Username: admin
Password: admin123

License
This project was created for academic purposes as part of the IPWA02 module.
