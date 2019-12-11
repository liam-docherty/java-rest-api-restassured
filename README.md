# java-rest-api-restassured

## TODO:
* Query parameter example
* Request specification - TestAutomationU Chapter 4
* Response specification - TestAutomationU Chapter 4
* Extracting common values from response - TestAutomationU Chapter 4 (useful example is grabbing an authentication token and using it in subsequent requests or a POST then GET where the POST returns an ID)
* Deserialisation - TestAutomationU Chapter 6

## pom.xml
Within the configuration of the `maven-compiler-plugin`, the expected version of Java is set to 11.

## Running Tests
To run all tests, in the terminal simply run:<br>
`mvn test`

The list of included tests is specified within the `pom.xml` under the plugin configuration.

You can refer to this stack overflow question for how to run a sub set of tests:<br>
https://stackoverflow.com/questions/6819888/how-to-run-all-tests-in-a-particular-package-with-maven

## Miscellaneous
When creating entities, the todoist API has issues processing the following characters. As this project is simply an example, it will ignore these characters:
* `@`
* `<`
* `>`
