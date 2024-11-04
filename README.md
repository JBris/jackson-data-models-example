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

## Expected output

```
JSON
{
  "type" : "object",
  "id" : "urn:jsonschema:com:data:example:model:Iris",
  "properties" : {
    "id" : {
      "type" : "integer",
      "required" : true,
      "description" : "The unique identifier",
      "minimum" : 0.0,
      "name" : "id",
      "propertyType" : "id",
      "defaultValue" : 0.0,
      "tags" : [ "example_tag", "another_tag" ]
    },
    "sepal_length" : {
      "type" : "number",
      "required" : true,
      "description" : "The flower sepal length",
      "maximum" : 100.0,
      "minimum" : 0.0,
      "name" : "sepal_length",
      "unit" : "cm",
      "propertyType" : "state_variable",
      "defaultValue" : 0.0,
      "tags" : [ "example_tag", "another_tag" ]
    },
    "sepal_width" : {
      "type" : "number",
      "required" : true,
      "description" : "The flower sepal width",
      "maximum" : 100.0,
      "minimum" : 0.0,
      "name" : "sepal_width",
      "unit" : "cm",
      "propertyType" : "state_variable",
      "defaultValue" : 0.0,
      "tags" : [ "example_tag", "another_tag" ]
    },
    "petal_length" : {
      "type" : "number",
      "required" : true,
      "description" : "The flower petal length",
      "maximum" : 300.0,
      "minimum" : 0.0,
      "name" : "petal_length",
      "unit" : "cm",
      "propertyType" : "state_variable",
      "defaultValue" : 0.0,
      "tags" : [ "example_tag", "another_tag" ]
    },
    "petal_width" : {
      "type" : "number",
      "required" : true,
      "description" : "The flower petal width",
      "maximum" : 300.0,
      "minimum" : 0.0,
      "name" : "petal_width",
      "unit" : "cm",
      "propertyType" : "state_variable",
      "defaultValue" : 0.0,
      "tags" : [ "example_tag", "another_tag" ]
    }
  }
}
CSV
id,sepal_length,sepal_width,petal_length,petal_width
0,1.0,2.0,3.0,4.0
1,2.0,3.0,4.0,5.0
2,3.0,4.0,5.0,6.0
3,4.0,5.0,6.0,7.0
4,5.0,6.0,7.0,8.0
5,6.0,7.0,8.0,9.0
6,7.0,8.0,9.0,10.0
7,8.0,9.0,10.0,11.0
8,9.0,10.0,11.0,12.0
9,10.0,11.0,12.0,13.0
10,11.0,12.0,13.0,14.0
```