calendar-service [![Build Status](https://travis-ci.org/VladimirYushkevich/calendar-service.svg?branch=master)](https://travis-ci.org/VladimirYushkevich/calendar-service) [![Code Coverage](https://img.shields.io/codecov/c/github/VladimirYushkevich/calendar-service/master.svg)](https://codecov.io/github/VladimirYushkevich/calendar-service?branch=master)
=
### Description:

REST API for schedule calls between customer and on of stylists from available pool.

### Run service:
```
./gradlew clean build -i && java -jar build/libs/calendar-service-0.0.1-SNAPSHOT.jar
```
### The Task
* internal users to manage stylists readiness to work;
* customers to see the list of available time slots (as per previous screenshot, see **docs** folder);
```
curl -X POST localhost:8080/api/v1/stylist/availability/search -d '{"start": "2018-10-22", "end": "2018-10-30"}' -H 'Content-Type: application/json' | jq
```
* customers to book a call on a specific time slot;
```
curl -X POST localhost:8080/api/v1/order -d '{"customerId": 1, "day": "2018-10-30", "timeSlotIndex": 2}' -H 'Content-Type: application/json' | jq
```
* automatic booking of calls for orders uploaded via spreadsheets as described above
```
curl -X POST localhost:8080/api/v1/order/bulk -d '{"start": "2018-10-29", "end": "2018-10-30", "customerIds": [1, 2]}' -H 'Content-Type: application/json' | jq
```

### Usage:

[In memory DB console](http://localhost:8080/h2-console)  
[SWAGGER](http://localhost:8080/swagger-ui.html)