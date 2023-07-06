
# Migros One Courier Tracing Task

This project is written in Java enviroment. It consists spring boot framework that uses Java 17.


## Requirements

* Java 17
* Avaible port on 8080

Project does not have DB dependency since It is not marked as requirment in the project structure.

## Used Design Patterns

* Builder
* Strategy
* Singleton

Singleton design pattern is used with **@Component** annotation in the spring boot. However, Other design patterns applied by me.

**Strategy** design pattern used for **Event** system in the program. In order to avoid using if clasues while creating log message for events, I used Strategy pattern.

**Builder** pattern used to create relation instances.

## General Flow

I used websockets for getting courier data constantly. Websocket data flow is one way data flow Client to backend. Results of the data. The reason why I used the websockets as input of the courier data is that It is mentioned that **"that mainly takes streaming geolocations of couriers"**. Streaming keyword was the reason why I used it.


[![Alt text](https://i.ibb.co/QkMpFYf/design-drawio.png)](https://i.ibb.co/QkMpFYf/design-drawio.png)

Events in the program such as

* Intersection with courier and store
* Distance Measured
* Incoming data
* Errors

are logged in the console.


## How to test it

In order to test websockets you could use postman. I will add a link to tell how to create websocket collection with postman. Unfortunently, It is impossible to export collections with websockets right now.

https://learning.postman.com/docs/sending-requests/websocket/websocket/


[![Alt text](https://i.ibb.co/309J1X7/image.png)](https://i.ibb.co/309J1X7/image.png)


This is my configuration for postman to send Websocket messages.

I added postman collection to project file.

## How to run it

You can download zip file in the releases tab in project repo

[![Alt text](https://i.ibb.co/mSYkQW1/image.png)](https://i.ibb.co/mSYkQW1/image.png)

You can trace logs from console I couldnt find time for adding logs to the txt file Unfortunently :(


## Some notes

* If you dont enter time to the send courier data, it will take current time as variable. Other fields are compulsory though. You can add time as **2017-01-13T17:09:42.411**






