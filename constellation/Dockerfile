FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8086

COPY ./build/libs/constellation-v1.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "constellation-v1.jar"]
