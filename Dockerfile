# Required for starting application up.
FROM openjdk:8-jdk-alpine
COPY target/bow-product-svc.jar app.jar
EXPOSE 9010

ENTRYPOINT ["java","-Dspring.profiles.active=kubernetes","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]