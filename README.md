
# Patient API

Once set-up, you can use the following commands.

## Using maven

* Re-compile

```
mvn clean install
```

* Test the service

```
mvn clean test
```

* Run the service (by default it uses a H2 database)

```
mvn spring-boot:run
```

* Run the service using a local postgresql database

```
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

## Using Docker




## Source Code Description

Please see [SystemDocumentation.md](SystemDocumentation.md)


## Set-up procedure

Please see [SetUp.md](SetUp.md)


## Testing

Please see [Testing.md](Testing.md)