[![Build Status](https://app.travis-ci.com/Thomas9292/homeserver.svg?branch=master)](https://app.travis-ci.com/Thomas9292/homeserver)
# Homeserver 

Homeserver is a playground project to familiarize myself with spring. It is a backend to a web app that allows several convenient home-related actions, like maintaining a shared shopping list. As can be read in the installation instructions, I use docker to host it on a raspberry pi.

## Usage

```bash
$ ./gradlew bootRun
```

## Setup

Copy and rename `application_template.yml` to `application.yml` and fill in a username and password.

The Todo database can be selected by setting the qualifier for TodoDao in TodoService.java as follows:
- `@Qualifier("fakeDao")` for in memory database
- `@Qualifier("postgresDao")` for postgres database

## Installation

#### Build a docker image of the server for raspberry pi

Build the image:
```bash
$ docker build --platform linux/arm/v7 -t <username>/homeserver:latest .
```

#### Initializing postgres

To host a dockerized postgresql database on your local machine (needed for the postgresDao), follow the following steps:

1. [Install docker](https://docs.docker.com/get-docker/)
2. Pull the postgres (we recommend the lightweight alpine version) image
      ```bash
      $ docker pull postgres:alpine
      ```
3. Create the docker image:
      ```bash
      docker run --name <name> -e POSTGRES_PASSWORD=<password> -p 5432:5432 -d postgres:alpine
      ```
   with `<password>` replaced by your password in src/main/resources/application.yml
4. Access the docker:
      ```bash
      docker exec -it <container id> bin/bash
      ```
   where `<container id>` can be found using `docker ps`
5. Access postgresql:
   ```bash
   bash-x.x# psql -U <username>
   ```
   where `<username>` is your username in application.yml
6. Create the database:
      ```sql
      postgres=# CREATE DATABASE homeserverpostgresdb;
      ```
   where `homeserverpostgresdb` is the same as in beforementioned application.yml

***NOTE:*** the ipadress of your postgresql docker container might vary. Use `docker inspect <container id> | grep IPAddress` and change jdbc-url in application.yml accordingly.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)