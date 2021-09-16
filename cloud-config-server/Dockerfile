FROM adoptopenjdk:11-jre-hotspot

EXPOSE 9051

COPY ./build/libs/cloud-config-server-v1.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "cloud-config-server-v1.jar"]