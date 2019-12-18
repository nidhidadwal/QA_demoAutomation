mvn clean package

ENV_NAME=$1
APP_NAME=$2
CHANNEL_NAME=$3

echo $ENV_NAME
echo $APP_NAME
echo $CHANNEL_NAME

java -cp target/classes:target/DemoTestScenarios/WEB-INF/lib/* testcases.TestKBSearches "$1" "$2" "$3"

