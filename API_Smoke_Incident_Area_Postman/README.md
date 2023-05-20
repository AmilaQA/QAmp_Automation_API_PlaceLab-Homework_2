
QAMP - API Testing – HOMEWORK 2
Assignment 1
- Create PlaceLab API Smoke Test in Postman and document in Given-When-Then 
format

- Create postman collection and environment, automate Smoke Test flow and run 
smoke test in 3 iterations.

- Report: Avg. Response time, Duration and Number of passed/failed steps
____________________________________________________________________
Smoke test is a quick and basic test that verifies if the critical functionalities of an application or API are working correctly.

•	SMOKE TEST for PlaceLab Incident Area functionality:

Url for Incident Area page is accessible;
Create new Incident Area report;
Retrieve and check the report details from previously created Incident area;
Delete previously created Incident Area (By ID);
Check if previous deletion was successful.


Given:

An API endpoint for PlaceLab Incident area is available.

Postman collection and environment are created.

When:

Send a request to the PlaceLab API to retrieve valid token that is going to be used for further actions - POST request.
Send a request to create a new Incident Area in the PlaceLab API - POST request. This method is used 3 times to create 3 Incident areas.
Send a request to retrieve the details of all Incident Areas - GET request.
Send a request to retrive the details one of previously created Incident Area by ID. - GET request.
Send a request to delete a specific Incident area.

Then:

Verify that the response for token is successful (200 status code).
Verify that the response for creating a new Incident Area is successful (201 status code) and the returned data is not empty.
Verify that the response for retrieving the details of a specific place is successful (200 status code) and the returned Incident area information matches the requested Incident area ID.
Verify that the response for deleting a specific place is successful (204 status code) and the place no longer exists in the PlaceLab.

Report for Postman run automation tests:

Record the response time for each request.
Track the total duration of the smoke test.
Count the number of passed and failed steps.