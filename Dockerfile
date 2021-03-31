FROM ubuntu
RUN apt update && apt install -y maven postgresql-client

COPY . /app
WORKDIR /app

RUN mvn package

EXPOSE 80

ENTRYPOINT ["bash", "/app/entrypoint.sh"]