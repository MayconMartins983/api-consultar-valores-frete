FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY target/demo-zid-code-consult.jar /app

CMD ["java", "-jar", "demo-zid-code-consult.jar"]