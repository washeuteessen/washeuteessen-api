# Washeuteessen API

## Starting a development enviroment

### Prerequisits

 * Java 8
 * Maven 3
 * Docker (including docker-compose)

### Starting

```bash                                      
# start support services for development (e.g. database)
docker-compose up -d

# run the application in development mode
mvn spring-boot:run                     
```                                     

### Stopping support services

```bash
# stop support services (keeping their state)
docker-compose stop
       
# remove support services (deleting there state)
docker-compose down
```