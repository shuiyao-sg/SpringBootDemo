#FROM openjdk:12-jdk-alpine
#ADD build/libs/demo-0.1.0.jar demo-0.1.0.jar
#EXPOSE 8086
##VOLUME /tmp
##ARG JAR_FILE=target/*.jar
##COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar","demo-st0.1.0.jar"]

#FROM openjdk:12-jdk-alpine
#VOLUME /tmp
#EXPOSE 8080
#RUN mkdir -p /app/
#RUN mkdir -p /app/logs/
#ADD build/libs/demo-0.1.0.jar /app/app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]

FROM openjdk:12-jdk-alpine
EXPOSE 8080
COPY build/libs/demo-trigger-v4.jar /app.jar
ENTRYPOINT ["java","-jar", "/app.jar" ]