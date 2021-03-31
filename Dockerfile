#FROM ubuntu
FROM java-base
#
#COPY . /app
#WORKDIR /app
#
#RUN apt update && apt install -y maven

RUN rm -rf /app
COPY . /app
WORKDIR /app
RUN mvn package
ENV DB_NAME=default
ENV DB_NAME=postgres
ENV DB_NAME=default
ENTRYPOINT ["java", "-jar", "/app/target/spring-boot-postgresql-0.0.1-SNAPSHOT.jar"]