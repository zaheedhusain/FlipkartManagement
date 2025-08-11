Feature: Navigation functionality

Scenario Outline: Checking navigation menu
  Given launch the browser
  And navigate to menu
  When click "<menu>" option
  Then navigates to according page

Examples:
  | menu       |
  | HOME       |
  | ABOUT US   |
  | BOOKING    |
  | CONTACT US |

