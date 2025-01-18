# Practical Work #3. RESTful web-service
## General task
You are to develop an application with REST web-service interface. Application should provide access to data within a database.

It is strongly recommended to use version control and source code management system (like GitHub).

It is strongly recommended to use Maven to manage your project.

You can choose any IDE, but IntelliJ IDEA is recommended.

## Task 1
Compare JAX-RS and SpringREST. Choose one of them for your application. Give some arguments for your choice.

Аргументы за выбор SpringREST:

* Интеграция с экосистемой Spring: SpringREST легко интегрируется с другими компонентами Spring (Spring Boot, Spring Security, Spring Data), что упрощает разработку и поддержку.

* Гибкость и расширяемость: Spring предоставляет больше возможностей для кастомизации и расширения функциональности.

* Поддержка аннотаций и конфигураций: SpringREST использует мощные аннотации (например, @RestController, @RequestMapping), которые делают код более читаемым и удобным.

## Task 2
Choose one of your previous applications for further development.

Develop a REST API for accessing your data.

## Task 3
Implement the API.

It should allow usage of both XML and JSON.

## Task 4
Develop an XSL transformation for your XML objects to make a browser able to show them as HTML pages (including object data and navigation to other entities and their lists).

## Task 5
Add the XSLT to all XML responses.

## Task 6
Make everything work together…

--

За основу была взята модель данных из ЛР2: Студент <- -> Курс (связь многие-ко-многим).

СУБД PostgreSQL

В этой ЛР для демонстрации и отладки работы REST-архитектуры применялся сервис Postman.

Рассмотрим все "ручки", которые были реализованы:

* Создание нового курса
  
![image](https://github.com/user-attachments/assets/05db97b6-0aee-4c2d-b50a-50ce4ecba494)

* Просмотр всех курсов -> JSON-объект

![image](https://github.com/user-attachments/assets/5be709b8-1397-4943-ac14-6b6047fc6a82)

* Просмотр всех курсов -> XML-объект

![image](https://github.com/user-attachments/assets/82d6823b-e13a-45d8-8199-d86a56413df7)

* Просмотр курса по ID -> JSON-объект

![image](https://github.com/user-attachments/assets/1305cd34-2098-4de1-8c81-5e5f1e7c8dae)

* Просмотр курса по ID -> XML-объект

![image](https://github.com/user-attachments/assets/5800a2d8-fe12-4aad-95e1-e159ad8aa078)

* Изменение существующего курса по ID

![image](https://github.com/user-attachments/assets/e162cca1-2740-41ba-9dd5-d8be9ec81d65)

* Удаление курса по ID
Если будут найдены записи в таблице связи (Enrollments), то они так же будут удалены
![image](https://github.com/user-attachments/assets/2c158ced-8f76-41f6-aa44-aa7c78f62fda)

* Аналогичный функционал реализован для сущности Студента

* Зачисление студента на курс по ID

![image](https://github.com/user-attachments/assets/49435c17-4bd6-4981-b406-3fd38302a75b)

Итоговый список "ручек" выглядит следующим образом

![image](https://github.com/user-attachments/assets/13a4cc46-ab8f-43eb-8b86-af779c559696)




