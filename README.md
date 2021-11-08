Upgrage Test Assessment


This test repository divided into 2 parts : 

1. UI Tests
   This used Java, Selenide(which internally uses Selenium), TestNg
2. API Tests
   For this i have used JAVA, TestNG




Setup and Requirements :  

I have would recommend have a setup of Java 9. 
I have used Intellij to code this project so would recommend using the same. 

Run Following commands  :

1. Download the project file.

``git clone <project repo>``

2. To download all the dependencies run following command : 

`` maven clean install``



Running UI and API tests Individually 

``NOTE : I didnt had time to properly setup the XML Suites to run just one file to run both UI and API test suites using this : ``


RegressionTestSuite.xml

``<suite-file path="/UITestSuite.xml" />
<suite-file path="/UIE2ETestSuites.xml" />
<suite-file path="/ApiRegressionTestSuite.xml" />``




``Anyways : So for now you need to run each test files individually : (Right click and run the tests )``

1. NonDMFunnelLandingPageUITests.java
2. DMFunnelUITests [E2E tests]
3. BRFunnelApiTests


API and UI has various bugs which i would have pointed out when i get API or UI for testing. 


UI Testing bugs or Improvements : 

1. Elements should be provided with `test-data-id` which can search and resolve testing easily. Also in future if the element location is changed that
would not break the automation. 

2. UI pages are not properly divided various places it had unused/unwanted divisions.


API Testing bugs : 
There could be many bugs but giving it around 4-5 hours to test and automation project from scratch more possibility to miss the bugs. 

1. When LoanAPPID is given with any random UUID it results in 500. Rather it should throw 400 Error. 
2. When LoanAPPID parameter missing from the body also results in 500 rather it should throw 400 Error. 
3. 