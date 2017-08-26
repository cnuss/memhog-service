FROM java:8u66

EXPOSE 11223

ADD build/install/memhog-service/bin /app/bin
ADD build/install/memhog-service/lib /app/lib

CMD "/app/bin/memhog-service"
