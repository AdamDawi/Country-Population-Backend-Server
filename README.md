# Country Population Backend Server

Welcome to the **Country Population Backend Server**! This Kotlin-based backend server provides information on country populations and their flag images. Built using Ktor and a range of essential plugins, this server is designed to deliver efficient data retrieval for all your country-related needs.

## ⭐️ Features

### Endpoints
- **Get All Countries:** Retrieve a comprehensive list of all countries, including their populations and flag images.
  
- **Search Countries:** Find a country by its name with a dedicated search endpoint.

- **Flag Images:** Access flag images for any country through a dedicated endpoint (e.g., `/images/pl.png`).

## ⚙️ Technologies

### 📦 Project:
- **Ktor:** A powerful framework for building web applications and APIs in Kotlin. Utilized for server setup, routing, and content negotiation.

- **Koin:** Dependency Injection framework used to manage dependencies throughout the project.

- **Kotlinx Serialization:** Simplifies the serialization and deserialization of data between JSON and Kotlin objects.

- **Default Headers Plugin:** Automatically adds default headers, including cache control, to all responses.

- **Status Pages Plugin:** Handles various HTTP status codes and exceptions in a streamlined manner.

- **Logback:** Logging framework integrated for monitoring and debugging.

### ✅ Testing:
- **Ktor Server Tests:** Framework used for testing server-side logic and endpoints.

- **Kotlin Test:** Standard testing library used to write unit tests and verify functionality.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AdamDawi/Country-Population-Backend-Server
   ```
2. Open the project in your preferred IDE.
3. Ensure that the versions in gradle match those specified in the project.
4. Run the server on http://localhost:8080/

## Overview
Here are some examples of how the API endpoints work:

### Get All Countries
```http
GET /countries
```
Returns a list of all countries with their populations and flag image URLs.

### Search Country
```http
GET /countries/search?name=CountryName
```
Returns details for the specified country.

### Flag Image
```http
GET /images/{countryCode}.png
```
Returns the flag image for the specified country code.

## Example Responses
- Get All Countries:
```json
[
  {
    "name": "Poland",
    "population": 38386000,
    "flagUrl": "/images/pl.png"
  },
  {
    "name": "Germany",
    "population": 83783942,
    "flagUrl": "/images/de.png"
  }
  ...
]
```
- Search Country:
```json
{
  "name": "Poland",
  "population": 38386000,
  "flagUrl": "/images/pl.png"
}
```

## Status Codes

Server returns the following status codes in its API:

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

## Author

Adam Dawidziuk🧑‍💻
