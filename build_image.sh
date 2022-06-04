#!/bin/bash
# get JDK_TAG parameter
var_jdk_tag="8u191-jdk-alpine"
if [ -n "$1" ]; then
    var_jdk_tag="$1"
fi
echo "JDK_TAG is: $var_jdk_tag"
# build jar
docker run -it --rm -v "$PWD":/app -v "$HOME"/.m2:/root/.m2 -w /app maven:3.8.5-jdk-8 mvn clean package -Dmaven.test.skip=true
# build image
docker build -t container-heap-size:"$var_jdk_tag" --build-arg JDK_TAG="$var_jdk_tag" .
