# crda-backend Project

## Dependencies

See the [application.properties](./src/main/resources/application.properties) for default values and configuration properties

- Current CRDA v1.5
- Trusted Content service

### Third party dependencies

 - Snyk API

## Required parameters

- `api.snyk.org` Snyk OrgId
- `api.snyk.token` Snyk API token for authentication
- `api.crda.key` CRDA user key

## Providers

Currently there are 2 available providers.

 - Snyk will provide a vulnerability report for your components or full dependency graph
 - Red Hat Trusted Content will provide the recommended software matching the current dependencies

## Package Managers

Only `maven` is currently supported.

## Dependency Graph Analysis

With Maven it is possible to generate a [DOT graph](https://graphviz.org/doc/info/lang.html) with all the resolved dependencies.
The following command will generate a `dependencies.txt` file in the project target folder.

```bash
mvn --quiet clean -f "/path/to/project/pom.xml" && mvn --quiet org.apache.maven.plugins:maven-dependency-plugin:3.5.0:tree -f "/path/to/project/pom.xml" -DoutputFile="/path/to/project/target/dependencies.txt" -DoutputType=dot -DappendOutput=true
```

You can submit this file to the `/dependency-analysis` endpoint in order to get a dependency graph analysis backed from the selected provider. Make sure youre passing the
right `Content-Type` and a valid `pkgManager` and `provider`

This is an example for `maven` and `snyk`:

```bash
http :8080/api/v3/dependency-analysis/maven/snyk Content-Type:"text/vnd.graphviz" @'./src/test/resources/dependencies.txt' | jq .
```

## Component Analysis

It is also possible to provide a list of packages in order to get a similar report. This method accepts a JSON object instead of a DOT graph.

Make sure you are providing a valid `pkgManager` and `provider`

```bash
$ curl 'http://localhost:8080/api/v3/component-analysis' \
--header 'Content-Type: application/json' \
--data '{
    "pkgManager": "maven",
    "provider": "redhat",
    "packages": [
        {"name": "log4j:log4j", "version": "1.2.17"},
        {"name": "io.netty:netty-common", "version": "4.1.86"},
    ]
}'
...
[
    null,
    null,
    {
        "artifactId": "jboss-logging-annotations",
        "groupId": "org.jboss.logging",
        "version": "2.2.1.Final-redhat-00001"
    }
]

```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/crda-backend-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)