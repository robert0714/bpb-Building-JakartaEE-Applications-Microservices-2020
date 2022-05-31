# Building-JakartaEE-Applications-Microservices
Building Jakarta EE Applications &amp; Microservices

## Chapter 01 Jakarta EE Platform
### Payara Previous version
https://www.payara.fish/downloads/previous-releases/
### Payara Micro Maven Plugin
* https://blog.payara.fish/using-the-payara-micro-maven-plugin
* [Start](https://github.com/payara/ecosystem-maven/blob/master/payara-micro-maven-plugin/README.md)
```shell
mvn payara-micro:start
```
* [stop](https://github.com/payara/ecosystem-maven/blob/master/payara-micro-maven-plugin/README.md#stop)
```shell
mvn toolchains:toolchain payara-micro:stop
```
#### Payara Eclipse IDE Plugin
* https://docs.payara.fish/community/docs/documentation/ecosystem/eclipse-plugin/README.html
* [Payara Server tools in Eclipse IDE](https://docs.payara.fish/community/docs/documentation/ecosystem/eclipse-plugin/payara-server.html)
* [Payara Micro tools in Eclipse IDE](https://docs.payara.fish/community/docs/documentation/ecosystem/eclipse-plugin/payara-micro.html)

### MicroProfile
#### compatible
* https://microprofile.io/compatible/
* https://wiki.eclipse.org/MicroProfile/Implementation

# Examples
* [nosqlejb](./nosqlejb/)
* [myservlet](./myservelt/)  depend on [nosqlejb](./nosqlejb/)  
```shell
curl http://localhost:8080/myservelt/Hello
curl http://localhost:8080/myservelt/PersonServlet
```
* [myweb](./myweb/)  depend on [nosqlejb](./nosqlejb/)  , Mongo DB
```shell
curl http://localhost:8080/myweb
```
* [myjaxrs](./myjaxrs/)  
```shell
curl http://localhost:8080/myjaxrs
```
* [mywebsocket](./mywebsocket/)  
```shell
curl http://localhost:8080/mywebsocket
```
