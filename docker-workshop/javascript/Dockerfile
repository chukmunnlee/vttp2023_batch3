# what your image will be based on
FROM node:20

# create a dirctory and CD into the directory
# mkdir /app
# cd /app
WORKDIR /app

# copy the files from the local into the image
COPY main.js .
COPY metrics.js .
COPY package-lock.json .
COPY package.json .
COPY public public
COPY views views

# install packages
RUN npm ci

# define an environment variable
ENV PORT=5000 METRICS_PORT=5100
ENV INSTANCE_NAME="This is my first containerise application"

# Tell Docker what ports to expose
EXPOSE ${PORT} ${METRICS_PORT}

ENTRYPOINT node main.js
