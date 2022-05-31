# Testing and  Continuous Integration 
[sample code](https://github.com/fturizo/ConferenceDemo)
https://blog.payara.fish/jakarta-ee-integration-testing-with-testcontainers


```shell
mvn clean package  -DskipTests=true  -Dmaven.test.skip=true  -Dmaven.main.skip=true    payara-micro:bundle payara-micro:start
```

## Example with OpenLiberty
```shell
mvn clean install
```
Maybee you need to trace it :
```shell
docker pull testcontainers/ryuk:0.3.0 
mvn clean package  -DskipTests=true  -Dmaven.test.skip=true  -Dmaven.main.skip=true 
docker build  -t  test   .
docker run -d   -p 8080:8080  --name lab  test:latest
```
Adjust Dockerfile
```dockerfile
# Reference :-> https://openliberty.io/guides/spring-boot.html#building-and-running-the-application-in-a-docker-container
# FROM openliberty/open-liberty:full-java8-openj9-ubi
FROM icr.io/appcafe/open-liberty:full-java8-openj9-ubi
COPY src/main/liberty/config /config/
ADD target/openlibertytest.war /config/dropins
RUN configure.sh 
```