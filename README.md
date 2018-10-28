### Run service:
```
./gradlew clean build -i && java -jar build/libs/calendar-service-0.0.1-SNAPSHOT.jar
```
### The Task
* internal users to manage stylists readiness to work;
* customers to see the list of available time slots (as per previous screenshot, see **docs** folder);
```
curl -X POST localhost:8080/api/v1/stylist/availability/search -d '{"start": "2018-10-22", "end": "2018-10-33"}' -H 'Content-Type: application/json' | jq
```