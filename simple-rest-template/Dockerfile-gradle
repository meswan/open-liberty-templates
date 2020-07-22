# Build the the application to generate the .war file.
FROM gradle:6.5.1-jdk8 AS builder
COPY settings.gradle build.gradle ./
COPY src src/
RUN gradle installLiberty \
           libertyCreate \
           installFeature \
           compileJava \
           processResources \
           classes \
           war \
           deploy \
           compileTestJava \
           processTestResources

# Copy the war file over to the open liberty image
FROM openliberty/open-liberty:kernel-java8-openj9-ubi
COPY --from=builder --chown=1001:0 /home/gradle/src/main/liberty/config /config/
COPY --from=builder --chown=1001:0 /home/gradle/build/libs/simple-service.war /config/apps/
RUN configure.sh