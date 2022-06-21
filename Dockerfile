FROM openjdk:11.0.6-jre-slim
COPY target/moviefetcher-*.war moviefetcher.war
ENTRYPOINT ["java", "-jar", "moviefetcher.war"]
