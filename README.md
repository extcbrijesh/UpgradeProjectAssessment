## Upgrade Test Assessment


### Test repository divided into 2 Test Modules : 

1. UI Module
   This used Java, Selenide(which internally uses Selenium), TestNg
2. API Module
   JAVA and TestNG were used to write the tests. And Maven used as build tools.

### Requirements :  

1. Java 9
2. Intellij
3. Maven

### SETUP:

#### Run Following commands  :

1. Download the project file from git.

``git clone https://github.com/extcbrijesh/UpgradeProjectAssessment.git``

2. To download all the dependencies run following command : 

`` maven clean install``

#### Running UI and API tests Individually via XML Suite

UI Tests
1. ``mvn clean test -DsuiteXmlFile=UITestSuite.xml``

2. ``mvn clean test -DsuiteXmlFile=UIE2ETestSuites.xml``

API Tests : 

``mvn clean test -DsuiteXmlFile=ApiRegressionTestSuite.xml``


### Suggestion and Recommendations to UI and API Developers : 

#### UI bugs or Improvements:

1. Elements should be provided with `test-data-id` which can search and resolve testing easily. Also in future if the element location is changed that
would not break the automation. 

2. UI pages are not properly divided various places it had unused/unwanted divisions.


#### API Testing bugs : 
There could be many bugs but giving it around 4-5 hours to test and automation project from scratch more possibility to miss the bugs. 

1. When LoanAPPID is given with any random UUID it results in 500. Rather it should throw 400 Error. 
2. When LoanAPPID parameter missing from the body also results in 500 rather it should throw 400 Error.