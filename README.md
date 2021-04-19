# PG5100-QuizGame-SpringBoot

[![Java CI with Maven](https://github.com/magnuen2k/PG5100-QuizGame-SpringBoot/actions/workflows/maven.yml/badge.svg)](https://github.com/magnuen2k/PG5100-QuizGame-SpringBoot/actions/workflows/maven.yml)

![](doc/img/quiz-time.jpeg  "Quiz Time Image")

### Description
Simple quiz game made with SpringBoot. 

### Setup for production mode
1. Setup `application.yml` / `application.properties` with your postgres database
2. Run `mvn clean verify` to build the project.
3. Execute .jar with cmd: `java -jar frontend/target/quizgame-exec.jar`.

### Setup for testing
1. Run `LocalApplicationRunner` class in `frontend/src/test/java/org/quigame/`
    * This is using an in memory database (h2)
