# Use a base image with Java
# Use a base image with Java
FROM openjdk:20

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/tv-0.0.1-SNAPSHOT.jar my-application.jar

# Set the entry point command to run the JAR file
CMD ["java", "-jar", "my-application.jar"]
EXPOSE 8081