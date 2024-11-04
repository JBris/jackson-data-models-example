# jackson-data-models-example

Demonstration of Jackson data models

## Instructions

Install Java and Maven:

```
sudo apt-get install -y openjdk-17-jdk maven
```

Run the code:

```
cd modelling
mvn clean compile assembly:single -X -e
java -jar target/modelling-1.0-SNAPSHOT-jar-with-dependencies.jar
```