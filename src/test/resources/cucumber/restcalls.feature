Feature: Depositing money in to a User account
 
Scenario Outline: Accessing server pages with different devices should returned properly formatted content
Given a user has the phone number '<msisdn>'
When accessing the json service '<page_uuid>'
And sending the parameter '<param>'
Then the content returned should have the keys '<keys>'


Examples:

|msisdn       | page_uuid    | param           | keys       |
|555555555    | test_phones  | lid=A-xxxxxxxxx | A-xxxxxxxxx|
