# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:20-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/contacts-rest-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
