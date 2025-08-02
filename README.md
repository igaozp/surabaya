<div id="top"></div>

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
![GitHub top lang](https://img.shields.io/github/languages/top/igaozp/surabaya?style=for-the-badge)

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/igaozp/surabaya">
    <img src="images/logo.png" alt="Logo">
  </a>

[//]: # (  <h3 align="center">surabaya</h3>)

  <p align="center">
    A comprehensive repository of modern JVM ecosystem code examples and best practices!
    <br />
    一个全面的现代 JVM 生态系统示例代码和最佳实践库！
    <br />
    <a href="#getting-started"><strong>Get Started »</strong></a>
    <br />
    <br />
    <a href="#module-list">View Examples</a>
    ·
    <a href="https://github.com/igaozp/surabaya/issues">Report Bug</a>
    ·
    <a href="https://github.com/igaozp/surabaya/issues">Request Feature</a>
  </p>
</div>

## Table of Contents

- [About](#about)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Module List](#module-list)
- [Usage Examples](#usage-examples)
- [Contributing](#contributing)
- [License](#license)

## About

Surabaya is a comprehensive collection of code examples, patterns, and best practices for the JVM ecosystem. It covers
modern Java features, popular frameworks, design patterns, databases, middleware, and architectural patterns.

### Key Features

- **Modern Java**: Examples using Java 23 with GraalVM, Virtual Threads, Scoped Values, and latest language features
- **Popular Frameworks**: Spring Boot 3.5+, Quarkus, Helidon with real-world examples
- **Design Patterns**: Complete implementation of GoF patterns with practical use cases
- **Database Integration**: JPA, MyBatis, ShardingSphere, MongoDB, Elasticsearch examples
- **Testing**: TDD examples with comprehensive test coverage
- **Performance**: Connection pooling, caching, reactive programming examples
- **DevOps Ready**: Docker Compose configurations and production-ready setups

## Getting Started

### Prerequisites

- **Java 23** (GraalVM recommended) - [Download](https://www.graalvm.org/)
- **Gradle 9.0+** (included via wrapper)
- **Docker & Docker Compose** (for database examples)

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/igaozp/surabaya.git
   cd surabaya
   ```

2. Build the project
   ```bash
   ./gradlew build
   ```

3. Run tests
   ```bash
   ./gradlew test
   ```

4. Start specific module (example)
   ```bash
   ./gradlew :framework:spring:web:bootRun
   ```

## Module List

### 🔤 Language Features (`lang`)

Modern JVM language examples and best practices

#### Java (`lang:java`)

- **Concurrent Programming**: Virtual Threads, Scoped Values, ThreadLocal examples
- **File I/O**: RandomAccessFile and modern file operations
- **Records**: Modern data class examples
- **SPI**: Service Provider Interface implementations
- **Streams**: Parallel processing and stream operations

#### Kotlin (`lang:kotlin`)

- **Coroutines**: Basic usage, cancellation, composition, context management
- **Flow**: Reactive stream processing
- **Interop**: Java-Kotlin integration patterns

### 🚀 Frameworks (`framework`)

Production-ready framework examples with modern patterns

#### Helidon (`framework:helidon`)

- **Microservices**: RESTful services with reactive programming
- **Configuration**: YAML-based application configuration
- **Testing**: Comprehensive test examples

#### Quarkus (`framework:quarkus`)

- **Native Compilation**: GraalVM native image examples
- **Virtual Threads** (`framework:quarkus:virtual-threads`): Project Loom integration
- **Reactive**: Non-blocking I/O patterns
- **Person API**: Complete CRUD operations with async/await patterns

#### Spring (`framework:spring`)

- **Spring Boot Next** (`framework:spring:next`): Latest Spring Boot 3.5+ features
- **Transaction Management** (`framework:spring:transaction`): Declarative and programmatic transactions
- **Web Development** (`framework:spring:web`): RESTful APIs and JSON streaming
- **Reactive Programming** (`framework:spring:reactive`): WebFlux with functional routing
- **Connection Pooling** (`framework:spring:connection-pool`): HikariCP and Druid configurations

### 🔧 Middleware (`middleware`)

Essential middleware and caching solutions

#### Redis (`middleware:redis`)

- **Vector Similarity Search**: AI/ML vector operations
- **Basic Operations**: CRUD operations and data structures
- **Configuration**: Production-ready Redis setup with Docker

#### Caffeine (`middleware:caffeine`)

- **In-Memory Caching**: High-performance local caching
- **Cache Strategies**: TTL, size-based eviction, and refresh patterns

### 🗄️ Database & ORM (`orm`, `db`)

Database integration patterns and ORM examples

#### JPA (`orm:jpa`)

- **Entity Management**: Complete CRUD operations
- **Repositories**: Spring Data JPA patterns

#### MyBatis (`orm:mybatis`)

- **SQL Mapping**: XML and annotation-based mapping
- **Dynamic SQL**: Conditional query generation
- **Testing**: Database integration testing

#### ShardingSphere (`orm:shardingsphere`)

- **Database Sharding**: Horizontal scaling patterns
- **Read/Write Splitting**: Master-slave configurations

#### MongoDB (`db:mongo`)

- **Document Operations**: CRUD with Spring Data MongoDB
- **Repository Patterns**: Custom query methods

#### Elasticsearch (`db:elasticsearch`)

- **Search Operations**: Full-text search and aggregations
- **Client Configuration**: High-level REST client setup

### 🎨 Design Patterns (`design-pattern`)

Complete Gang of Four patterns with practical examples

#### Creational Patterns

- **Abstract Factory**: GUI component families
- **Builder**: Complex object construction with Car/Manual examples
- **Factory Method**: UI dialog creation patterns
- **Prototype**: Shape cloning and manipulation
- **Singleton**: Thread-safe singleton implementations

#### Structural Patterns

- **Adapter**: Shape compatibility layers
- **Bridge**: Device-remote control abstraction
- **Composite**: Hierarchical shape compositions
- **Decorator**: Data compression and encryption
- **Facade**: Video conversion simplification
- **Flyweight**: Memory-efficient tree rendering
- **Proxy**: YouTube video caching

#### Behavioral Patterns

- **Chain of Responsibility**: Authentication middleware
- **Command**: Text editor operations with undo/redo
- **Visitor**: Shape export operations

### 🏗️ Architecture (`arch`)

Software architecture and development practices

#### TDD (`arch:tdd`)

- **Test-Driven Development**: Complete TDD workflow examples
- **Course Management**: Distance calculation and validation
- **Scholarship System**: Complex business logic with comprehensive testing
- **Student Registration**: Controller, service, and repository testing patterns

### 🔧 Miscellaneous (`misc`)

#### Troubleshooting (`misc:troubleshooting`)

- **Logging**: Structured logging and debugging techniques
- **Profiling**: Performance monitoring and optimization
- **Debugging**: Development and production debugging strategies

## Usage Examples

### Running Specific Modules

```bash
# Spring Boot Web application
./gradlew :framework:spring:web:bootRun

# Quarkus application with Virtual Threads
./gradlew :framework:quarkus:virtual-threads:quarkusDev

# TDD examples
./gradlew :arch:tdd:test

# Design pattern examples
./gradlew :design-pattern:run
```

### Docker Examples

```bash
# Start Redis with Vector Search
cd middleware/redis/src/main/resources
docker-compose up -d

# Start MongoDB
cd db/mongo/src/main/resources
docker-compose up -d

# Start Elasticsearch
cd db/elasticsearch/src/main/resources
docker-compose up -d
```

### Testing

```bash
# Run all tests
./gradlew test

# Run specific module tests
./gradlew :framework:spring:test
./gradlew :orm:jpa:test

# Run tests with coverage
./gradlew test jacocoTestReport
```

## Contributing

We welcome contributions! Here's how you can help:

### Development Workflow

1. **Fork the repository**
   ```bash
   git clone https://github.com/yourusername/surabaya.git
   cd surabaya
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Make your changes**
    - Follow existing code style and patterns
    - Add comprehensive tests for new features
    - Update documentation as needed

4. **Test your changes**
   ```bash
   ./gradlew test
   ./gradlew build
   ```

5. **Commit and push**
   ```bash
   git add .
   git commit -m "feat: add amazing feature"
   git push origin feature/amazing-feature
   ```

6. **Create a Pull Request**

### Coding Standards

- **Java Style**: Follow standard Java conventions
- **Testing**: Aim for >80% test coverage
- **Documentation**: Include JavaDoc for public APIs
- **Commit Messages**: Use conventional commits format
- **Dependencies**: Keep dependencies up to date

### Adding New Examples

When adding new examples:

1. Create appropriate module structure under relevant category
2. Include comprehensive README in module directory
3. Add Docker Compose files for external dependencies
4. Write both unit and integration tests
5. Update main README.md with new module information

### Architecture Guidelines

- **Modularity**: Keep examples focused and self-contained
- **Dependencies**: Minimize cross-module dependencies
- **Configuration**: Use externalized configuration (YAML/Properties)
- **Testing**: Include both positive and negative test cases
- **Documentation**: Provide clear setup and usage instructions

<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p style="text-align: right;">(<a href="#top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/igaozp/surabaya.svg?style=for-the-badge
[contributors-url]: https://github.com/igaozp/surabaya/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/igaozp/surabaya.svg?style=for-the-badge
[forks-url]: https://github.com/igaozp/surabaya/network/members
[stars-shield]: https://img.shields.io/github/stars/igaozp/surabaya.svg?style=for-the-badge
[stars-url]: https://github.com/igaozp/surabaya/stargazers
[issues-shield]: https://img.shields.io/github/issues/igaozp/surabaya.svg?style=for-the-badge
[issues-url]: https://github.com/igaozp/surabaya/issues
[license-shield]: https://img.shields.io/github/license/igaozp/surabaya.svg?style=for-the-badge
[license-url]: https://github.com/igaozp/surabaya/blob/master/LICENSE.txt

[github-top-lang]: https://img.shields.io/github/languages/top/igaozp/surabaya?style=for-the-badge

