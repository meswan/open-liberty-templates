# Package the application as a war file
FROM maven:3.6.3-ibmjava-8-alpine AS builder
COPY pom.xml ./
COPY src src/
RUN mvn clean package

# Copy the war file over to the open liberty image
FROM openliberty/open-liberty:kernel-java8-openj9-ubi
ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Your Name" \
  org.opencontainers.image.vendor="IBM" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/meswan/open-liberty-templates" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="Open Liberty" \
  name="system" \
  version="$VERSION-$REVISION" \
  summary="Starter Project for Open Liberty" \
  description="This image contains a simple application running with the Open Liberty runtime."

COPY --from=builder --chown=1001:0 src/main/liberty/config/ /config/
COPY --from=builder --chown=1001:0 target/*.war /config/apps/

RUN configure.sh