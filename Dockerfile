FROM adoptopenjdk:17-jdk-hotspot

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY pizza-core/pom.xml pizza-core/
COPY pizza-frontend/pom.xml pizza-frontend/

RUN ./mvnw dependency:go-offline

COPY pizza-core/src pizza-core/src
COPY pizza-frontend/src pizza-frontend/src

RUN ./mvnw package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "pizza-frontend/target/pizza-frontend.jar"]