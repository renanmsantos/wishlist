FROM openjdk:16-jdk-alpine
COPY /target/*.jar /
RUN mv /*.jar wishlist.jar
CMD ["java", "-jar", "wishlist.jar"]