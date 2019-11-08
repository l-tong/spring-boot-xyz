# spring-boot-xyz
This is an example on how to connect to two databases and sync the table records.

## To build the executable jar
`mvn package`

## To execute and print the database differences
`java -jar target/db-sync-1.0.jar print`

## To sync the two databases
`java -jar target/db-sync-1.0.jar fix`