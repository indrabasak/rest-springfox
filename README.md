[![Build Status][travis-badge]][travis-badge-url]

REST Springfox/Swagger Example
==========================
This is a [**Springfox/Swagger**](https://springfox.github.io/springfox/docs/current/) example for documenting REST endpoints.

# Build
Execute the following command from the parent directory:
```
mvn clean install
```
Once the build completes successfully, you should have the artifact `rest-springfox.war` in the `target` folder.

# Deploy
You can deploy the `rest-springfox.war` in a [Tomcat](http://tomcat.apache.org/) web container or any other JEE web 
container of your choice.

# Swagger Endpoints
Once you have successfully deployed the war file, the Swagger endpoints can be accessed at:

1. Testing Swagger 2.0 JSON API documentation

   `http://localhost:8080/rest-springfox/v2/api-docs`

2. Testing Swagger UI

    `http://localhost:8080/rest-springfox/swagger-ui.html`


[travis-badge]: https://travis-ci.org/indrabasak/rest-springfox.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/rest-springfox/