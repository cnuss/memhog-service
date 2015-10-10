# greeting-service
Sample RESTful Service

# Building
 1) `./gradlew installDist`
 1) `docker build --tag=hub.cchh.io/christian/greeting-service:0.1.0 .`

# Testing
TODO

# Running
After building:
 1) `docker run -p --rm 11223:11223 hub.cchh.io/christian/greeting-service:0.1.0`

# Connecting
http://localhost:11223/greeting
and
http://localhost:11223/greeting?name=Christian
