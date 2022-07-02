FROM amazoncorretto:17-alpine

WORKDIR /app

COPY build/libs/transaction-log-ratpack-1.0-SNAPSHOT-all.jar app.jar

ENV REDIS_URL="redis://host.docker.internal:6379"

EXPOSE 5050

ENTRYPOINT ["java","-jar","app.jar"]