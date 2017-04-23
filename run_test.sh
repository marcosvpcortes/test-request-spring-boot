#!/bin/bash
export DOCKER_MEMORY=512m
export JAVA_OPTS='-Xmx386m -Xms386m'
#export TEST_TIME=1M
export TEST_TIME=5M
export USERS_COUNT=100
#metodo que executa o teste
executa_teste()
{
	THREADS=$1
	QUEUE_SIZE=$2
	echo "Executing  for THREADS=$THREADS e QUEUE_SIZE=$QUEUE_SIZE"
        docker run -d -m $DOCKER_MEMORY --cpuset-cpus="2" --name test -p8080:8080 -e JAVA_OPTS="$JAVA_OPTS" test_request_spring_boot --server.tomcat.max-threads=$THREADS --server.tomcat.accept-count=$QUEUE_SIZE
	sleep 10
        MARK="t$THREADS"
        MARK+="_q"
        MARK+=$QUEUE_SIZE
	siege -c$USERS_COUNT -t $TEST_TIME --log=./test.log http://localhost:8080/test --mark="$MARK" > ./$MARK.log 2>&1
	docker kill test
	docker rm test
}

#clean data
rm ./test.log > /dev/null
docker kill test
docker rm test
docker rmi test_request_spring_boot

#copile java and builder docker image
mvn clean package docker:build

#testing change thead pool end queue size
for iThread in 10 25 50 75 100
do
    for iQueue in 10 25 50 75 100
    do
        executa_teste $iThread $iQueue
    done
done
