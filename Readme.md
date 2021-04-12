## Description

This app is packed with lots of modern libs that ease the development in Java when using Spring Boot framework. Ita makes use of lombok, mapstruct, spring-data, configuration processor, vavr, actuators.

## Vavr

To see differences when using and not using Vavr, please take a look at the branches :
1. __feature/classic-no-vavr__, we use classical way based on success scenario and an exception handler 
1. __feature/vavr__, we use a vavr scenario based on `ResponseEntity`, no exception handlers
1. __feature/vavr-web-return__, we use __vavr__, vavr module for jackson and exception handlers for best results

## External references

- https://www.elastic.co/guide/en/apm/server/current/running-on-docker.html#_configure_apm_server_on_docker
- https://www.elastic.co/blog/monitoring-java-applications-and-multiservice-traces-and-correlated-logs
- https://www.elastic.co/guide/en/beats/metricbeat/current/running-on-docker.html
- https://www.elastic.co/guide/en/elasticsearch/reference/current/configuring-filebeat.html
- https://www.elastic.co/guide/en/beats/filebeat/current/running-on-docker.html
- https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-source-field.html
- https://micrometer.io/docs/registry/elastic
- https://www.elastic.co/guide/en/apm/get-started/current/quick-start-overview.html
- https://docs.spring.io/spring-boot/docs/2.0.0.M3/reference/html/howto-properties-and-configuration.html
- https://www.baeldung.com/jvm-parameters


Downloaded to be able to build if liquibase domain is not accessible

dbchangelog-2.0.xsd