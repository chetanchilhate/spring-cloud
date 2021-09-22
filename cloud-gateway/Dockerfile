FROM adoptopenjdk:11-jre-hotspot

EXPOSE 9090

COPY ./build/libs/cloud-gateway-v1.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "cloud-gateway-v1.jar"]