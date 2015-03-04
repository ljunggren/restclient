Feature: Depositing money in to a User account
 
Scenario Outline: Accessing server pages with different devices should returned properly formatted content
Given a user has a '<devicetype>' device
When accessing the '<servlet>'
And using the filename '<filename>'
Then the content returned should be styled by '<cssfile>'
And transformed by '<xslfile>'

Examples:

|devicetype   | servlet     | filename        | cssfile   | xslfile |
|Nokia        | fileservlet | tablesample.xml | class2.css| class2  |
|SonyEricsson | fileservlet | tablesample.xml | class3.css| class3  |
|default      | fileservlet | tablesample.xml | class1.css| class1  |
|             | fileservlet | tablesample.xml | class1.css| class1  |
|xxxxxxx      | fileservlet | tablesample.xml | class1.css| class1  |