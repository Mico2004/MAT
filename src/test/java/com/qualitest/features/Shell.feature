Feature: Shell Tests


@placeholder
Scenario: MINT-15695 - Verify default user picture when user doesn't have a picture defined
	                    Given I login as "WithoutProfilePic"
	                    Then I verify "default" picture is displayed
	                    Then I verify the "WithoutProfilePic" value is displayed in the username field


@placeholder
Scenario: MINT-15696 - Verify a user picture when a user does have a picture defined
	                    Given I login as "WithProfilePic"
	                    Then I verify "user custom" picture is displayed
	                    Then I verify the "WithProfilePic" value is displayed in the username field


@placeholder
Scenario: MINT-15691 - Verify username field when login with a user without a fullname fallback to username
                        Given I login as "OnlyUsername"
                        Then I verify "default" picture is displayed
                        Then I verify the "OnlyUsername" value is displayed in the username field


@placeholder
Scenario: MINT-15691 - Verify username field when login with a user with both user name and fullname displays the fullname
                        Given I login as "bothUsernameAndFullname"
                        Then I verify "default" picture is displayed
                        Then I verify the "fullname" value is displayed in the username field


@placeholder
Scenario Outline: MINT-15004 - Verify that the Login page supports all acceptable login characters
                        Given I login as "<username>"
                        Then I verify "default" picture is displayed
                        Then I verify the "<username>" value is displayed in the username field
                        And I sign out

                                                           Examples:
                                                                         | username |
                                                                         | keyboardspecialCharacters |
                                                                         | undescore |
                                                                         | mailformat |
                                                                            | longstring |


@placeholder
Scenario: MINT-15860 - Verify that the user name isn't case sensitive and the password is case sensitive
                      And I login with "nonadmin" user
                      Then I verify login failed


@placeholder
Scenario: MINT-14995 - Verify that a non admin user type login fails
                        Given I open the MAT login page
                        And I login with "MixedCaseUserAndPass" user with username in "lowercase" and password in "original case"
                        Then I verify i logged in successfully
                        And I sign out
                        And I login with "MixedCaseUserAndPass" user with username in "original case" and password in "lowercase"
                        Then I verify login failed


@placeholder
Scenario:  MINT-14995 - Verify MAT-to-Login-to-MAT redirections
                        Given I open the offer setup page
                        Then I verify MAT login page is loaded successfully
                        Then  I login as "bothUsernameAndFullname"
                        And I sign out





















