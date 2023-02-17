FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY target/api-zip-code-1.0.0.jar /app

CMD ["java", "-jar", "api-zip-code-1.0.0.jar"]