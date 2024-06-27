<h1 align="center">
    <span href="">Weather Application API</span>
</h1>

![Badge](https://img.shields.io/github/languages/top/mcarneirobug/weather-spring)
![Badge](https://img.shields.io/github/last-commit/mcarneirobug/weather-spring)

### 💻 About  

The Weather Application API provides real-time weather forecasts for cities globally. This service is designed to be efficient and robust, utilizing the OpenWeatherMap API to fetch weather data.

## 🛠 Features

- [X] **Real-Time Weather Forecasts**: Retrieve up-to-the-minute weather information for any valid city worldwide.
- [X] **Flexible Forecast Options**: Users can request weather forecasts for the next 3 or 5 days.
- [X] **In-memory Database**: Integration with H2 database for lightweight data management and quick setup without the need for external database servers.

## 🚀 Technology Stack

- **Spring Boot**: Simplifies the development of new Spring applications through convention over configuration.
- **H2 Database**: A lightweight in-memory database, perfect for development and testing environments.
- **JUnit 5**: The latest generation of the standard framework in Java for unit testing with built-in support for parameterized tests.
- **Maven**: Dependency management and project lifecycle management.
- **Spring HATEOAS**: Enables the creation of REST APIs that follow the HATEOAS principle, making the API more discoverable and self-descriptive by including hypermedia links with responses.
- **ControllerAdvice**: Centralizes exception handling in Spring Boot applications, allowing for consistent and clean error handling across all controllers.

## ⌨️ Setup and Installation

- Java 17 or newer
- Maven
- An IDE of your choice (e.g., IntelliJ IDEA, Eclipse)

### Running the Application Locally

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/weather-spring.git
   cd weather-spring
   ```
2. **Build the Project**
   ```bash
   mvn clean install
   ```
3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   
## API Usage 🌐

This section provides detailed information on how to interact with the Weather Application API. The API is designed to be intuitive and robust, handling requests efficiently with comprehensive error handling.

### Get Weather Forecast 🌦

Retrieve up-to-date weather forecasts for specified cities, with flexibility in forecasting duration.

- **URL** 🌍

  `api/forecast/{cityName}`

- **Method** 🛠️:

  `GET`
  
- **URL Params** 🔍

  **Required:**

  `cityName=[string]` - The name of the city for which weather data is requested.

  **Optional:**

  `days=[int]` - Number of days for the forecast (valid choices are 3 or 5; default is 3).

### Register City 🏙️

Allows the registration of a new city in the system for which weather data can be fetched.

- **Endpoint** 🌍:

  `api/cities`

- **Method** 🛠️:

  `POST`
  
- **URL Params** 🔍

  **Required:**

  `cityName=[string]` - The name of the city to register.

### Get City Details Endpoint 🌆

This endpoint retrieves detailed information about a city by its unique identifier (ID). 

- **Endpoint** 🌍:

   `api/cities/{id}`

 - **Method** 🛠️:
   
     `GET`

- **URL Params** 🔍

  **Required:**

  `id=[number]` - The unique identifier of the city.

### Examples Usage 💡

```bash
# Fetch a 5-day weather forecast for London
curl -X GET "http://localhost:8080/api/forecast/London?days=5"
```

```bash
# Register a new city
curl -X POST "http://localhost:8080/api/cities?cityName=London"
```

```bash
# Get registered city by id 
curl -X GET "http://localhost:8080/api/cities/1" -H "Accept: application/json"
```

```bash
# List all registered cities
curl -X GET "http://localhost:8080/api/cities"
```

# Environment Variables Configuration Guide

This document outlines the environment variables used in the Weather Application API, detailing their purpose, how to set them up in different operating systems, and how they are integrated into the application's configuration with default values.

## List of Environment Variables

### Application Configuration

- **SPRING_APPLICATION_NAME**: The name of the Spring Boot application.
- **SPRING_DATASOURCE_URL**: JDBC URL for connecting to the database.
- **SPRING_DATASOURCE_DRIVERCLASSNAME**: The class name of the JDBC driver.
- **SPRING_DATASOURCE_USERNAME**: Username for database access.
- **SPRING_DATASOURCE_PASSWORD**: Password for database access.
- **SPRING_JPA_HIBERNATE_DDL_AUTO**: Hibernate configuration for schema management.
- **WEATHER_API_URL**: Base URL for the OpenWeatherMap API.
- **WEATHER_API_KEY**: API key for authenticating requests to the OpenWeatherMap API.

## Setting Environment Variables

### macOS/Linux

Open your terminal and input the following commands to set the environment variables for the session:

```bash
export SPRING_APPLICATION_NAME=weather
export SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
export SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
export SPRING_DATASOURCE_USERNAME=sa
export SPRING_DATASOURCE_PASSWORD=
export SPRING_JPA_HIBERNATE_DDL_AUTO=update
export WEATHER_API_URL=https://api.openweathermap.org/data/2.5/
export WEATHER_API_KEY=your-api-key-here
```

### Windows

```bash
set SPRING_APPLICATION_NAME=weather
set SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
set SPRING_DATASOURCE_DRIVERCLASSNAME=org.h2.Driver
set SPRING_DATASOURCE_USERNAME=sa
set SPRING_DATASOURCE_PASSWORD=
set SPRING_JPA_HIBERNATE_DDL_AUTO=update
set WEATHER_API_URL=https://api.openweathermap.org/data/2.5/
set WEATHER_API_KEY=your-api-key-here
```

### Configuring Environment Variables in IntelliJ IDEA 🛠️

When developing with IntelliJ IDEA, it's straightforward to set up environment variables directly in your IDE. Follow these steps to configure your environment variables within IntelliJ IDEA:

-  Step-by-Step Guide

1. **Open Your Project**: Start IntelliJ IDEA and open your project where the Spring Boot application is located.

2. **Edit Configurations**: Navigate to the **Run** menu at the top of the IDE, then select **Edit Configurations...** from the dropdown menu.

3. **Select Your Application**: In the left panel of the Run/Debug Configurations dialog, select your Spring Boot application configuration. If it’s not already there, you may need to add it by clicking the `+` icon and selecting **Spring Boot**.

4. **Set Environment Variables**: In the configuration settings, find the **Environment variables** field. Click the browse button (the icon with three dots) to open the **Environment Variables** dialog.

5. **Enter the Variables**: In the dialog that appears, you can add your environment variables. Use a semicolon `;` to separate different variables. Here is an example of how to format them:

```bash
SPRING_DATASOURCE_USERNAME=sa;SPRING_DATASOURCE_PASSWORD=;SPRING_JPA_HIBERNATE_DDL_AUTO=update;WEATHER_API_URL=https://api.openweathermap.org/data/2.5/;WEATHER_API_KEY=your-api-key-here
```

### :bust_in_silhouette: Author

* Matheus Santos Rosa Carneiro - [mcarneirobug](https://github.com/mcarneirobug)
