# URL shortener
## Review
This is a link-shortening service that transforms lengthy URLs into shorter, 
more manageable links. This can be useful for a variety of purposes, 
such as making links more shareable on social media, or for tracking clicks 
on a specific link. It is a simple and efficient solution that can help to streamline 
the process of sharing and managing links.

### Использованные технологии
* Java 17
* Spring Boot
* Maven
* Lombok
* Hibernate validator
* Model mapper
* Google guava
* Apache commons-lang3
* Postgresql
* Log4j

## How to run
### Github
* You should have docker
* Перейдя в папку проекта прописать команду
mvn spring-boot:run

### Docker
* Клонировать репозиторий
* Перейдя в папку проекта прописать команду
mvn clean install
* Собрать докер-образ с произвольным именем, в нашем случае seq-image
docker image build ./ -t seq-image
* Запустить контейнер с образом
docker run -p 8080:8080 -d --name seq-service seq-image

## Endpoints
### Возвращает максимальное число
POST /max

В случае размещения на порте 8080, запрос будет выглядеть:

**localhost:8080/max**

//TODO





