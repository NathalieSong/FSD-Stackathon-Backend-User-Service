docker-compose down
docker image rm emart-user-service
docker build . -t emart-user-service
docker-compose up -d