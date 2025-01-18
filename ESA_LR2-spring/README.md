# Practical Work #2. Application using Spring Framework

--

## General task
You are to develop an application using common Spring architecture and technologies. It should have three layers (data, logic, view) and provide means to work with a database.
It is strongly recommended to use version control and source code management system (like GitHub).
It is strongly recommended to use Maven to manage your project.
You can choose any IDE, but IntelliJ IDEA is recommended.

## Task 1
Choose any subject area and make a model with at least two entities with a few properties.
Create a script to make a database for your model.
Yes, you can use the same models and scripts you used before.

## Task 2
Develop data layer as Java Beans for the model and make them prepared for use with Hibernate.
Create additional Hibernate classes and configuration files, as required.

## Task 3
Implement business layer using Spring beans.

## Task 4
Implement view layer using Spring MVC.

## Task 5
Make everything work together…

--

В качестве реализации данного задания была создана модель данных Студент <- -> Курсы (со связью многие-ко-многим)

СУБД - PostgreSQL

Взаимодействие с системой осуществлялось путем изменения ссылок в адресной строке браузера и шаблонов HTML-страниц.

Приведу пример работы всех возможных ситуаций:

* Отображение всех студентов

![image](https://github.com/user-attachments/assets/bd3a672e-1345-43a9-ab07-761298292190)

Далее можно перейти по гиперссылке на следующий пункт

* Форма для добавления нового студента

![image](https://github.com/user-attachments/assets/50b60c14-2434-4747-8ff4-64b7d9ce6a32)

По гиперссылке можно вернуться обратно, если решено не добавлять нового студента.

Если нажать на кнопку сохранить, общий список студентов обновится.

![image](https://github.com/user-attachments/assets/8629b5b5-0c24-461d-91e0-0d5fc68e5cda)

* Аналогичный функционал, описанный выше, реализован для курсов

* Просмотр студентов на курсе и зачисление студентов на курс

![image](https://github.com/user-attachments/assets/00f108f5-075e-4847-824c-0c996ca4e3b7)

Зачисление студентов реализовано путем ввода ID студента, при нажатии на кнопку Записать автоматически обновляется список студентов на курсе

![image](https://github.com/user-attachments/assets/da6b039b-bfab-43e6-af7c-22639d89f872)
