# base image from adopt openjdk
FROM adoptopenjdk/openjdk11:alpine-jre
# Refer to Maven build -> finalName
ARG JAR_FILE=target/demo-ibm-cloud-0.0.2.jar
# cd /opt/app
WORKDIR /opt/app
COPY ${JAR_FILE} demo-ibm-cloud-0.0.2.jar
ENTRYPOINT ["java","-jar","demo-ibm-cloud-0.0.2.jar"]