# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY target/quoteapi-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render will override with $PORT)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
