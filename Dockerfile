# Start with a base image containing Java runtime and Maven
FROM maven:3.8.4-openjdk-17 as build

# Copy the project files into the container
COPY src /hoidanit/spring-mvc/src
COPY pom.xml /hoidanit/spring-mvc

# Set the working directory
WORKDIR /hoidanit/spring-mvc

# Build the application as a WAR file and skip tests
RUN mvn clean package -DskipTests

# Use a Tomcat image as the base for the final stage
FROM tomcat:9.0-jdk17-slim

# Copy WAR file to the webapps directory in Tomcat
COPY --from=build /hoidanit/spring-mvc/target/*.war /usr/local/tomcat/webapps/app.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
