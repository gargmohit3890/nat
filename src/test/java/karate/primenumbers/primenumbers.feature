Feature: Hello World

  Background:
    Given url baseUrl
    Given path '/primenumbers'

  Scenario: No query param

    When method GET
    Then status 400

  Scenario: Success- invalid Algo

    * param number = 9
    * param algorithm = 'test'
    When method GET
    Then status 400

  Scenario: Success- JAVA8 Algo

    * param number = 20000000
    * param algorithm = 'JAVA8'
    When method GET
    Then status 200
    And match $.primeNumber[0] == 2

  Scenario: Success- SOE Algo

    * param number = 9
    * param algorithm = 'SOE'
    When method GET
    Then status 200
    And match $.primeNumber[0] == 2

