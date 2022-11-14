mvn clean && mvn package -DskipTests
docker build . -t config-service
