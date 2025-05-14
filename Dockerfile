FROM openjdk:17

WORKDIR /app
COPY . .

RUN mkdir -p build
RUN javac -d build src/main/java/org/jsp/*.java

CMD ["java", "-cp", "build", "org.jsp.Main"]
