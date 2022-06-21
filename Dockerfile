FROM openjdk:11.0.6-jre-slim
COPY target/moviefetcher-0.0.1-SNAPSHOT.war moviefetcher-0.0.1-SNAPSHOT.war
COPY application.properties application.properties
ENTRYPOINT ["java", "-jar", "moviefetcher-0.0.1-SNAPSHOT.war"]