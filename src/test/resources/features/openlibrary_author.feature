Feature: OpenLibrary Author API validation

  Scenario: Validate author personal name and alternate names
    Given the OpenLibrary author endpoint is available
    When I send a GET request to the author endpoint
    Then the response status code should be 200
    And the response content type should be JSON
    And the "personal_name" field should be "Sachi Rautroy"
    And the "alternate_names" list should contain "Yugashrashta Sachi Routray"
