# Real Estate API

A Spring Boot REST API to manage properties, owners, tenants and leases, backed by an H2 database.

## Requirements

- JDK 21
- Maven (provided through the Maven wrapper `./mvnw`)

## Start the application

All commands are run from the root of the project (the folder containing `pom.xml`):

```bash
./mvnw spring-boot:run
```

The application listens on http://localhost:8080. Stop it with `Ctrl+C`.

```bash
curl -i http://localhost:8080/api/properties
```

## Run the tests

```bash
./mvnw test                      # all the tests
./mvnw test -Dtest=Task00Test    # the tests of one task
```
