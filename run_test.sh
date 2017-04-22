#!/bin/bash

export DOCKER_MEMORY=512m
export JAVA_OPTS='-Xmx384m -Xms384m'

#metodo que executa o teste
executa_teste()
{
	THREADS=$1
	QUEUE_SIZE=$2
	echo "Executando para THREADS=$THREADS e QUEUE_SIZE=$QUEUE_SIZE"
        echo docker run -d -m $DOCKER_MEMORY --cpuset-cpus="2" --name test -p8080:8080 -e JAVA_OPTS="$JAVA_OPTS" test_request_spring_boot --server.tomcat.max-threads=$THREADS --server.tomcat.accept-count=$QUEUE_SIZE
	docker run -d -m $DOCKER_MEMORY --cpuset-cpus="2" --name test -p8080:8080 -e JAVA_OPTS="$JAVA_OPTS" test_request_spring_boot --server.tomcat.max-threads=$THREADS --server.tomcat.accept-count=$QUEUE_SIZE
	sleep 10
	siege -c100 -t 1M --log=./test.log http://localhost:8080/test --mark="t($THREADS)q($QUEUE_SIZE)"
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

#primeiro teste
executa_teste 10 5
executa_teste 20 10
executa_teste 30 15
executa_teste 40 20
executa_teste 50 25
executa_teste 60 30
executa_teste 70 35
executa_teste 80 40
executa_teste 90 45
executa_teste 100 50
