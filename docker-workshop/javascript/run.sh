#!/bin/bash

#docker container run -d \
#	-p 8000:5000 \
#	-p 8100:5100 \
#	--name app1 \
#	-e INSTANCE_NAME="my other app" \
#	chukmunnlee/vttpb3-dov-bear:v1.0
#
#docker container stop f7a5
#docker container start f7a5
#
#docker container exec -ti app0 ls -l
#docker container exec -ti app0 /bin/sh
docker container rm -f app0
