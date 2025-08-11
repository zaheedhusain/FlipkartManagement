Feature: Booking Form Functionality

  Scenario: Successful booking with valid inputs
    Given launch the browser
    And navigate to booking form
    When Fill valid inputs in fields
      | First name | Last name | Phone Number| Email ID | Event Type | Event Date | Event Time | Guest Count | Catering Service | Address | City    | Pincode | Event Details |
      | husain     | shaik  | 1234567891 | husain@gmail.com   | Wedding | 24/11/2025 | 10.30pm | 50    | Veg  | 9-42/1,chennai  | Chennai | 522203  | welcome |
    And click submit button
    Then shows result table

  Scenario: Submission blocked when required fields are empty
    Given launch the browser
    And navigate to booking form
    When Leaves all required fields empty
    And click submit button
    Then display error message

  Scenario: Booking with invalid input formats
    Given launch the browser
    And navigate to booking form
    When Enter invalid data in fields
      | First name | Last name | Phone Number| Email ID | Event Type | Event Date | Event Time | Guest Count | Catering Service | Address | City    | Pincode | Event Details |
      | Husain    | Shaik  | 1236   | husain@gmail.com   | Weddi | 24/11/2025 | 10.30pm | 50    | Veg  | 9-42/1,chennai  | Chennai | 52220e323 | welcome |
  	And click submit button
    Then display error message