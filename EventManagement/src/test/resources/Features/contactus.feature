Feature: Contact Us Form Functionality

  Scenario: Form submission with valid input
  Given launch the browser
  And navigate to contact form URL
  When fill valid data in fields
  And click send message button
  Then success message should be visible


  Scenario: Form submission with empty input
    Given launch the browser
    And navigate to contact form URL
    When leave all fields empty
    And click send message button
    Then error message should be visible
