# greeting-service
Sample RESTful Service

# Building (Locally)
 1. `./gradlew installDist`

# Building (Container)
 1. After Building (Locally)
 1. `docker build --tag=cnuss/greeting-service:0.1.0 .`

# Running (Locally)
 1. After Building (Locally)
 1. `build/install/greeting-service/bin/greeting-service`

# Running (Within Container)
 1. After Building (Container)
 1. `docker run -p --rm 11223:11223 cnuss/greeting-service:0.1.0`

# Pushing (Container)
1. After Building (Container)
2. `docker push cnuss/greeting-service:0.1.0`

# Connecting
 1. http://localhost:11223/greeting
 1. http://localhost:11223/greeting?name=Christian
