mvn clean package

export ENV_NAME='dev1'
export APP_NAME="IT Help Desk"
export CHANNEL_NAME="Nik webchat 2"

java -cp target/classes:target/DemoTestScenarios/WEB-INF/lib/* testcases.TestKBSearches "dev1" "IT Helpdesk" "Nik webchat 2"

