#!/bin/bash

./gradlew clean dockerbuildNative
docker-compose up
