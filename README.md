![Status](https://github.com/iundarigun/employee-challenge/actions/workflows/employee-challenge-ci.yml/badge.svg)
# Employee challenge

Welcome to the employee-challenge! This repository contains logic to manage employees with a database.

## How to run

We need Redis, MySQL and MockWS to run. I Prepared two ways:

1. MySQL, Redis and MockWS images are getting from docker hub, but employee challenge is compiling when try to start:
```shell
docker-compose -f environment/docker-compose-compiling.yml up
```
_Advice_: This options sometimes fails, because `depends on` instruction doesn't wait for ready connections on database, and application sometimes is ready before database, mainly the first run

2. MySQL, Redis and MockWS images are getting from docker hub, and we can run employee-challenge from IDE. Used to develop time:
```shell
docker-compose -f environment/docker-compose.yml up
```

I add swagger to access: http://localhost:1980/swagger-ui.html

### To run test

We can run all tests directly from command line. During the process a MySQL container is started:
```shell
./gradlew test
```

## Technologies
- The main technology are `Java` with `Spring Boot`. I decided use sync way because is easier to add cache.
I add a flyway to create schema and initial data to play with.
  
- I add `Redis` and cache mechanism. In this case, Redis is an overengineering, and Caffeine may be resolve better. But if you have more than one instance of the application, Redis can be a good solution.

- I add an external communication, mocking an endpoint trying to register employee in the government system. I use MockWS for this -a project was created for me two years ago-. It allows us to add delays and failing calls. For leading with, I used Resilient4j with Retry and CircuitBreaker configuration. I let the window of CircuitBreaker small to see the behavior.

- I used mapStruct to transform object DTO to entity and entity to DTO. I used two strategies to lead with differences. The first was ingore null fields on respond Json for the list response, and I used interface and `@Validate` annotation to use the same object to request POST and PUT with different restrictions. 

To Integration test, I choose to use `RestAssured` with `TestContainers`. The main reason is to have an environment near the real scenario. I only test Department endpoint for time to finish the test.

- I used a checkstyle configuration to keep the code style uniform.

## Package design
I separated with package with logical layers:
- `client`: Contains client feign definitions
- `controller`: Contains the endpoints.
- `domain`: Contains da entities and DTOs
- `exception`: Contains the specific application exception
- `mapper`: Contains the mapper classes
- `repository`: Contains the access database layer
- `service`: Contains the business logic. Every entity has a service, who only access to the same entity respository. For access other entity data, use service.