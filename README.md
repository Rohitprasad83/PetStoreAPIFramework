# Pet Store API Testing Framework

## Overview
This is an automated testing framework for the Pet Store API, built using REST Assured, TestNG, and Java. The framework implements various test scenarios covering pets, users, and store endpoints with extensive reporting capabilities.

## Features
- REST API testing using REST Assured
- Data-driven testing approach
- Extensive logging using Log4j2
- Detailed reporting using Extent Reports
- Test execution management with TestNG
- Random test data generation using Java Faker
- Support for JSON schema validation
- Modular and maintainable test structure

## Project Structure

PetStoreAPIFramework/
├── src/
│   └── test/
│       ├── java/
│       │   └── api/
│       │       ├── endpoints/    # API endpoint configurations
│       │       ├── payload/      # POJO classes for request/response
│       │       ├── utilities/    # Helper classes and utilities
│       │       └── test/         # Test classes
│       └── resources/
│           ├── log4j2.xml       # Logging configuration
│           └── testng.xml       # Test suite configuration
├── logs/                        # Test execution logs
├── test-output/                 # Test reports
└── pom.xml                      # Project dependencies

## Prerequisites
- Java JDK 11 or higher
- Maven 3.8.x or higher
- IDE (IntelliJ IDEA recommended)

## Dependencies
- REST Assured 5.5.0
- TestNG 7.10.2
- Log4j2 2.24.3
- Extent Reports
- Java Faker 1.0.2
- JSON 20250107
- Gson 2.11.0

## Installation & Setup
1. Clone the repository
2. Open the project in your IDE
3. Install Maven dependencies:

bash
mvn clean install

## Running Tests
### Using Maven

bash
mvn test


### Using TestNG XML
Run the `testng.xml` file directly from your IDE or using Maven:

bash
mvn test -DsuiteXmlFile=testng.xml

## Test Categories
1. Pet Operations
   - Create, update, and delete pets
   - Upload pet images
   - Get pets by status
   - Validate pet data

2. Store Operations
   - Place orders
   - Get order details
   - Delete orders

3. User Operations
   - User registration
   - Login/Logout
   - Update user details
   - Delete users

## Reporting
- Extent Reports are generated in `test-output/ExtentReport.html`
- Log files are generated in `logs/automation.log`

## Logging
The framework uses Log4j2 for logging with the following configurations:
- Log Level: INFO
- Log Pattern: Date, Time, Level, Class, Line Number, Message
- Rolling File Appender with daily rollover

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## Best Practices
- Use meaningful test names
- Implement proper assertions
- Maintain test independence
- Follow page object model principles
- Regular code reviews
- Keep tests maintainable and readable

## Author
Rohit Prasad