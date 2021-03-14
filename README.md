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
- [] Secure config endpoints.
- [ ]  Write Kafka streams into ElasticSearch and set it up for search.
  - [ ] Add Kafka Consumer module.
  - [ ] Set up ElasticSearch Container and feed Kafka Consumer Data.
  - [ ] Set up ElasticSearch Queries.

Based on the brilliant [course](https://www.udemy.com/course/event-driven-microservices-spring-boot-kafka-and-elasticsearch/) by Ali Gelenler, do check it out!
