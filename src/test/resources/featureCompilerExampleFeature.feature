@Feature @Example
Feature: Guess the word

# The first example has two steps
@Scenario1 @Example
Scenario: Maker starts a game
When the Maker starts a game
Then the Maker waits for a Breaker to join

# The second example has three steps
@Scenario2
Scenario: Breaker joins a game
Given the Maker has started a game with the word "silky"
When the Breaker joins the Maker's game
Then the Breaker must guess a word with 5 characters

@ExampleTable
Scenario: Correct non-zero number of books found by author
Given I have the following books in the store
|The Devil in the White City|Erik Larson|
|The Lion, the Witch and the Wardrobe|C.S. Lewis|
|In the Garden of Beasts|Erik Larson|
When I search for books by author Erik Larson
Then I find 2 books