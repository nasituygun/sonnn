FROM openjdk:20 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:20
WORKDIR Master
COPY --from=build target/*.jar Master.jar


ENTRYPOINT ["java", "-jar", "Master.jar"]