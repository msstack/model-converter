# Model Converter
[![Build Status](https://www.travis-ci.org/msstack/model-converter.svg?branch=master)](https://www.travis-ci.org/msstack/model-converter)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3e163e0a34d04e85b688c8db7540adb7)](https://www.codacy.com/app/grydtech/model-converter?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=msstack/model-converter&amp;utm_campaign=Badge_Grade)

## About this project
Convert business model into micro service specification

>Note: For further informaions about MSstack [msstack.ml](http://msstack.grydtech.com)

## Build from source

### Install maven
1. You can download maven from [here](https://maven.apache.org/download.cgi)
2. You can find installation instruction [here](https://maven.apache.org/install.html)

### Clone and install dependencies
1. Clone repository to your local machine (assume you already installed git if not please install)
```bash
git clone https://github.com/msstack/model-converter.git
mvn clean install
```

### Run
1. go to target folder
2. run `java -jar modelconverter-1.0.jar "b2m" "sample.json" "output.json"`

### Program arguments
1. Mode: "b2m" - convert to micro service model, "m2b" - convert to business model
2. Input file (eg: sample.json): Load input file and convert it
3. Output file (eg: output.json): Result will save in here 

## Add as a project dependency
1. Add snapshot version to maven pom file
```xml
<!-- load version with latest commit -->
<dependency>
    <groupId>com.grydtech.msstack</groupId>
    <artifactId>model-converter</artifactId>
    <version>-SNAPSHOT</version>
</dependency>
```
2. Import Load business model from json file
3. Convert into micro service models
4. Use model for further development or write model into a json file
```java
File file = new File(Sample.class.getResource("/sample.json").toURI());
ModelReader modelReader = new ModelReaderJackson();
ModelConverter modelConverter = new ModelConverterEntityBased();
BusinessModel businessModel = modelReader.readBusinessModel(file);
List<MicroServiceModel> microServiceModels = modelConverter.convertToMicroServiceModel(businessModel);
```