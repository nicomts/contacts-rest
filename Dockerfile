# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:20-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:20-jre
WORKDIR /app
COPY --from=build /app/target/contacts-rest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
