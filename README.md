# Introduction
The framework is about automating a specified test scenario which is located in the project test directory with [following path](src/test/java/autotests/e2eTests/E2ETest.java)

The test scenario is the following:
1. Navigate to Picsart Search. 
2. Click on the filter button and verify that the filters disappear.
3. Click on the filter button again to open the filters.
4. Choose the "Personal" checkbox from the “License” section and verify that there are no “PLUS” assets. Hovering over an asset should display the like, save, and try now buttons.
5. Click on the like button and verify that the sign-in popup appears.
6. Close the popup.
7. Remove the filter.
8. Hover over a “PLUS” asset and verify that only the “try now” button appears.
9. Click on the “try now” button and verify that the editing screen opens with the image applied to the canvas.

## Instructions how to run the tests
Run each test method individually as running the whole test class results in an issue with accepting the cookies. After first test method, 
the next 2 do not contain the cookie popup at the bottom.

Run the following methods from the E2ETest class **individually**
```Java
public void picsartEditorTestScenarioResolution1()
public void picsartEditorTestScenarioResolution2()
public void picsartEditorTestScenarioResolution3()
```

