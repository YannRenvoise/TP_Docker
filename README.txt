# TP Docker

## Images Docker Hub
- yannrenvoise/ubuntu-java:1
- yannrenvoise/hello:1

## Application Spring Boot
Le projet `hello` contient une application Spring Boot simple exposant `/`.

## Build de l'image
```bash
docker build -t hello .
Lancement du conteneur
docker run -p 4000:8080 -t hello

## Test
Ouvrir :
http://localhost:4000