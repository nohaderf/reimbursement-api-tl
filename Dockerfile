FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD_STAGE

COPY ./ ./

RUN mvn clean package -DskipTests

FROM openjdk:8-jre-alpine

COPY --from=MAVEN_BUILD_STAGE /target/reimbursement-request-api-0.0.1-SNAPSHOT.jar /reimbursements.jar

CMD ["java", "-jar", "/reimbursements.jar"]