FROM java:8

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/recofashion_app-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /recofashion_app.jar

ENTRYPOINT ["java", "-jar", "/recofashion_app.jar"]
