# Etran Project

## Описание проекта

Этот проект, предназначенный для взаимодействия с системой Этран через SOAP-запросы. Для примера был взят блок СКПП из документации этрана ([XSD схема](EtranSKPP.xsd), а также [Руководство программиста](RPr Programmnoe obespechenie vzaimodeystviya s ASU GO 19122024.doc)).  
Основная цель проекта — автоматизация обработки данных, связанных с планами СКПП (согласование и погрузка), а также логирование запросов и ответов для   
последующего анализа.

Проект включает в себя следующие основные компоненты:

- **SOAP-запросы**: Формирование и отправка SOAP-запросов в систему Этран.
- **Обработка ответов**: Обработка ответов от Этран, включая обработку ошибок и обновление данных.
- **Планировщик задач**: Асинхронное выполнение задач для обработки данных.
- **Логирование**: Сохранение запросов и ответов в базу данных для последующего анализа.

---

## Технологии

- **Java 17**
- **Spring Boot 3.4.2**
    - **Spring Boot Starter Web** — для создания REST API.
    - **Spring Boot Starter Web Services** — для работы с SOAP.
    - **Spring Boot Starter Data JPA** — для работы с базой данных через ORM Hibernate.
- **Liquibase**
- **PostgreSQL**
- **Lombok**
- **MapStruct**