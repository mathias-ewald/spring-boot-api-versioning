# Spring Boot Example: REST API Versioning

Demonstrates API versioning via content negiatiation.

```
$ cd docker/mongodb
$ docker-compose up -d
```

```
$ mvn spring-boot:run
```

```
$ curl -H "Accept: application/vnd.example.pub.v1+hal+json" localhost:8080/publications/p1
{
  ...
  "text" : "In lorem ipsum",
  ...
}
```

```
$ curl -H "Accept: application/vnd.example.pub.v2+hal+json" localhost:8080/publications/p1
{
  ...
  "content" : "In lorem ipsum",
  ...
}
```
