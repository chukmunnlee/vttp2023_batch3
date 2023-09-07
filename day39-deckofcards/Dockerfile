FROM node:18 AS angbuilder

WORKDIR /app

COPY angular.json .
COPY package.json .
COPY package-lock.json .
COPY tsconfig.app.json .
COPY tsconfig.json .
COPY src src

RUN npm i -g @angular/cli
RUN npm ci
RUN ng build

# dist/deckofcards

FROM caddy:2-alpine

WORKDIR /app

COPY --from=angbuilder /app/dist/deckofcards deckofcards

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT caddy file-server --listen :${PORT} --root /app/deckofcards
