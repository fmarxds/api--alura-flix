FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
RUN mkdir /app
COPY build/libs/*.jar /app/
EXPOSE 5000
CMD java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar /app/api--alura-flix-0.1-all.jar