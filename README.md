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

