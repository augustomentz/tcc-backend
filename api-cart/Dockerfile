FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

ARG SKIP_TESTS=true  # Arg para controlar skip de tests
RUN mvn clean package -DskipTests=$SKIP_TESTS -B

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN apk add --no-cache stress-ng

ARG PORT=8080  # Arg para porta dinâmica
EXPOSE $PORT

CMD ["java", "-jar", "app.jar"]
