#Start a java instance in our program, will compile & run the program..
FROM openjdk:latest
COPY ./target/seMethods-0.1.0.4-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods-0.1.0.4-jar-with-dependencies.jar"]