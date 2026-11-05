# aot-repos-demo

Spring Boot 4.0.5 + Spring Data JPA with **AOT repositories**: repository implementations are generated as Java source at build time instead of being built reflectively at startup.

Requires JDK 21+ and Maven.

## Run the build (generates AOT sources and runs tests)

    mvn clean package

The `process-aot` execution in `pom.xml` generates the repository code. Look for it under:

    target/spring-aot/main/sources/com/example/aot/UserRepositoryImpl__AotRepository.java

(Class naming can vary by version, so search for `*UserRepository*` under `target/spring-aot` if it is not there.)
Query metadata is written as JSON next to the generated sources.

## Run on the JVM with AOT active

    java -Dspring.aot.enabled=true -jar target/aot-repos-demo-0.0.1-SNAPSHOT.jar

Without `spring.aot.enabled=true` (or a native image), the app still works but uses the regular runtime proxies.

## Try it

    curl localhost:8080/users
    curl "localhost:8080/users/search?lastname=Turing&minAge=30"
    curl localhost:8080/users/by-email/grace@example.com

## Native image (needs a GraalVM JDK)

    mvn -Pnative native:compile
    ./target/aot-repos-demo

## Switches

| Property | Effect |
|---|---|
| `spring.aot.enabled` | Run using AOT-generated code (JVM) |
| `spring.aot.repositories.enabled` | Generate repository source (default on when AOT is active) |
| `spring.aot.jpa.repositories.enabled` | Same, JPA module only |
| `spring.aot.repositories.metadata.enabled` | Emit JSON query metadata |

## Notes

- Spring Boot DevTools can conflict with AOT. Keep it out of AOT/native builds.
- Not every query method style is supported at build time; unsupported ones fall back to runtime handling. Check the Spring Data reference docs for your version.
