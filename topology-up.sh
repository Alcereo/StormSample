#!/usr/bin/env bash

echo 'Killing current topology...'
docker run \
-v $(pwd)/topology/build/libs/StormTest.jar:/topology.jar \
--net stormloggingtesting_default \
storm \
storm kill simple-name-counting-topology -w 0

echo 'Start to submit new topology...'
docker run \
-v $(pwd)/topology/build/libs/StormTest.jar:/topology.jar \
--net stormloggingtesting_default \
storm \
storm jar /topology.jar ru.alcereo.Main remote

echo 'Exit...'
