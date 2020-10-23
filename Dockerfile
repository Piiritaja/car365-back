FROM openjdk:11
WORKDIR ./
ADD build/libs/car365-backend-0.0.1-SNAPSHOT.jar car365-backend.jar
EXPOSE 8080
CMD java -jar car365-backend.jar
