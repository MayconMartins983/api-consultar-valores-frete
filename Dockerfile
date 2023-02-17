# Base image
FROM adoptopenjdk:11-jre-hotspot

# Set working directory
WORKDIR /app

# Copy the executable JAR file and any other necessary files to the container
COPY target/demo-zid-code-consult.jar /app

# Set the command to run the application
CMD ["java", "-jar", "demo-zid-code-consult.jar"]