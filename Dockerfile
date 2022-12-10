#
# Build stage
#
FROM maven:3-eclipse-temurin-17-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17.0.5_8-jre-alpine
COPY --from=build /home/app/target/SDGMainApplication-0.0.1-SNAPSHOT.jar /usr/local/lib/sdg.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/sdg.jar"]