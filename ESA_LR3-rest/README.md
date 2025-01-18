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

# Practical Work #4. Java Message Service
## General task
You are to add some new functionality to the previous application, namely a logging mechanism and a “watchdog” for a specific kind of changes.

Logging should provide a simple mechanism to put information on changes in your system into a special table of the database.

“Watchdog” should send e-mail notifications on specific changes in the system to some specified e-mail addresses.

It is strongly recommended to use version control and source code management system (like GitHub).

It is strongly recommended to use Maven to manage your project.

You can choose any IDE, but IntelliJ IDEA is recommended.

## Task 1
Add new table to your database. Each row should represent a change of information in other tables of your database. A row should contain information on a kind of a change (insert, update, etc.), on a changing entity (e.g. its class, id) and on a substance of a change (e.g. new values for fields).

## Task 2
Create and configure JMS administrative objects in your application server. Choose type of destination object wisely.

## Task 3
Change your application to make it send a message to the destination object every time some changes are applied to entities. Choose message type and structure reasonably.

## Task 4
Develop MDB or MDP (depending on your technology platform) to receive these messages. Then MDB or MDP should check message’s type and structure, and then make a record into the dedicated table.

## Task 5
Choose a type of events you want to make notifications for. It can be changes of exact entity, changes of entities with names containing some specific string, some entity attribute exceeding some boundaries, etc. Specify additional information required to identify such an event (e.g. entity id, specific string, attribute boundaries, etc.).

Add new table to the database. Each row should contain an e-mail for notification and condition information.

## Task 6
Develop MDB or MDP to receive messages on changes, check conditions and send e-mails.
Some additional class may be required.

## Task 7
Make everything work together…

--

В данной работе были созданы еще две таблицы:
* Log - для фиксирования конкретных изменений в основных сущностях
* Notification - для отслеживания отправленных сообщений по различным e-mail

Применялся брокер сообщений с открытым кодом Apache ActiveMQ, который "поднимался" локально отдельно от приложения.
![image](https://github.com/user-attachments/assets/cf02e00e-566d-460f-a5be-4c1c23261628)

Учитывались 3 основных изменения сущностей Студента и Курса: INSERT, UPDATE, DELETE.

К примеру, записи в таблице Log после добавления 3 курсов:
![image](https://github.com/user-attachments/assets/3f522b96-9d8f-437b-acef-fd7fcf4353c9)

Соответствующие записи в таблице Notification:
![image](https://github.com/user-attachments/assets/ffc32ad4-d7ad-4364-8bfc-fad211110c7d)





