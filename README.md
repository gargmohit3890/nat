# Calculate prime number from the supplied number
This rest api exposes single endpoint to calculate the primeNumbers for a given number.
The API contract is as below:

VERB/METHOD: GET

URI: /assignment/v1/primenumbers

Query Param: 
1. number: Integer: positive integer greater than 1
2. algorithm: String Enum: [ JAVA8, SOE ]

Response: [integer]

Port: 8080

Swagger url: http://localhost:8080/assignment/v1/swagger-ui/index.html


