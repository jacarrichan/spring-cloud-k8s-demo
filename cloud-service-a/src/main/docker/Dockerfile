FROM ascdc/jdk8:latest

ADD cloud-service-a.jar /app.jar

ENV TZ=Asia/Shanghai

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
