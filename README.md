# Digital student

Данный сервис содержит в себе модель отношений студент-оценка.
Является аналогом электронного дневника, отображающего итоговую успеваемость по какому-либо предмету.

### **Зависимости проекта:**
* Spring Boot Data
* Spring Boot Validation
* Spring Boot Web
* PostgreSQL
* ModelMapper
* Springdoc-openapi
* Lombok
* Jasper Reports

### **Как запустить:**
1. Склонировать проект
2. Собрать и запустить образ с помощью docker-compose.yml для запуска базы данных из корня каталога digital-student (порт 6545)
3. Локально запустить проект через среду разработки, поддерживающую JDK 17
4. Перейти на страницу с документацией: http://localhost:8088/swagger-ui/index.html#/
5. Осуществить работу с эндпоинтами сервиса в соответствии с документацией

### **Использование отчётов Jasper Reports:**
Была добавлена функция для полного списка студентов и списка оценок каждого студента с помощью запросов /report/all и /report/student.
Новые PDF-файлы сохраняются в корневом каталоге проекта в формате all_student_report.pdf и имя_фамилия_student_report.pdf

Примеры отчётов:

![](https://sun9-66.userapi.com/impg/5lbSSKj82yR6gquY4s_Ffy9XFVl_jvkWMAivAQ/Y-KfU0ZIAJc.jpg?size=796x439&quality=95&sign=5155ac325e5e15830fc1c23992bf4d94&c_uniq_tag=q2uxZFCF-iKltVPDTh1JglXsFkmBu6aR1yPlPf-PRWo&type=album)
![](https://sun9-60.userapi.com/impg/2QgpKASOQ7kz6k_C9z8PXwUfRY8DlTXA_DEU1w/tgecFbBYNsE.jpg?size=789x467&quality=96&sign=7b46b9f1c68258c3df75d56419461580&type=album)
