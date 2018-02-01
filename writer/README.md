docker rm $(docker ps -aq --filter name=redis)
docker run --name redis -p 6379:6379 -d redis
docker run -it --link redis:redis --rm redis redis-cli -h redis -p 6379
docker inspect -f "{{ .NetworkSettings.IPAddress }}" redis

spring.profiles.active=redis
spring.profiles.active=gemfire

cf create-service rediscloud 30mb redis-cache
