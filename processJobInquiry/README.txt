Either run
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql/mysql-server:latest
or, in case there is already a mysql db on localhost, change the ip stored in dbHost in the class MySQLConnector

Then execute
docker run -d --name camunda -p 8080:8080 camunda/camunda-bpm-platform:latest

Generate a war by running Maven install on the processJobInquiry project and copy the war file into the camunda docker container
Navigate to processJobInquiry/target
docker cp processJobInquiry-0.0.1-SNAPSHOT.war camunda:/camunda/webapps/processJobInquiry.war

Run
docker exec mysql bash

in mysql:
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root';
flush privileges;