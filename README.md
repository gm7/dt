## Test Instructions
1) On the “Kanban Board“ landing page there is a “+Create Kanban” button.   For each Kanban item that gets created there must be a created date visible on the item, currently there is no date available. (The date must be persisted in the DB)

2) Click on any Kanban item that was created.  You will then be able to view the Kanban details with different columns.   Add a new “Testing” column.

3) Click on “+Create Task” button and add 2 more colours (red & green) in the “Select colour” combobox.  When adding a new item with the new colours added it must then display the task item in that colour.

4) Add a “Back” button to navigate back to the “Kanban boards” landing page

5) Add a “Delete” button to be able to delete a Kanban item on the “Kanban Board” landing page

6) Using CSS change the background of the pages.

7) Write unit tests for the new functionality you have added.



## Kanban Application

This is a simple implementation of a Kanban Board, a tool that helps visualize and manage work. Originally it was first created in Toyota automotive, but nowadays it's widely used in software development.

A Kanban Board is usually made of 3 columns - *TODO*, *InProgres*s & *Done*. In each column there are Post-it notes that represents task and their status.

As already stated this project is an implementation of such board and made of 3 separate Docker containers that holds:

- PostgreSQL database
- Java backend (Spring Boot)
- Angular frontend

The entry point for a user is a website which is available under the address: **http://localhost:4200/**



---

### Prerequisites

In order to run this application you need to install two tools: **Docker** & **Docker Compose**.

Instructions how to install **Docker** on [Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/), [Windows](https://docs.docker.com/docker-for-windows/install/), [Mac](https://docs.docker.com/docker-for-mac/install/).

**Docker Compose** is already included in installation packs for *Windows* and *Mac*, so only Ubuntu users needs to follow [this instructions](https://docs.docker.com/compose/install/).


### How to run it?

The entire application can be run with a single command on a terminal:

```
$ docker-compose up -d
```

If you want to stop it, use the following command:

```
$ docker-compose down
```

---

#### kanban-postgres (Database)

PostgreSQL database contains only single schema with two tables - kanban
and task table.

After running the app it can be accessible using these connectors:

- Host: *localhost*
- Database: *kanban*
- User: *kanban*
- Password: *kanban*


Like other parts of application Postgres database is containerized and
the definition of its Docker container can be found in
*docker-compose.yml* file.

```yml
kanban-postgres:
    image: "postgres:9.6-alpine"
    container_name: kanban-postgres
    volumes:
      - kanban-data:/var/lib/postgresql/data
    ports:
      - 5432:5432
    environment:
      - POSTGRES_DB:kanban
      - POSTGRES_USER:kanban
      - POSTGRES_PASSWORD:kanban
```

#### kanban-app (REST API)

This is a Spring Boot (Java) based application that connects with a
database that and expose the REST endpoints that can be consumed by
frontend. It supports multiple HTTP REST methods like GET, POST, PUT and
DELETE for two resources - kanban & task.

Full list of available REST endpoints could be found in Swagger UI,
which could be called using link: **http://localhost:8080/api/swagger-ui.html**


![swagger-ui](https://github.com/wkrzywiec/kanban-board/blob/master/assets/swagger.png)


This app is also put in Docker container and its definition can be found
in a file *kanban-app/Dockerfile*. 



#### kanban-ui (Frontend)

This is a real endpoint for a user where they can manipulate their
kanbans and tasks. It consumes the REST API endpoints provided by
*kanban-app*.

It can be entered using link: **http://localhost:4200/**
