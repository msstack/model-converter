# Model Converter
[![Build Status](https://travis-ci.com/msstack/model-converter.svg?branch=master)](https://travis-ci.com/msstack/model-converter)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b657a3dfb6cc4b8082a346e85e97113f)](https://www.codacy.com/app/msstack/model-converter?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=msstack/model-converter&amp;utm_campaign=Badge_Grade)

## About this project
Convert business model into micro service specification

>Note: For further informaions about MSstack [https://msstack.grydtech.com](https://msstack.grydtech.com)

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
2. Follow these [instruction](https://packagecloud.io/msstack/msstack-artifacts) when adding dependency
```java
File file = new File(Sample.class.getResource("/sample.json").toURI());
ModelReader modelReader = new DefaultModelReader();
ModelConverter modelConverter = new DefaultModelConverter();
BusinessModel businessModel = modelReader.readBusinessModel(file);
List<MicroServiceModel> microServiceModels = modelConverter.convertToMicroServiceModel(businessModel);
```

## Sample model specification
```json
{
  "version": "0.0.1",
  "entities": [
    {
      "id": "en1",
      "name": "item",
      "fields": [
        {
          "name": "item_code",
          "type": "string"
        },
        {
          "name": "name",
          "type": "string"
        }
      ]
    },
    {
      "id": "en2",
      "name": "order",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        },
        {
          "name": "items",
          "type": "en1",
          "array": true
        }
      ]
    }
  ],
  "events": [
    {
      "id": "ev1",
      "name": "order_created",
      "entity_id": "en2"
    }
  ],
  "contracts": [
    {
      "id": "cn1",
      "entity_id": "en2",
      "handler": {
        "type": "command",
        "name": "create_order"
      },
      "request_id": "req1",
      "response_id": "res1",
      "event_ids": [
        "ev1"
      ]
    },
    {
      "id": "cn1",
      "entity_id": "en2",
      "handler": {
        "type": "event",
        "name": "order_created"
      },
      "request_id": "ev1",
      "event_ids": []
    }
  ],
  "requests": [
    {
      "id": "req1",
      "name": "create_order",
      "type": "command",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        }
      ]
    }
  ],
  "responses": [
    {
      "id": "res1",
      "name": "create_order",
      "fields": []
    }
  ],
  "servers": [
    {
      "id": "sv1",
      "name": "order_management",
      "contract_ids": [
        "cn1"
      ]
    },
    {
      "id": "sv2",
      "name": "order_logging",
      "contract_ids": [
        "cn2"
      ]
    }
  ]
}
```
