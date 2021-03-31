FROM maven

COPY . /app

WORKDIR /app
RUN mvn package

ENTRYPOINT []