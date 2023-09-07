FROM node:18 AS angbuilder

WORKDIR /app

COPY upload-client/src src
COPY upload-client/angular.json .
COPY upload-client/package.json .
COPY upload-client/package-lock.json .
COPY upload-client/tsconfig.app.json .
COPY upload-client/tsconfig.json .
COPY upload-client/tsconfig.spec.json .

# install angular cli
RUN npm i -g @angular/cli
# install packages
RUN npm i
# compile angular
RUN ng build

FROM maven:3-eclipse-temurin-20 AS mvnbuilder

WORKDIR /app

COPY upload-server/src src
COPY upload-server/mvnw mvnw
COPY upload-server/pom.xml .
COPY --from=angbuilder /app/dist/upload-client /app/src/main/resources/static/

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:20-slim 

WORKDIR /app

COPY --from=mvnbuilder /app/target/upload-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=3000
# set your keys here
ENV S3_KEY_ACCESS=123 S3_KEY_SECRET=123

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar /app/app.jar
