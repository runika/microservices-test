FROM java:8-jre
MAINTAINER Miruna Goia <miruna.goia@valoradigital.com>

ADD ./target/config-0.0.1-SNAPSHOT.jar /app/
ADD ./target/lib /app/lib
CMD ["java", "-Xmx200m", "-jar", "/app/config-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081