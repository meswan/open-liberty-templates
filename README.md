![](https://github.com/OpenLiberty/open-liberty/blob/master/logos/logo_horizontal_light_navy.png)

This repository contains starter templates for Open Liberty.

## Navigate to the sample-restful-microservice directory.
	cd open-liberty-templates
	
## Select a template
 - gradle-core-template
    - This template contains the core files needed for Open Liberty and Gradle to run with an empty file structure.
 - maven-core-template
    - This template contains the core files needed for Open Liberty and Maven to run with an empty file structure.
  - simple-rest-template	
    - This template contains both Gradle and Maven core files to run a simple greeting REST endpoint.
    - Once started, the endpoint can be reached at http://localhost:9080/app/resource/greeting

## Start the OpenLiberty server in development mode.
    mvn liberty:dev
    or
    gradle libertyDev

## Docker
 - gradle-core-template
 
 
    docker build -t gradle-core-template:1.0-SNAPSHOT ./gradle-core-template
    
    docker run -d --name gradle-core-template -p 9080:9080 gradle-core-template:1.0-SNAPSHOT
 - maven-core-template
 
 
    docker build -t maven-core-template:1.0-SNAPSHOT ./maven-core-template
    
    docker run -d --name maven-core-template -p 9080:9080 maven-core-template:1.0-SNAPSHOT
 - simple-rest-template (maven)
 
 
    docker build -t simple-rest-template:1.0-SNAPSHOT ./simple-rest-template
    
    docker run -d --name simple-rest-template -p 9080:9080 simple-rest-template:1.0-SNAPSHOT

 - simple-rest-template (gradle)
 
 
    docker build -f ./simple-rest-template/Dockerfile-gradle -t simple-rest-template:1.0-SNAPSHOT ./simple-rest-template
    
    docker run -d --name simple-rest-template -p 9080:9080 simple-rest-template:1.0-SNAPSHOT

