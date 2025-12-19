# ---------- BUILD STAGE ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build

# Copy pom.xml and download dependencies
COPY quoteapi/pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY quoteapi/src ./src

# Build the application
RUN mvn clean package -DskipTests

# ---------- RUN STAGE ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy jar from build stage
COPY --from=build /build/target/*.jar app.jar

# Expose port (Render overrides with $PORT)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
