#!/bin/bash
docker run -d --name test --expose 9010 -p 9010:9010 -p8080:8080 test_request_spring_boot:0.0.1