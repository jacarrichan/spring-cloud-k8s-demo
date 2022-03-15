FROM ascdc/jdk8:latest

ADD cloud-gateway.jar /app.jar

ENV TZ=Asia/Shanghai

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
