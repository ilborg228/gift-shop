FROM arm64v8/maven:3.8.6-jdk-11
WORKDIR /notification-service
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run