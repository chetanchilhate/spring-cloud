FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8090

COPY ./build/libs/universe-v1.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "universe-v1.jar"]
