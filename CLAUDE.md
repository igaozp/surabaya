# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Surabaya is a comprehensive collection of JVM ecosystem code examples and best practices, organized into independent
modules demonstrating modern Java/Kotlin patterns, frameworks, and architectural practices.

**Technology Stack:**

- Java 25 with modern features (Virtual Threads, Scoped Values, Records)
- Kotlin 2.3.0-RC with coroutines
- Spring Boot 4.0.0, Quarkus, Helidon
- Gradle 9.2.1 (wrapper provided)

## Build Commands

All commands use the Gradle wrapper. On Windows, use `gradlew.bat` or `./gradlew` in Git Bash.

### Essential Commands

```bash
# Build entire project
./gradlew build

# Run all tests
./gradlew test

# Run tests for a specific module
./gradlew :framework:spring:web:test
./gradlew :arch:tdd:test

# Clean and rebuild
./gradlew clean build

# Run tests continuously (watch mode)
./gradlew test --continuous

# Run tests with coverage
./gradlew test jacocoTestReport
```

### Running Applications

```bash
# Spring Boot applications (use bootRun)
./gradlew :framework:spring:web:bootRun
./gradlew :framework:spring:next:bootRun

# Quarkus applications (use quarkusDev)
./gradlew :framework:quarkus:virtual-threads:quarkusDev

# Design pattern examples
./gradlew :design-pattern:run
```

### Module-Specific Commands

```bash
# List all tasks for a module
./gradlew :framework:spring:web:tasks

# Build specific module
./gradlew :orm:jpa:build

# Run single test class
./gradlew :arch:tdd:test --tests StudentRegistrationControllerTest
```

## Project Architecture

### Module Organization

The project follows a **multi-module Gradle structure** with 9 top-level categories:

1. **`framework/`** - Web frameworks (Spring, Quarkus, Helidon)
    - `spring/{next, web, transaction, reactive, connection-pool}` - Spring Boot examples
    - `quarkus/virtual-threads` - Quarkus with Project Loom
    - `helidon/` - Helidon microservices

2. **`lang/`** - Language features
    - `java/` - Virtual Threads, Records, Streams, SPI
    - `kotlin/` - Coroutines, Flow, Java interop

3. **`orm/`** - Persistence frameworks
    - `jpa/` - JPA/Hibernate with Spring Data
    - `mybatis/` - SQL mapping
    - `shardingsphere/` - Database sharding

4. **`middleware/`** - Caching solutions
    - `redis/` - Redis with vector search
    - `caffeine/` - In-memory caching

5. **`db/`** - NoSQL databases
    - `mongo/` - MongoDB with Spring Data
    - `elasticsearch/` - Full-text search

6. **`arch/`** - Architecture patterns
    - `tdd/` - Test-Driven Development examples

7. **`design-pattern/`** - Gang of Four patterns
    - Creational, Structural, Behavioral patterns

8. **`misc/`** - Utilities
    - `troubleshooting/` - Logging, profiling, debugging

### Standard Module Structure

Each module follows this layout:

```
module-name/
├── src/
│   ├── main/
│   │   ├── java/          # Java source code
│   │   ├── kotlin/        # Kotlin source code (if applicable)
│   │   └── resources/     # Configuration files, docker-compose.yml
│   └── test/
│       ├── java/          # Java tests
│       ├── groovy/        # Spock tests (Redis module)
│       └── resources/     # Test configuration
└── build.gradle.kts       # Module-specific build configuration
```

### Dependency Management

- **Spring Boot BOM** manages versions for all Spring dependencies
- Root `build.gradle` defines common dependencies (Lombok 1.18.42, JUnit 6.0.1)
- Maven repositories: Aliyun and Tencent mirrors (optimized for Chinese developers)
- Module-specific dependencies declared in individual `build.gradle.kts` files

### Testing Framework

- **Java modules**: JUnit 5 (Jupiter)
- **Redis module**: Spock Framework (Groovy-based BDD)
- **Integration tests**: TestContainers for Redis, MongoDB
- **Async testing**: Awaitility library
- All tests use `useJUnitPlatform()` for execution

## Key Patterns and Conventions

### Package Naming

All code uses `xyz.andornot` as the root package.

### Code Patterns

1. **Repository Pattern**: Spring Data repositories for data access across all modules (JPA, MongoDB, Redis)

2. **Configuration Classes**: Externalized in `src/main/resources/application.yml` or `.properties`

3. **Docker Integration**: Modules requiring external services include `docker-compose.yml` in `src/main/resources/`

4. **Application Entry Points**: Simple `@SpringBootApplication` classes in root package

### Testing Patterns

- Test classes named `*Test.java` or `*Spec.groovy`
- Integration tests start external services via TestContainers
- Use `@SpringBootTest` for full Spring context integration tests
- Mock-based unit tests for service layer

## Docker Services

Several modules require Docker services for testing/running:

```bash
# Start Redis (from middleware/redis/src/main/resources/)
cd middleware/redis/src/main/resources
docker-compose up -d

# Start MongoDB (from db/mongo/src/main/resources/)
cd db/mongo/src/main/resources
docker-compose up -d

# Start Elasticsearch (from db/elasticsearch/src/main/resources/)
cd db/elasticsearch/src/main/resources
docker-compose up -d

# Stop services
docker-compose down
```

**Note**: Navigate to the specific `src/main/resources/` directory before running `docker-compose` commands.

## Important Build Details

### Gradle Configuration

- Parallel builds enabled (`org.gradle.parallel=true`)
- Build cache enabled (`org.gradle.caching=true`)
- JVM args: `-Xmx2g -Dfile.encoding=UTF-8`
- Gradle wrapper version: 9.2.1

### Java Version

- Requires **Java 25** (toolchain version 25)
- Uses modern features: Virtual Threads, Scoped Values, Pattern Matching

### Profile-Based Configuration

Spring modules support multiple profiles:

- HikariCP connection pool: `--spring.profiles.active=hikari`
- Druid connection pool: `--spring.profiles.active=druid`

Example:

```bash
./gradlew :framework:spring:connection-pool:bootRun --args='--spring.profiles.active=hikari'
```

## Common Development Workflows

### Adding a New Module

1. Create directory under appropriate category (`framework/`, `orm/`, etc.)
2. Add `build.gradle.kts` with module-specific dependencies
3. Include in parent module's `settings.gradle.kts`
4. Follow standard `src/main/{java,kotlin,resources}` structure
5. Add tests in `src/test/`
6. Update main README.md with module description

### Working with Database Modules

1. Start required Docker services first
2. Check `application.yml` for connection details (usually `localhost` with standard ports)
3. Run tests to verify setup: `./gradlew :db:mongo:test`
4. Integration tests will automatically start TestContainers if configured

### Testing TDD Examples

The `arch:tdd` module contains complete TDD workflow examples:

```bash
./gradlew :arch:tdd:test
```

Demonstrates:

- Course distance calculation
- Scholarship system logic
- Student registration with controller/service/repository layers

## Module Dependencies

Most modules are **independent** with minimal cross-module dependencies. This allows:

- Parallel development and testing
- Isolated feature exploration
- Independent version upgrades

**Exception**: Some Spring modules may share common Spring Boot parent configuration.

## Troubleshooting

### Build Failures

1. Verify Java 25 is installed: `java -version`
2. Clean and rebuild: `./gradlew clean build`
3. Check Docker services are running for integration tests
4. Ensure Gradle daemon has sufficient memory (2GB configured)

### Test Failures

1. Check if Docker services are required and running
2. Verify `application.yml` configuration matches environment
3. Run specific test in isolation: `./gradlew :module:test --tests TestClassName`
4. Check for port conflicts (Spring Boot default: 8080)

### Module Not Found

1. Verify module is included in `settings.gradle.kts`
2. Run `./gradlew projects` to list all available modules
3. Use exact module path: `:framework:spring:web` (not `framework/spring/web`)