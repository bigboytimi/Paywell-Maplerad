FROM openjdk:17-alpine

WORKDIR /app

COPY ./target/paywellX-0.0.1-SNAPSHOT.jar /app/paywell.jar

EXPOSE 8080

CMD ["java", "-jar", "paywell.jar"]