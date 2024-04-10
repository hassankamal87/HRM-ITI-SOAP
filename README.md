

# **HR Managment Api** - SOAP
- HRM API (Human Resource Management API) is a Java-based application programming interface designed to facilitate the management of human resources-related tasks and data.
  This API provides functionalities for tasks such as employee information management, leave management, attendance tracking, and more.
    

# **Installation & Usage** 
  - Dependencies
    - JAVA:
      - You can find Java17 [here](https://dev.mysql.com/downloads/installer/).
      - JAVA_HOME: path/to/java
      - Add path/to/java/bin to PATH
      - Run Command: java -version
        
    - MYSQL Database v8.0.34:
      - You can find the database server [here](https://dev.mysql.com/downloads/installer/).
      - Import the schema file hr_db.sql provided in the repository from your MySQL Workbench.

    - Apache Tomcat 10.1.8:
      - You can find the apache tomcat server [here](https://tomcat.apache.org/download-10.cgi).
      - Download the Windows Zip file then extract it.
      - Unpack then add the following Environment Variables:
      - CATALINA_HOME : path/to/tomcat
      - Add path/to/tomcat/bin to PATH
      
    - Apache Maven:
      - You can find Apache Maven here.
      - Download the Binary Zip Archive.
      - Unpack then add the following Environment Variables:
      - Add path/to/maven/bin to PATH
      - Run Command: mvn -version
  - Go to the project file in the same directory as the pom.xml file and run the following command: mvn package tomcat7:deploy
    
# Documentaion
- Import the xml files provided in the repository in soapDoc from your SoapUI and .

