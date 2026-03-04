[16:46, 3/4/2026] Adham Ehab: # -------- Build Stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only the pom first (for caching)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source
COPY src ./src

# Build the Jar
RUN mvn clean package -DskipTests

# -------- Runtime Stage --------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the jar from build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
[16:46, 3/4/2026] Adham Ehab: # -------- Build Stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only the pom first (for caching)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source
COPY src ./src

# Build the Jar
RUN mvn clean package -DskipTests

# -------- Runtime Stage --------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the jar from build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]