# TP DevOps - GitHub et Docker

## Objectif

Ce repository contient mon travail de TP DevOps en Java avec :

- une application Spring Boot
- des tests unitaires et web
- une conteneurisation Docker
- une automatisation CI avec GitHub Actions

## Structure du repository

### `hello`

Le dossier `hello` correspond au **mini projet d'exemple de départ**.

Il contient une application Spring Boot très simple qui expose seulement la route `/`.

Ce dossier a été conservé comme trace du point de départ du TP, mais ce n'est **pas** le projet principal du rendu.

### `projet/MyService`

Le dossier `projet/MyService` contient le **vrai projet principal** du TP.

Thème choisi : **service de location de voitures**.

L'application permet de :

- consulter le catalogue de voitures
- consulter uniquement les voitures disponibles
- rechercher une voiture par numéro de plaque
- ajouter une nouvelle voiture
- modifier la disponibilité d'une voiture

## Architecture du projet principal

Le projet Spring Boot principal est organisé avec :

- `entities` : modèle métier `Car`
- `services` : logique métier avec `CarService`
- `controllers` : API REST avec `CarController`
- `tests` : tests unitaires et tests web

## Endpoints disponibles

Dans le projet principal `projet/MyService` :

- `GET /api/`
- `GET /api/cars`
- `GET /api/cars/available`
- `GET /api/cars/{plateNumber}`
- `POST /api/cars`
- `PATCH /api/cars/{plateNumber}/availability?available=true`

## Tests

Le projet principal contient :

- des tests unitaires sur l'entité `Car`
- des tests unitaires sur le service `CarService`
- des tests web avec MockMvc sur le contrôleur REST
- un test de chargement du contexte Spring

Exemples de fichiers de test :

- `projet/MyService/src/test/java/com/example/myservice/entities/CarTest.java`
- `projet/MyService/src/test/java/com/example/myservice/services/CarServiceTest.java`
- `projet/MyService/src/test/java/com/example/myservice/controllers/CarControllerTest.java`

## Lancement local

### Depuis IntelliJ

Comme mon environnement terminal Windows peut poser problème avec certains chemins accentués, le plus simple est souvent :

- ouvrir `projet/MyService` dans IntelliJ
- lancer la classe `CarRentalApplication`
- exécuter les tests JUnit directement depuis IntelliJ

### Depuis la ligne de commande

Pour éviter les problèmes liés au chemin utilisateur accentué, on peut utiliser ce chemin de travail :

```powershell
C:\dev\tp_docker\projet\MyService
```

Commandes utiles :

```powershell
cd C:\dev\tp_docker\projet\MyService
.\gradlew.bat test
.\gradlew.bat bootRun
```

## Docker

Construire l'image du projet principal :

```powershell
cd C:\dev\tp_docker\projet\MyService
docker build -t car-rental-service .
```

Lancer le conteneur :

```powershell
docker run -p 8080:8080 car-rental-service
```

Tester ensuite :

- `http://localhost:8080/api/`
- `http://localhost:8080/api/cars`
- `http://localhost:8080/api/cars/available`

## Intégration continue

Le repository contient un workflow GitHub Actions dans :

```text
.github/workflows/action.yaml
```

Ce workflow prépare :

- l'installation de Java 21
- l'exécution des tests Gradle du projet principal
- la génération du rapport JaCoCo
- le build Docker du projet principal
- un test HTTP du projet principal
- le build et le test Docker du projet `hello`

## Remarque

Le projet `rent` fourni comme base d'inspiration a été importé dans le dossier `projet`, puis adapté et nettoyé pour répondre aux consignes du TP.
