FROM openjdk:17-jdk-alpine3.14
EXPOSE 8085
ADD ./target/SumaRest-0.0.1-SNAPSHOT.jar suma-rest.jar
ENTRYPOINT ["java","-jar","/suma-rest.jar"]