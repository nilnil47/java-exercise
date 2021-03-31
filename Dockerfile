FROM ubuntu

COPY . /app
WORKDIR /app

RUN apt update && apt install -y maven && mvn package
ENV DB_NAME=default
ENTRYPOINT ["java", "-jar", "/app/target/spring-boot-postgresql-0.0.1-SNAPSHOT.jar"]