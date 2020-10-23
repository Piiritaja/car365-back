FROM gradle:6.7.0-jdk11 as assemble
WORKDIR /assemble
COPY . /assemble
RUN gradle assemble --no-daemon
ADD build/libs/car365-backend-0.0.1-SNAPSHOT.jar car365-backend.jar

FROM openjdk:11
WORKDIR /build
COPY --from=assemble /assemble/car365-backend.jar car365-backend.jar
EXPOSE 8080
CMD java -jar car365-backend.jar
