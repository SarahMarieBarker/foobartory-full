# Foobartory

## Informations

Back-end of the project is done in Java.\
Front-end is done in Javascript/React using MUI Library.\
Back-end pushes event-based data to the front-end.

Back-end is using Spring to expose websocket end-points\
and to expose the webpage and the javascript/React runtime.

Front-end subscribes to back-end websocket broker and displays\
the pushed data using React components.

Project is using frontend-maven-plugin to use webpack and \
pack the front-end during maven back-end build process.

## Requirements

Java JDK version >= 11 \
Maven version >= 3.8.1

## Installation

```bash
mvn clean install
```
to start the webserver :
```bash
./mvnw spring-boot:run
```

## Usage

Open the following link in your browser.\
The Foobar production starts when the page opens.\
Refresh the page to start a new production.

```bash
http://localhost:8080
```
