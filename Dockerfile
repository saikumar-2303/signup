FROM openjdk:17

WORKDIR /app

COPY . .

RUN javac DatabaseConnection.java Main.java

CMD ["java", "Main"]
