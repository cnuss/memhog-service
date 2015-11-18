# greeting-service
Sample RESTful Service

# Building

 1. `./gradlew installDist`
 1. `docker build --tag=cnuss/greeting-service:0.1.0 .`

# Testing
TODO

# Running
After building:
 1. `docker run -p --rm 11223:11223 cnuss/greeting-service:0.1.0`

# Connecting
 1. http://localhost:11223/greeting
 1. http://localhost:11223/greeting?name=Christian
