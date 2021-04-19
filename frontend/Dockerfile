FROM adoptopenjdk/openjdk11:alpine
ADD target/quizgame-exec.jar quizgame-exec.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/quizgame-exec.jar"]