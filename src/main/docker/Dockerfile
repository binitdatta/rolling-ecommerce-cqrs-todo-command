FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
WORKDIR /app
ADD rolling-ecommerce-cqrs-todo-command-1.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8081
EXPOSE 9081
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar" ]