Feature: Validate Place API's
# Scenerio outline-->Help to read the Examples data if only "scenerio" then it's not possible
# We can add multiple tag in a single test scenerio ex: suppose we add @regression in multiple tag help us to run particular test case only.
@AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with post "POST" request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"
    
     Examples:
     
    | name   |language | address           |
    | AbHouse|Hindi    | Gautam Budha Nagar|
    |BBHouse |Tamil    | Delhi,uttam Nagar |
    
    
    
    @DeletePlace @Regression
    Scenario: Verify if delete place functionality is working             
    
    Given DeletePlace payload 
    When user calls "deletePlaceAPI" with post "POST" request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
      
