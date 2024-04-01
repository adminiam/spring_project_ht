FROM openjdk:21
COPY target/spring_project_ht-0.0.1-SNAPSHOT.jar /spring_project_ht-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "spring_project_ht-0.0.1-SNAPSHOT.jar"]