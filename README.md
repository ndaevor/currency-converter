# Currency Converter using API

This is a Spring Boot application to:
1. Convert an amount from one currency to another.

## Features
- Currency conversion using real-time exchange rates.

---

## Prerequisites
1. **Java**: Ensure Java 11 or higher is installed.
2. **Maven**: Make sure Maven is installed.
3. **IDE**: Any Java IDE like IntelliJ IDEA, Eclipse, or VS Code.
4. **Exchange Rate API Key**: Obtain an API key from a provider like OpenExchangeRates or another public API.

---

## Running the Application Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/currency-converter.git

2. Navigate to the project directory:
    ```bash
    cd currency-converter

3. Update application.properties: Add your exchange rate API key in the src/main/resources/application.properties file:
    exchange.api.key=your-api-key-here
    exchange.api.url=https://api.exchangerate-api.com/v4/latest/USD

4. Build and run the application:
    ```bash
    mvn spring-boot:run

5. Access the application:
API Base URL: http://localhost:8080/api/v1/currency

## use Postman to test the application as this project does not have a frontend
