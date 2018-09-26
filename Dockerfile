FROM java:8
WORKDIR /usr/src/app
COPY ./target/get-better-0.0.1-SNAPSHOT.jar /usr/src/app
CMD ["java","-jar","get-better-0.0.1-SNAPSHOT.jar"]