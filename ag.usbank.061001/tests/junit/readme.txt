UP9 - Automatically Generated Java (JUnit) Tests


Requirements:

Apache Maven needs to be installed on your machine: https://maven.apache.org/install.html

Running the tests:

`mvn clean test -DtrimStackTrace=false`

Running a single test:

`mvn clean test -DtrimStackTrace=false -Dtest=TestsClassNameTest#testMethodName`

Generating the Surefire HTML report:

`mvn surefire-report:report-only`
