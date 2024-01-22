# POC Spring Boot Apache Ignite application

# How to build and run

To compile just do `mvn clean install`.
To run a three node cluster execute the following:
```
java -jar target/apache-ignite-cache-1.0.0-SNAPSHOT.jar
```
Application runs on the port 9001. Application is enabled with Swagger-ui, to test endpoints launch the [Swagger Ui](http://localhost:9001/swagger-ui.html).

## Below is the reference documentation from the Apache Ignite

[Spring Boot Extensions for Apache Ignite](https://apacheignite-mix.readme.io/docs/spring-boot)

[Use Spring Cache Abstraction to interact with Ignite cache](https://apacheignite-mix.readme.io/docs/spring-caching)

[Spring Data Repository Support for Apache Ignite](https://apacheignite-mix.readme.io/docs/spring-data)







