#!/bin/bash

set -x

NUM_CONTAINERS=10
NUM_THREADS=10

for i in `seq 1 $NUM_CONTAINERS`; do
	PORT=$((15000+$i))
	docker run -d -p $PORT:11223 -e "JAVA_OPTS=-Xmx2g" cnuss/memhog-service:latest
done

sleep 60s

for i in `seq 1 $NUM_CONTAINERS`; do
	PORT=$((15000+$i))
	curl "http://localhost:$PORT/begin?bytes=1000&increment=1&sleep=0.1&cpuHeavy=true&numThreads=$NUM_THREADS"
	sleep 1s
done
