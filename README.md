Test scripts and all object definition are maintained under src/test/java folder.
The package com.digital.Adams contains test scripts under the class "RestApiTestCases"
The package com.ojectModel contains the keys(objects) for login api

Test case descrition:
- Test case 1 ("login_Successful") checks whether the user is able to login sucessfully and the status code is 201
- Test case 2 ("login_Unsuccessful") checks whether the user is unable to login on providing invalid credentials and the status code is 400

To ensure correctness/completeness, the following two test cases have been prepared (these will be the actual test cases)
- Test case 3 ("login_Successful_additional") checks whether the user is able to login sucessfully with status code 201 and also validate response schema 
- Test case 4 ("login_Unsuccessful_additional") checks whether the user is unable to login on providing invalid credentials with status code is 400 and also validate response schema 
