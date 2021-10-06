# Homeserver

Homeserver is a playground project to familiarize myself with spring. It is a backend to a web app that allows several convenient home-related actions, like maintaining a shared shopping list.

## Usage

```bash
$ ./gradlew bootRun
```

## Setup

The Todo database can be selected by setting the qualifier for TodoDao in TodoService.java as follows:
- `@Qualifier("fakeDao")` for in memory database
- `@Qualifier("postgresDao")` for postgres database

## Installation

To host a dockerized postgresql database on your local computer (needed for the postgresDao), follow the following steps:

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

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)