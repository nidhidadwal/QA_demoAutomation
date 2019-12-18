mvn clean package

java -cp target/classes:target/DemoTestScenarios/WEB-INF/lib/* testcases.TestKBSearches "demo" "IT Service Desk" "IT Service Desk Webchat"

