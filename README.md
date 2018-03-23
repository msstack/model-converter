# model-converter
Convert business model into micro service specification.

# How to run
1. open terminal and run `mvn clean install`
2. go to target folder
3. run `java -jar modelconverter-1.0.jar "b2m" "sample.json" "output.json"`

# Program arguments
1. Mode: "b2m" - convert to micro service model, "m2b" - convert to business model
2. Input file (eg: sample.json): Load input file and convert it
3. Output file (eg: output.json): Result will save in here 

# Add as a dependency
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
```java
File file = new File(Sample.class.getResource("/sample.json").toURI());
ModelReader modelReader = new ModelReaderJackson();
ModelConverter modelConverter = new ModelConverterEntityBased();
BusinessModel businessModel = modelReader.readBusinessModel(file);
List<MicroServiceModel> microServiceModels = modelConverter.convertToMicroServiceModel(businessModel);
```