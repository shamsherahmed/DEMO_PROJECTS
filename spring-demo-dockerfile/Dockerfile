FROM shamsid/ubuntu-java:1.0

WORKDIR /app
COPY ./target/spring-demo-dockerfile-demo-application.0.0.1.jar /app

EXPOSE 8080

CMD ["java", "-jar", "spring-demo-dockerfile-demo-application.0.0.1.jar"]