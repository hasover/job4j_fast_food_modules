# job4j_fast_food
[![Build Status](https://app.travis-ci.com/hasover/job4j_fast_food.svg?branch=master)](https://app.travis-ci.com/hasover/job4j_fast_food)

* [Описание](#описание)
* [Технологии](#технологии)
* [Функционал](#функционал)
* [Запуск](#запуск)
* [Контакты](#контакты)

## Описание
Проект по заказу и доставке еды, который состоит из maven моделей. 
Каждый модуль представляет собой отдельное Spring boot приложение.
Структура проекта job4j_fast_food:
- /admin/ - админка. Представляет собой web-приложение, где можно составлять каталог блюд, редактировать наименования и цены.Адрес сервиса по умолчанию: http://localhost:8080/
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/admin.PNG)
- /delivery/ - сервис доставки.Rest-сервис, через который курьеры получают заказы для доставки. Адрес сервиса по умолчанию: http://localhost:8081/
- /dish/ - сервис блюд. Rest-сервис, через который можно добавлять новые позиции в меню, редактировать и удалять. Адрес сервиса по умолчанию: http://localhost:8082/
- /kitchen/ - сервис кухни. Приложение, которое обрабатывает созданные заказы. В случае успеха отправляет заказ в сервис доставки, в случае неудачи отменяет созданный заказ.
- /notification/ - сервис уведомлений. Приложение, которое обрабатывает все приходящие уведомления.
- /order/ - сервис заказов.Rest-сервис, через который можно сделать заказ, проверять/обновлять статус. Адрес сервиса по умолчанию: http://localhost:8086/  
- /payment/ - сервис платежей. Rest-сервис по обработке платежей. Адрес сервиса по умолчанию: http://localhost:8087/
- /domain/ - доменные модели. Модели, используемые в сервисах.
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/overview.PNG)

## Технологии
* Spring Boot (Web, Data)
* Apache Kafka
* PostgreSQL
* Liquibase
* Maven
* Travis CI

## Функционал

- Для оформления заказа делается post запрос на сервис order, указываются id блюд и их количество, 
customerId клиента и его счет accountId.
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/create.PNG)
- Платежный сервис payment фиксирует покупку и обновляет баланс счета клиента.
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/payment.PNG)
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/balance.PNG)
- В случае успешного платежа данные отправляются в topic orders, откуда обрабатываются сервисом kitchen.
Если заказ можно приготовить, сервис kitchen отправляет данные в topic cooked_orders, которые получит сервис delivery.
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/kitchen.PNG)
- Через сервис delivery курьеры могут видеть список ожидающих доставки заказов, брать заказ в работу,
а также обновлять статус заказа.
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/delivery.PNG)
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/accept.PNG)
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/setStatus.PNG)
- Клиенты могут видеть статус заказа через сервис order
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/getStatus.PNG)
- Сервис notification читает входящие сообщения из topic notifications и обрабатывает их
![alt text](https://github.com/hasover/job4j_fast_food/blob/master/images/notification.PNG)

## Запуск

- Для сборки приложения на вашем компьютере должны быть установлены:
    - JDK 14+
    - Maven
    - PostgreSQL
    - Apache Kafka
- Укажите настройки для подключения к БД в модуле каждого сервиса в файле `src/main/resources/application.properties`
- Запустите кластер Apache Kafka c работающим брокером по адресу localhost:9092
- В корне модуля domain выполните команду `mvn install`
- Выполните команду `mvn spring-boot:run` в модуле каждого сервиса:
    - dish
    - payment
    - order
    - kitchen
    - delivery
    - notification

## Контакты
telegram: @hasover 



   
    