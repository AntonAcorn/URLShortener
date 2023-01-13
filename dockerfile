FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar

ENV DB_USERNAME=12345
ENV DB_PASSWORD=12345

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Dspring.datasource.username=12345", "-Dspring.datasource.password=12345", "-jar", "app.jar"]