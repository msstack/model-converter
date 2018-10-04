# Model Converter
[![Build Status](https://travis-ci.com/msstack/model-converter.svg?branch=master)](https://travis-ci.com/msstack/model-converter)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b657a3dfb6cc4b8082a346e85e97113f)](https://www.codacy.com/app/msstack/model-converter?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=msstack/model-converter&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/b657a3dfb6cc4b8082a346e85e97113f)](https://www.codacy.com/app/msstack/model-converter?utm_source=github.com&utm_medium=referral&utm_content=msstack/model-converter&utm_campaign=Badge_Coverage)

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
  "schemaVersion": "0.0.2",
  "entities": [
    {
      "name": "item",
      "fields": [
        {
          "name": "item_code",
          "type": "string",
          "constraints": ["NOT_NULL"]
        },
        {
          "name": "name",
          "type": "string",
          "constraints": ["NOT_NULL"]
        }
      ]
    },
    {
      "name": "order",
      "fields": [
        {
          "name": "order_id",
          "type": "string",
          "constraints": ["NOT_NULL"]
        },
        {
          "name": "items",
          "type": "list<{ref:entities#item}>",
          "constraints": ["NOT_NULL"]
        }
      ]
    }
  ],
  "events": [
    {
      "name": "order_created",
      "entity": "{ref:entities#order}"
      "description": ""
    }
  ],
  "requests": [
    {
      "name": "create_order",
      "fields": [
        {
          "name": "customer_id",
          "type": "string"
        }
      ]
    }
  ],
  "responses": [
    {
      "name": "order_created",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        }
      ]
    },
    {
      "name": "order_not_created",
      "fields": [
        {
          "name": "message",
          "type": "string"
        }
      ]
    }
  ],
  "contracts": [
    {
      "name": "create_order",
      "description": "creates an order if details are valid, else notify user that details are invalid",
      "entity": "{ref:entities#order}",
      "type": "COMMAND",
      "consumes": "{ref:request#create_order}",
      "produces": {
        "on_success": ["{ref:events#order_created}"],
        "on_failure": ["{ref:events#order_not_created}"],
      }
    },
    {
      "name": "order_created",
      "entity": "{ref:entities#order}",
      "type": "EVENT",
      "consumes": "{ref:events#order_created}"
      "generates": {
        "on_success": ["{ref:responses#order_created}"],
        "on_failure": [],
      }
    },
    {
      "name": "order_not_created",
      "entity": "{ref:entities#order}",
      "type": "EVENT",
      "consumes": "{ref:events#order_not_created}"
      "generates": {
        "on_success: ["{ref:responses#order_not_created}"],
        "on_failure": [],
      }
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
