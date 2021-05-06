How to reproduce:

have a rabbitmq running and accessible as 'rabbitmq' from docker

run:

``
./gradlew clean dockerbuildNative
docker run --rm dispatch:latest
``
