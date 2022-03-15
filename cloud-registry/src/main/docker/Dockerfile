FROM ascdc/jdk8:latest

ADD cloud-registry.jar /app.jar

ENV TZ=Asia/Shanghai

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
#RUN  top  -b