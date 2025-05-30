# Like Hero To Zero  
Case Study – IPWA02  
Advanced JavaEE Web Application for CO₂ Data Management

## Based on Previous Module  
This project is a continuation of the [CO₂ Footprint project (IPWA01)](https://github.com/clia7/IPWA01-CasyStudy-CO2-Footprint), which provided a basic frontend for visualizing CO₂ emissions. The current case study enhances it with a full backend, login system, and persistent data handling using JavaEE technologies.

## Project Description  
"Like Hero To Zero" is a JavaEE web application that displays and manages global CO₂ emission data.

- Public area: View latest emissions by country  
- Scientific area (login required): Add and delete emission entries

The application was developed as part of the module "IPWA02 – Programming Industrial Information Systems with JavaEE".

## Technologies Used  
- Frontend: JSF (JavaServer Faces)  
- Backend: JavaEE (CDI, Managed Beans)  
- Persistence: JPA (Hibernate)  
- Database: MySQL  
- Deployment: Apache Tomcat 11

## Project Structure

## Project Structure

The most relevant project folders and files:

- `src/main/java/beans/` – JSF Managed Beans  
- `src/main/java/model/` – JPA Entity Classes  
- `src/main/webapp/pages/` – JSF Views (.xhtml)  
- `src/main/webapp/WEB-INF/` – Configuration files (e.g. `web.xml`)  
- `pom.xml` – Maven build configuration

## How to Run

1. Clone this repository  
   git clone https://github.com/clia7/IPWA02-CaseStudy-LikeHeroToZero.git

2. Create MySQL database  
   CREATE DATABASE hero2zero_db;

3. Configure database connection in context.xml

4. Deploy the .war file to Apache Tomcat

5. Start the server and open the application in your browser

## Test Login

Username: admin  
Password: admin123

## License  
This project was created for academic purposes as part of the IPWA02 module.
