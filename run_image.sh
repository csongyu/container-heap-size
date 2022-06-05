#!/bin/bash
# get image tag parameter
var_image_tag="8u191-jdk-alpine"
if [ -n "$1" ]; then
    var_image_tag="$1"
fi
echo "image tag is: $var_image_tag"
# get container memory limit parameter
var_memory_limit=""
if [ -n "$2" ]; then
    var_memory_limit="$2"
fi
echo "container memory limit is: $var_memory_limit"
# get JAVA_OPTS parameter
var_java_opts=""
if [ -n "$3" ]; then
    var_java_opts="$3"
fi
echo "JAVA_OPTS is: $var_java_opts"
# get allocate heap size parameter
var_allocate_heap_size=0
if [ -n "$4" ]; then
    var_allocate_heap_size="$4"
fi
echo "allocate heap size is: $var_allocate_heap_size"
# run image
if [ -n "$var_memory_limit" ] && [ -n "$var_java_opts" ]; then
  docker run -it --rm --memory="$var_memory_limit" -e JAVA_OPTS="$var_java_opts" -e ALLOCATE_HEAP_SIZE="$var_allocate_heap_size" container-heap-size:"$var_image_tag"
elif [ -n "$var_memory_limit" ]; then
  docker run -it --rm --memory="$var_memory_limit" -e ALLOCATE_HEAP_SIZE="$var_allocate_heap_size" container-heap-size:"$var_image_tag"
else
  docker run -it --rm -e ALLOCATE_HEAP_SIZE="$var_allocate_heap_size" container-heap-size:"$var_image_tag"
fi
