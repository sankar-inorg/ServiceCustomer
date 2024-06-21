Ctrl + B
@SpringBootApplication
    - @SpringBootConfiguration
    - @EnableAutoConfiguration
    - @ComponentScan 

MVC - Model View Controller

Layered Architecture
    - Controller (@RestController) - Where we write the APIs (Get, Post, Patch, Put, Delete)
    - Service (@Service) - Business Logic
    - Repository (@Repository) - Interact with the Database
    - Models (@Entity) - Structure the Entity to store in the Database
    - DTOs - Data Transfer Objects

Controllers:
    - @RequestBody - JSON Payload
    - @PathVariable - Fetch URI Path Parameters
    - @RequestParam - Query Parameters

@RequestMapping
    - @PostMapping - PUT
    - @GetMapping - GET
    - @PutMapping - POST
    - @PatchMapping - PATCH
    - @DeleteMapping - DELETE
    - HEAD, OPTIONS

REST - Rest, Statelessness of Rest, Idempotent and Safe Methods
Log Statements - Info, Error, Warning
    - SLF4J - Simple Logging Facade for Java

Static, Final 

Dependency Injection - Injecting the Bean
    - Constructor Injection - Constructor
    - Setter Injection
    - Field Injection
IOC Container - A place where all the Beans are present
Inversion of Control - Handing over the responsibility of creating and injecting the object/bean to the Framework(SpringBoot)
Bean = Object + Extra MetaData

Application.Yaml --> Properties, Port(Default Port - 8080)

Configuration
    - Singleton Design Pattern
    - @Configuration, @ConfigurationProperties
    - @Bean
    - @Data - Getters, Setters, ToString, AllArgsConstructor, NoArgsConstructor

Dependency Management - Build Tool
    - Maven - pom.xml
    - Gradle - build.gradle

Service
    - Service (Interface)
        - Interface: Contract which contains only Abstract Methods, Static & Final Variables/Methods
        - Java 8 Features: 
            - Streams & Lambdas, Functional Interfaces, Date & Time Module, Default Methods
        - Interfaces & Abstract Classes
    - Service Implementation 
        - extends, implements
        - @Override
        - Builder Design Pattern

Global ExceptionHandler 
    - @ControllerAdvice
    - @Exception Handler
    - Exceptions & Custom Exceptions

Commerce Tool: Service Layer Implementation

Create
    - Play with the API - Return Type and Draft - Documentation
    - Model/DTO - JSON PayLoad
    - Draft - DraftBuilder
        - Locale - LocalizedStringBuilder
        - typeID - ResourceIdentifierBuilder

Update
    - Play with the API - Return Type and Draft - Documentation
    - Model/DTO - JSON PayLoad
    - To Fetch the Version - API Call
    - Create the Action --> Action Builder - Use the JSON Payload
    - Update - UpdateBuilder
    - ExecuteBlocking, Execute, SendBlocking
    - Completable Future