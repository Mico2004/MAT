Feature: Create New
    
@rft
Scenario: MINT-15510 - Verify creating an offer with a single resource and instructor sub offer
                        Given I login as "bothUsernameAndFullname"
                        And I click on Create New tab
                        And I name the offer - "unique"
                        And I add a resource
                        | Title    | ID     | ResourceType |
                        | default  | unique |  default     |

						And I add an Offer
						|  type      | status | individual | buisness | duration | isbn   |
						| instructor | off    | instructor | he       | default  | default|

						Then I click on Save Offer
						And I search for the unique id
						And I click the offer link
						Then I verify the "resources" rows count is correct
						Then I verify the "offers" rows count is correct
						Then I verify the "resources" cells content is correct
						Then I verify the "offers" cells content is correct