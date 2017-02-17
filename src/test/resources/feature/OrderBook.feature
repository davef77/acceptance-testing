Feature: Add Book to Shopping-Basket
    As a book-buyer
    I would like to select a book and add it to my shopping-basket
    So that I can pay for it later

Scenario:
    Given I search for books about "Continuous Delivery"
    And I select a book by "David Farley"

    When I add my selected book to my shopping-basket

    Then I can see the book "Continuous Delivery" listed in my shopping-basket
