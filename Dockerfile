FROM java:8u66

EXPOSE 11223

ADD build/install/greeting-service/bin /app/bin
ADD build/install/greeting-service/lib /app/lib

CMD "/app/bin/greeting-service"

