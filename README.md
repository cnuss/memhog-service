# memhog-service
A Java Service that is a memory hog.

Once invoking `/begin` on the service, the application will request memory from the Java heap until an OutOfMemoryError is generated.

This service was written to deploy multiple containers on a single machine to demonstrate what happens you overprovision memory on a system, to invoke `containerd`'s OOM killer or the OS's OOM handler

# Building (Locally)
 1. `./gradlew installDist`

# Building (Container)
 1. After Building (Locally)
 1. `docker build --tag=cnuss/memhog-service:latest .`

# Running (Locally)
 1. After Building (Locally)
 1. `build/install/memhog-service/bin/memhog-service`

# Running (Within Container)
 1. After Building (Container)
 1. `docker run -d -p 11223:11223 -e "JAVA_OPTS=-Xmx2g" cnuss/memhog-service:latest`
   1. `-e "JAVA_OPTS=-Xmx2g"` -  Dynamically set any Java Options, such as heap size!

# Pushing (Container)
1. After Building (Container)
2. `docker push cnuss/memhog-service:latest`

# Connecting
 1. http://localhost:11223/begin
 1. http://localhost:11223/begin?bytes=100&increment=5&sleep=2.0
   1. `bytes` - The amount of bytes to request on the first iteration
     1. Defaults to 1
   1. `increment` - The exponential rate at to which request more memory.  Each increment will request `iteration_num * bytes * increment` bytes of data
     1. Defaults to 2
   1. `sleep` - The amount of time (in seconds) to sleep between requests, in decimal format
     1. Defaults to 1.0 second
