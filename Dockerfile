FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY target/movie_project_61-1.0-SNAPSHOT.jar /app
ENTRYPOINT java -jar /app/movie_project_61-1.0-SNAPSHOT.jar