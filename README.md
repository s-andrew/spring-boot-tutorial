# Spring Boot Tutorial
Введени бла бла бла

##Step 1. Init
Идем на сайт [start.spring.io](https://start.spring.io/) и генерируем пустой проект. Необходимые зависимости: __Web__, __H2__, __JPA__, __Security__, __Thymeleaf__. Качаем распаковываем архив, открываем проект в idea, ждем пока maven все скачает.

Результат: `git checkout 8fa267`


##Step 2. Create simple app
Создадим наше первое приложение. Пока что не нужно ничего понимать и не в чем разбираться. Главная задача - запустить приложение без ошибок, чтобы проверить что все норм. Главное правило урока:
> не пытайся ничего понять, оно просто должно рабоать

#### 1. application.property

  ...Находим этот файл в __src/main/resources__, переименовываем его в __application.yml__ и вставляем вот это:
```yaml
server:
  port: 5000

security:
  user:
    name: user
    password: 123
###
#   Database Settings
###
spring:
  datasource:
    url: jdbc:h2:mem:example-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    platform: h2
    username: sa
    password:
    driverClassName: org.h2.Driver
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        show_sql: true
        use_sql_comments: true
        format_sql: true
```
В происходящее пока не вникаем.

#### 2. DataSource
Для нашего приложения мы развернем встроенную(embedded) базу данных h2 в inmemory режиме. Источником данных будут служить два файла в папке __src/main/resources__: __schema.sql__ и __data.sql__ . Первый содержит __ddl__ базы второй __dml__. Создадим первую таблицу с __очень__ простой структурой.

(_Если ваша таблица называется не PERSONS, то далее вам везде нужно подставлять вместо Person название своей таблицы_)
###### Er диаграмма:
[PERSONS ER](https://github.com/s-andrew/spring-boot-tutorial/raw/master/er_f_small.png)
###### schema.sql:
```sql
CREATE TABLE PERSONS(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
FIRST_NAME VARCHAR(100),
SECOND_NAME VARCHAR(100)
);
```

###### data.sql:
```sql
INSERT INTO PERSONS (FIRST_NAME, SECOND_NAME) VALUES('Stupid', 'Student');
```
В происходящее все еще не вникаем
#### 3.Entities
Настало время Java) Идем в папку с кодом: __src/main/java/*com/tutorial/tutorial*__. Создаем пакет __entities__. В пакете создаем класс __Person__.
Это будет __Entity__ класс. Когда наше приложение будет обращаться к бд, оно будет возвращать результат в виде Entity классов. Поля Entity класса соответствуют полям соответствующей таблицы в бд.
Код:
```java
@Entity   // По этой анотации spring понимает, что это Entity класс 
@Table(name = "PERSONS") // По этой - с каокй таблице в бд связан этот Entity
public class Person {
    @Id @GeneratedValue // Данное поле является AUTO INCREMENT PRIMARY KEY
    private Long id;

    @Column(name = "FIRST_NAME") // Данное поле соответствует столбцу FIRST_NAME
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    // Constructor, getters and setters
}

```
#### 4. DataAccessObject
Теперь необходимо создать интерфейс для работы с бд. Создаем папку __Repositories__, а в ней класс __PersonRepository__. Вставляем туда этот код (*сейчас максимально не вникаем*):
```java
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{}
```
Если все таки хочется вникнуть, то вот [repositories](https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/repositories.html).
#### 5.Controller
Последний штрих. Создаем папку __Controllers__. Внутри нее - класс __Test__. В него вставляем этот код:
```java
@RestController
public class TestController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public Iterable<Person> test(){
        return personRepository.findAll();
    }
}
```
#### 6. Готово
Идем на 127.0.0.1:5000, вводим user 123,  получаем:
```json
[{"firstName":"Stupid","secondName":"Student"}]
```

Результат: `git checkout e09d0d`

## Step 3. Complex DB & Enttity
Немного усложним структуру базы данных

###### Логическая ER диаграмма:
[Логическая ER диаграмма](https://github.com/s-andrew/spring-boot-tutorial/raw/master/er_l_full.png)


###### Физическа ER диаграмма:
[Физическая ER диаграмма](https://github.com/s-andrew/spring-boot-tutorial/raw/master/er_f_full.png)

###### schema.sql
```sql
CREATE TABLE PERSONS(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
FIRST_NAME VARCHAR(100),
SECOND_NAME VARCHAR(100),
BIRTHDAY DATE
);

CREATE TABLE PHONES(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
NUMBER VARCHAR(20),
PERSON_ID BIGINT NOT NULL,
FOREIGN KEY (PERSON_ID) REFERENCES PERSONS(ID)
);


CREATE TABLE PROFESSIONS(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
TITLE VARCHAR(100)
);


CREATE TABLE PERSONS_PROFESSIONS(
PERSON_ID BIGINT,
PROFESSION_ID BIGINT,
FOREIGN KEY (PERSON_ID) REFERENCES PERSONS(ID),
FOREIGN KEY (PROFESSION_ID) REFERENCES PROFESSIONS(ID)
);
```
###### data.sql
```sql
INSERT INTO PERSONS (ID, FIRST_NAME, SECOND_NAME, BIRTHDAY)
    VALUES
    (1, 'Stupid', 'Student', '2018-01-20'),
    (2, 'Nikita', 'Zubetch', '1997-01-10'),
    (3, 'Stas', 'Pochipov', '1996-12-10');

INSERT INTO PHONES(ID, NUMBER, PERSON_ID)
    VALUES
    (1, '00000000000', 1),
    (2, '11111111111', 2),
    (3, '22222222222', 3);

INSERT INTO PROFESSIONS(ID, TITLE)
    VALUES
    (1, 'Po professii'),
    (2, 'Ne po professii');

INSERT INTO PERSONS_PROFESSIONS(PERSON_ID, PROFESSION_ID)
    VALUES
    (1, 1),
    (2, 2),
    (3, 1);
```
#### @ManyToOne, @OneToMany
Между таблицами появились отношения и это надо отразить в Entity.
Класс __Phone__:
```java
@Entity
@Table(name = "PHONES")
public class Phone {
    @Id @GeneratedValue
    private long id;
    private String number;

    @ManyToOne // Много Phone относятся к одному Person
    @JoinColumn(name = "PERSON_ID") // Имя FK столбца 
    @JsonBackReference // Пока даже не задумываемся что это
    private Person person;

    // Constructor, getters and setters
}
```
И соответственно в  классе __Person__ появилось новое поле:
```java
    @OneToMany(mappedBy = "person")
    @JsonManagedReference
    private List<Phone> phones;

```

Главное не путать __@OneToMany__ и __@ManyToOne__ местами.


#### @ManyToMany
Теперь посмотрим на класс __Profession__:
```java
@Entity
@Table(name = "PROFESSIONS")
public class Profession {
    @Id
    @GeneratedValue
    private long id;
    private String title;

    @ManyToMany
    @JoinTable(name = "PERSONS_PROFESSIONS", // Имя соединительной таблицы
       joinColumns = @JoinColumn(name = "PROFESSION_ID"), // FK на Profession,
                                                          // т.е. на себя
       inverseJoinColumns = @JoinColumn(name = "PERSON_ID")) // Ссылка на Person
    @JsonBackReference
    private List<Person> persons;

    // Constructor, getters and setters
}
```

Соответствующее поле в __Person__:
```java
    @ManyToMany(mappedBy = "persons")
    @JsonManagedReference
    private List<Profession> professions;
```


#### Готово
Проверяем:
```json
[{
"id": 1,
"firstName": "Stupid",
"secondName": "Student",
"birthday": "2018-01-19",
"phones": [{"id":1,"number":"00000000000"}],
"professions":[{"title":"По профессии"}]
}, {
"id": 2,
"firstName":"Nikita",
"secondName":"Zubetch",
"birthday":"1997-01-09",
"phones":[{"id":2,"number":"11111111111"}],
"professions":[{"title":"Ne po professii"}]
}, {
"id":3,
"firstName":"Stas",
"secondName":"Pochipov",
"birthday":"1996-12-09",
"phones":[{"id":3,"number":"22222222222"}],
"professions":[{"title":"По профессии"}]
}]
```

Результат: `git checkout 59da03`

