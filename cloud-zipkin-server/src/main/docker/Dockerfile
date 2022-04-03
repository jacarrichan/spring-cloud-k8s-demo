FROM ascdc/jdk8:latest
CMD ["cd" ,"/"]
ADD cloud-zipkin-server.jar /app.jar

ENV TZ=Asia/Shanghai

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
