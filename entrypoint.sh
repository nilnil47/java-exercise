# create new db if not exists
psql -h $DB_HOST -U $DB_USER -p $DB_PORT -c "CREATE DATABASE ${DB_NAME};"
java -jar /app/target/spring-boot-postgresql-0.0.1-SNAPSHOT.jar