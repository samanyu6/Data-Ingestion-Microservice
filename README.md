# Twitter Data Ingestion Microservice
Event driven microservices for ingesting twitter data, built using Spring Cloud, Docker, Kafka, Prometheus, Grafana and Elasticsearch (ELK)!

[![Build Status](https://travis-ci.com/samanyu6/Data-Ingestion-Microservice.svg?branch=develop)](https://travis-ci.com/samanyu6/Data-Ingestion-Microservice)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=samanyu6_Data-Ingestion-Microservice&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=samanyu6_Data-Ingestion-Microservice)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=samanyu6_Data-Ingestion-Microservice&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=samanyu6_Data-Ingestion-Microservice)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=samanyu6_Data-Ingestion-Microservice&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=samanyu6_Data-Ingestion-Microservice)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=samanyu6_Data-Ingestion-Microservice&metric=security_rating)](https://sonarcloud.io/dashboard?id=samanyu6_Data-Ingestion-Microservice)

# TO DO:

- [x] Retrieve stream of tweets for specific topics.
- [x] Stream data from Java to Kafka Topics 
  - [x] Serialize Stream data for Kafka Topics (each twitter stream topic is a Kafka topic).
  - [x] Produce Serialized Stream into Kafka Container.
- [x] Set up Spring Cloud Server Config.
- [ ]  Write Kafka streams into ElasticSearch and set it up for search.
  - [x] Add Kafka Consumer module.
  - [x] Set up ElasticSearch Container
  - [ ] Feed consumed data into Elastic Search.
  - [ ] Set up ElasticSearch Queries.
 
# SETUP:
- Add your twitter developer API keys in the twitter-to-kafka-service module under ```src/main/resources/``` with a file named ```twitter4j.properties```.
   The file would have the following attributes:
   ```.env
    debug=true
    oauth.consumerKey="YOUR_CONSUMER_KEY"
    oauth.consumerSecret="YOUR_CONSUMER_SECRET"
    oauth.accessToken="YOUR_ACCESS_TOKEN"
    oauth.accessTokenSecret="YOUR_ACCESS_TOKEN_SECRET"
   ```
  
- ### Running setup in docker:
  - ```shell script
    mvn clean install
    ```
  - ```shell script
    cd docker-compose/
    docker-compose up
    ``` 
# Note:
1. Shell scripts for docker ***have*** to be in the Unix LF format.
2. Mount volumes should be defined by you in the elastic_cluster.yml file. 
3. If the elastic search docker images crash, increase the virtual memory by using the following command (WSL/UNIX) :
    ```shell script
    sudo sysctl -w vm.max_map_count=262144
    ```
    
Based on the brilliant [course](https://www.udemy.com/course/event-driven-microservices-spring-boot-kafka-and-elasticsearch/) by Ali Gelenler, do check it out!
