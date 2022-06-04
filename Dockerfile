ARG JDK_TAG
FROM openjdk:${JDK_TAG}
ARG JAR_FILE="target/*.jar"
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/heap.dump"
CMD ["/bin/sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]