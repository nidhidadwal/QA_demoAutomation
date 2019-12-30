mvn clean package

if [ $# -lt 3 ]; then
  echo 1>&2 "$0: not enough arguments"
  exit 2
elif [ $# -gt 3 ]; then
  echo 1>&2 "$0: too many arguments"
  exit 2
fi
java -cp target/classes:target/DemoTestScenarios/WEB-INF/classes:target/DemoTestScenarios/WEB-INF/lib/*:target/DemoTestScenarios/WEB-INF/classes/log4j2.properties com.aisera.qa.automation.ui.TestKBSearches "$1" "$2" "$3"

java $* -jar target/DemoTestScenarios.jar
