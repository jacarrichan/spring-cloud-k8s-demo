FROM ascdc/jdk8:latest
CMD ["cd" ,"/"]
ADD cloud-service-b.jar /app.jar

ENV TZ=Asia/Shanghai

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
