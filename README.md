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
  "schemaVersion": "0.0.3",
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
      "entity": "{ref:entities#order}",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        },
        {
          "name": "customer_id",
          "type": "string"
        }
      ]
    },
    {
      "name": "order_validated",
      "entity": "{ref:entities#order}",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        },
        {
          "name": "status",
          "type": "string"
        }
      ]
    },
    {
      "name": "order_accepted",
      "entity": "{ref:entities#order}",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        }
      ]
    },
    {
      "name": "order_rejected",
      "entity": "{ref:entities#order}",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        }
      ]
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
      "name": "order_accepted",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        }
      ]
    },
    {
      "name": "order_rejected",
      "fields": [
        {
          "name": "order_id",
          "type": "string"
        },
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
      "description": "creates an order in pending state",
      "entity": "{ref:entities#order}",
      "type": "COMMAND",
      "consumes": "{ref:requests#create_order}",
      "produces": {
        "on_success": ["{ref:events#order_created}"],
        "on_failure": []
      }
    },
    {
      "name": "validate_order",
      "description": "chech if the order is valid",
      "entity": "{ref:entities#order}",
      "type": "EVENT",
      "consumes": "{ref:events#order_created}",
      "produces": {
        "on_success": ["{ref:events#order_validated}"],
        "on_failure": [],
      }
    },
    {
      "name": "finalize_order",
      "description": "complete creation of order",
      "entity": "{ref:entities#order}",
      "type": "EVENT",
      "consumes": "{ref:events#order_validated}",
      "produces": {
        "on_success": ["{ref:events#order_accepted}", "{ref:responses#order_accepted}"],
        "on_failure": ["{ref:events#order_rejected}", "{ref:responses#order_rejected}"],
      }
    }
  ]
}
```
