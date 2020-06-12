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
    - Once started, the endpoint can be reached at http://localhost:9080/example/app/resource/greeting

## Start the OpenLiberty server in development mode.
    mvn liberty:dev
    or
    gradle libertyDev




