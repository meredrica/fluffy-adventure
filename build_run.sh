#!/bin/bash

./gradlew clean dockerbuild
docker-compose up
