#Spring Boot Tutorial
--------------------------
Введени бла бла бла

##Step 1. Init
Идем на сайт [start.spring.io](https://start.spring.io/) и генерируем пустой проект. Необходимые зависимости: __Web__, __H2__, __JPA__, __Security__, __Thymeleaf__. Качаем распаковываем архив, открываем проект в idea, ждем пока maven все скачает.

Результат: `git checkout 8fa267`


##Step 2. Create simple app
Создадим наше первое приложение. Пока что не нужно ничего понимать и не в чем разбираться. Главная задача - запустить приложение без ошибок, чтобы проверить что все норм. Главное правило урока:
> не пытайся ничего понять, оно просто должно рабоать

####1. application.property

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

####2. DataSource
Для нашего приложения мы развернем встроенную(embedded) базу данных h2 в inmemory режиме. Источником данных будут служить два файла в папке __src/main/resources__: __schema.sql__ и __data.sql__ . Первый содержит __ddl__ базы второй __dml__. Создадим первую таблицу с __очень__ простой структурой.

(_Если ваша таблица называется не PERSONS, то далее вам везде нужно подставлять вместо Person название своей таблицы_)
######Er диаграмма:
<svg xmlns="http://www.w3.org/2000/svg" style="background-color: rgb(255, 255, 255);" xmlns:xlink="http://www.w3.org/1999/xlink" width="161px" height="119px" version="1.1" content="&lt;mxfile userAgent=&quot;Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0&quot; version=&quot;8.0.1&quot; editor=&quot;www.draw.io&quot; type=&quot;device&quot;&gt;&lt;diagram name=&quot;Page-1&quot; id=&quot;2ca16b54-16f6-2749-3443-fa8db7711227&quot;&gt;7Vhdb5swFP01vE4Y56uPhaZbtX4pVNrj5AQHrBkbGWdJ+ut3DSZASZYoo6sqpY0qfHzt63vPOaHg4CDdfFUkSx5kRLnjudHGwTeO503GI/hrgG0JDIaoBGLFohJqACF7pRZ0LbpiEc1bgVpKrlnWBhdSCLrQLYwoJdftsKXk7awZiWkHCBeEd9EfLNKJRZHr1hPfKIsTm3oytBNzsvgVK7kSNp/j4WXxU06npNrLxucJieS6AeGpgwMlpS6v0k1AuWlt1bZy3e2B2d25FRX6lAVeueA34Stb+vN0Fj49hvZ4elu1JF+zlBMBI38phQ7tjAvjRcJ4dE+2cmVy5hp6UI38RCr2CvGEwxQCAKaVtox7I7Mb4zyQXKoiD6au+W2tDM2ONpeiOax9rgpEb6AHsmkF3pNcV6eUnJMsZ/Pi3GZhSlTMhC+1lqkNqqq8bR/Kcoh9wlksAFtALgqTvu0fVZpuDnKAdsyCYahMqVZbCLEL8MiKwZplfFUO1w3lVSFJQ3QITazirdrj3da7bDNwBxExlLxLN3iTDo0mnXzjPem8UTsb4dABQTT1jdzzpszgolFoDRXi2y9E3BHi3U1XgwnJzCX0VjPC6+Kwr2VmKeR0WVGu7OnN9byiGTVoLGN9Qx8D919bOGVRVOza1KaQhfjzjCyYiO/LLHhQQzObzUAStlzywtkJbEaFOY3URJP5zjaZZEIXzRz68IGWB+6XoTOESgMYo3oMHxOudCBFrhVhhbAoaHtNjb79SMnsBdRMq2qbFh2eKlNvv0y3bf6PyRK7h1XZEshf1DDofi19/2c1zJtG38PsqaJoK6A3ARzj2JRp5XuQTXs/tDnq20yTZbyf5Qat+J1YHXdYvb2bhS8/H68fpu/jdfc4rcVmn9foPRh7eKKx394AzpHApCOBj7Z1yf8n9/T4LE/3QehVh9BwGjw93lxM/bGmnnj/z9TVU9vF1f26+urDXI1Q/4xevHyul5HbfULb/0TYwz9pqPtK4GLmHsyMDrwEOOLmMyiFYf0CqXwUr1/S4ekf&lt;/diagram&gt;&lt;/mxfile&gt;"><defs><clipPath id="mx-clip-35-27-120-28-0"><rect x="35" y="27" width="120" height="28"/></clipPath><clipPath id="mx-clip-5-27-20-28-0"><rect x="5" y="27" width="20" height="28"/></clipPath><clipPath id="mx-clip-35-62-120-24-0"><rect x="35" y="62" width="120" height="24"/></clipPath><clipPath id="mx-clip-35-88-120-24-0"><rect x="35" y="88" width="120" height="24"/></clipPath></defs><g transform="translate(0.5,0.5)"><rect x="0" y="0" width="160" height="118" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 0 26 L 0 0 L 160 0 L 160 26 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="79.5" y="17.5">PERSONS</text></g><path d="M 0 26 M 160 26 M 160 56 L 0 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-35-27-120-28-0)" font-size="12px"><text x="36.5" y="44.5">ID</text></g><path d="M 0 26 M 30 26 L 30 56 M 0 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-5-27-20-28-0)" font-size="12px"><text x="6.5" y="44.5">PK</text></g><path d="M 0 56 M 160 56 M 160 82 M 0 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-35-62-120-24-0)" font-size="12px"><text x="36.5" y="74.5">FIRST_NAME</text></g><path d="M 0 56 M 30 56 L 30 82 M 0 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 0 82 M 160 82 M 160 108 M 0 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-35-88-120-24-0)" font-size="12px"><text x="36.5" y="100.5">SECOND_NAME</text></g><path d="M 0 82 M 30 82 L 30 108 M 0 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 0 108 M 160 108 M 160 118 M 0 118" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 0 108 M 30 108 L 30 118 M 0 118" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/></g></svg>

######schema.sql:
```sql
CREATE TABLE PERSONS(
ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
FIRST_NAME VARCHAR(100),
SECOND_NAME VARCHAR(100)
);
```

######data.sql:
```sql
INSERT INTO PERSONS (FIRST_NAME, SECOND_NAME) VALUES('Stupid', 'Student');
```
В происходящее все еще не вникаем
####3.Entities
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
####4. DataAccessObject
Теперь необходимо создать интерфейс для работы с бд. Создаем папку __Repositories__, а в ней класс __PersonRepository__. Вставляем туда этот код (*сейчас максимально не вникаем*):
```java
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{}
```
Если все таки хочется вникнуть, то вот [repositories](https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/repositories.html).
####5.Controller
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
####6. Готово
Идем на 127.0.0.1:5000, вводим user 123,  получаем:
```json
[{"firstName":"Stupid","secondName":"Student"}]
```

Результат: `git checkout e09d0d`

##Step 3. Complex DB & Enttity
Немного усложним структуру базы данных

######Логическая ER диаграмма:
<svg xmlns="http://www.w3.org/2000/svg" style="background-color: rgb(255, 255, 255);" xmlns:xlink="http://www.w3.org/1999/xlink" width="721px" height="254px" version="1.1" content="&lt;mxfile userAgent=&quot;Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0&quot; version=&quot;8.0.1&quot; editor=&quot;www.draw.io&quot; type=&quot;device&quot;&gt;&lt;diagram name=&quot;Page-1&quot; id=&quot;2ca16b54-16f6-2749-3443-fa8db7711227&quot;&gt;7Vvbkqo4FP0aH6cLCN4eG9uetqa9lDo15zxNRYmQGiBWiEc9Xz8JJAJGW8sB9dTQ3dVFdi47yVpLszfQAL1w9zuFa39IXBQ0LMPdNcBbw7I67Rb/Lwz71GA3zdTgUeymppxhhn8iaTSkdYNdFBcaMkIChtdF45JEEVqygg1SSrbFZisSFL2uoYc0w2wJA936F3aZL62mYWQVHwh7vnTdacqKBVz+41GyiaS/hgVWyU9aHUI1lmwf+9Al25wJ9BugRwlh6VW466FAbK3atrTf+5naw7wpitg1Hay0ww8YbOTSJ/3pbDyayemxvdqSeIvDAEa85KxIxGayxuDlpY8D9xPuyUb4jBnfA1VyfELxT94eBrzK5AZeTZlE3GqJ0XAQ9EhAaOIHIEP8FnrOxIjSF0Ux7ztRCzSPTEO4KzT8hDFTsyRBANcxXiTzFh1DSD0cOYQxEspGapXvxUlJDIEDA+xF3LbkvhCvdOT+IcrQ7iwG5gFZLhhEQsTonjeRHUBLkkGKpd1Ni9sc81QTP0c607Yl4yXbvcPQB29Trg4YeXzJB3f2kTuz1dH8tU+4s1pFbzDgOxBBhhxB9zhPM36RW2hmSsh3mohAI+LgTeegD9fiku8twzDIFgccRtYSwgCtFORUzl5cLxTMZg7GtK0j4MNc/a/SHGLXTUbNczMiCfnjNVziyPtMvQA7M02lN2EifMhVkCjb54OhSMyGMMjg4iCbNcERSzaz6fA/vuU946XZaPKV9njZzMr8TzSnrEeimFGIE2Ihzu0tEvx2XErWc85mpFabl2jzWppap2m6L+J/iZbAOM/KAkG+YIOtfyz98Z/ZsMgL/QSy15KiyIDSCHAJY7FMSd+zaMrvQ+kj+5rJowxOo5yDFVSEaltD9X0wnc3/Hr0O+9Vo3bgMazLYryv0EoTdvFLYx18At1Cgo1Hg0bJO8f/FNd2+SdNlANrVAJ31e+PRWy3qx4q6Y91P1Cpqq1Vdrqq7D1O1pSPqDKbzj7fX77WmH6Zp09AjtcpEraZXi7pcUefSPPdWtVkBpLWab1YzsK9Ts1lC6GXqib5azSWo2TyT2rug5jIgPZW7/RiP+nXq9n6p25ZdzKVaqnxJ012rBALUOdMnyJk+T9K0zppWeWx7XNrUamq4jv4cOv1pfXir8vD2PElTNUZ9eCtZ082HhWKgDsWeSc3XZkvLOLeDOhSr5r7m40IxoN/WmkzH7/3ZbFA/SnPPeKzzwHAM6LfC6nDs3uFYqsSnCMcU9+pwrIoP+9vujZWCq350mw/mn/XN7irPbxd0fc9ozK7Pb5VI2r7t/FYKpPqTabWaH6fme0Zjdv1EWjVqvu2RtDIgben50nGE5mQIo72GLXI9pE6ZfPKY7acogAyTqJ/VOD4LVdCFIvdVvGHCi/0pOYzLK3aYfRN7/tLt2rL8nZd/M16MdjvpyRfyTaKSFES1+WKq4gRRzFcrgqG0DR/h2JYuQMz6rLqkKSYbupStZGjClFIbVyW0D6+7FBGSRprs04/iPL54FWEieJiFaqZVjNWAfQR9On/ZK0NfG8iyzgR9aqB01dpAN7yk0NITsQL8SqgVpqxKYnzNeppKnHkt6ysynaLngZ2lkU0dkPNsu/xp8H8nGy9mr32lzbNX60D/Xw==&lt;/diagram&gt;&lt;/mxfile&gt;"><defs><clipPath id="mx-clip-315-27-120-28-0"><rect x="315" y="27" width="120" height="28"/></clipPath><clipPath id="mx-clip-285-27-20-28-0"><rect x="285" y="27" width="20" height="28"/></clipPath><clipPath id="mx-clip-315-62-120-24-0"><rect x="315" y="62" width="120" height="24"/></clipPath><clipPath id="mx-clip-315-88-120-24-0"><rect x="315" y="88" width="120" height="24"/></clipPath><clipPath id="mx-clip-315-114-120-24-0"><rect x="315" y="114" width="120" height="24"/></clipPath><clipPath id="mx-clip-595-188-120-28-0"><rect x="595" y="188" width="120" height="28"/></clipPath><clipPath id="mx-clip-565-188-20-28-0"><rect x="565" y="188" width="20" height="28"/></clipPath><clipPath id="mx-clip-595-223-120-24-0"><rect x="595" y="223" width="120" height="24"/></clipPath><clipPath id="mx-clip-35-188-120-28-0"><rect x="35" y="188" width="120" height="28"/></clipPath><clipPath id="mx-clip-5-188-20-28-0"><rect x="5" y="188" width="20" height="28"/></clipPath><clipPath id="mx-clip-35-223-120-24-0"><rect x="35" y="223" width="120" height="24"/></clipPath></defs><g transform="translate(0.5,0.5)"><rect x="280" y="0" width="160" height="144" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 280 26 L 280 0 L 440 0 L 440 26 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="359.5" y="17.5">PERSONS</text></g><path d="M 280 26 M 440 26 M 440 56 L 280 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-315-27-120-28-0)" font-size="12px"><text x="316.5" y="44.5">ID</text></g><path d="M 280 26 M 310 26 L 310 56 M 280 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-285-27-20-28-0)" font-size="12px"><text x="286.5" y="44.5">PK</text></g><path d="M 280 56 M 440 56 M 440 82 M 280 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-315-62-120-24-0)" font-size="12px"><text x="316.5" y="74.5">FIRST_NAME</text></g><path d="M 280 56 M 310 56 L 310 82 M 280 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 280 82 M 440 82 M 440 108 M 280 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-315-88-120-24-0)" font-size="12px"><text x="316.5" y="100.5">SECOND_NAME</text></g><path d="M 280 82 M 310 82 L 310 108 M 280 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 280 108 M 440 108 M 440 134 M 280 134" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-315-114-120-24-0)" font-size="12px"><text x="316.5" y="126.5">BIRTHDAY</text></g><path d="M 280 108 M 310 108 L 310 134 M 280 134" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 280 134 M 440 134 M 440 144 M 280 144" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 280 134 M 310 134 L 310 144 M 280 144" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><rect x="560" y="161" width="160" height="92" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 560 187 L 560 161 L 720 161 L 720 187 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="639.5" y="178.5">PHONES</text></g><path d="M 560 187 M 720 187 M 720 217 L 560 217" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-595-188-120-28-0)" font-size="12px"><text x="596.5" y="205.5">ID</text></g><path d="M 560 187 M 590 187 L 590 217 M 560 217" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-565-188-20-28-0)" font-size="12px"><text x="566.5" y="205.5">PK</text></g><path d="M 560 217 M 720 217 M 720 243 M 560 243" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-595-223-120-24-0)" font-size="12px"><text x="596.5" y="235.5">NUMBER</text></g><path d="M 560 217 M 590 217 L 590 243 M 560 243" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 560 243 M 720 243 M 720 253 M 560 253" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 560 243 M 590 243 L 590 253 M 560 253" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><rect x="0" y="161" width="160" height="92" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 0 187 L 0 161 L 160 161 L 160 187 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="79.5" y="178.5">PROFESSIONS</text></g><path d="M 0 187 M 160 187 M 160 217 L 0 217" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-35-188-120-28-0)" font-size="12px"><text x="36.5" y="205.5">ID</text></g><path d="M 0 187 M 30 187 L 30 217 M 0 217" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-5-188-20-28-0)" font-size="12px"><text x="6.5" y="205.5">PK</text></g><path d="M 0 217 M 160 217 M 160 243 M 0 243" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-35-223-120-24-0)" font-size="12px"><text x="36.5" y="235.5">TITLE</text></g><path d="M 0 217 M 30 217 L 30 243 M 0 243" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 0 243 M 160 243 M 160 253 M 0 253" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 0 243 M 30 243 L 30 253 M 0 253" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 439 80 L 459 80 Q 469 80 472.99 89.17 L 526.01 210.83 Q 530 220 540 220 L 560 220" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 552 224 L 552 216 M 560 216 L 552 220 L 560 224" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g transform="translate(470.5,141.5)"><switch><foreignObject style="overflow:visible;" pointer-events="all" width="57" height="11" requiredFeatures="http://www.w3.org/TR/SVG11/feature#Extensibility"><div xmlns="http://www.w3.org/1999/xhtml" style="display: inline-block; font-size: 11px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; white-space: nowrap; text-align: center;"><div xmlns="http://www.w3.org/1999/xhtml" style="display:inline-block;text-align:inherit;text-decoration:inherit;background-color:#ffffff;">OneToMany</div></div></foreignObject><text x="29" y="11" fill="#000000" text-anchor="middle" font-size="11px" font-family="Helvetica">OneToMany</text></switch></g><path d="M 159 219 L 179 219 Q 189 219 193.04 209.85 L 245.96 90.15 Q 250 81 260 81 L 280 81" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 159 223 L 167 219 L 159 215" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 280 77 L 272 81 L 280 85" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g transform="translate(187.5,142.5)"><switch><foreignObject style="overflow:visible;" pointer-events="all" width="63" height="11" requiredFeatures="http://www.w3.org/TR/SVG11/feature#Extensibility"><div xmlns="http://www.w3.org/1999/xhtml" style="display: inline-block; font-size: 11px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; white-space: nowrap; text-align: center;"><div xmlns="http://www.w3.org/1999/xhtml" style="display:inline-block;text-align:inherit;text-decoration:inherit;background-color:#ffffff;">ManyToMany</div></div></foreignObject><text x="32" y="11" fill="#000000" text-anchor="middle" font-size="11px" font-family="Helvetica">ManyToMany</text></switch></g></g></svg>




######Физическа ER диаграмма:
<svg xmlns="http://www.w3.org/2000/svg" style="background-color: rgb(255, 255, 255);" xmlns:xlink="http://www.w3.org/1999/xlink" width="761px" height="145px" version="1.1" content="&lt;mxfile userAgent=&quot;Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0&quot; version=&quot;8.0.1&quot; editor=&quot;www.draw.io&quot; type=&quot;device&quot;&gt;&lt;diagram name=&quot;Page-1&quot; id=&quot;2ca16b54-16f6-2749-3443-fa8db7711227&quot;&gt;7Vxtc9o4EP41fO34FcjHmECTubwN5ObaTxkFK7bujMXIooH++pNsCdsRvlAq27SnhMnYK1kraZ/HWu0qDNzJavuZgHV8h0OYDBwr3A7cq4HjjEdD9pcLdoXA8+1CEBEUFqKKYIG+QyG0hHSDQpjVKlKME4rWdeESpylc0poMEILf6tVecVLXugYRVASLJUhU6V8opLGQ2pZVFlxDFMVC9dgXBS9g+U9E8CYV+gaO+5r/FMUrINsS9bMYhPitInKnA3dCMKbF1Wo7gQmfWjltxXOzhtJ9vwlM6TEPOMUD30CyEUN/nM4XD/cL0T26k1OSvaFVAlJ2F7zilC5EicXulzFKwluwwxuuM6NsDuRdEGOCvrP6IGFFNhOwYkKFxZ0hbw0lyQQnmOR6XGjx39qTC96i0EVgxp59lAO034nuwLZW8RZkVPYSJwlYZ+gl7zd/cAVIhNIAU4pXopIc5azeKWFDNwAJilImWzJdkBUGYv4goXDbaAN7b1lGGIhXkJIdqyIe8DwBBkEWWzbxVoHeUNSJK6izPU9AXsA92re9Vzdn9ABpxMZc6hu+0zccK/pGB9Q5w7o2kLApSAGFAcd7VsUZu6iMtBTl6DuMRFdB4s2VCsIYrPklm1yKQFIOzg0oXgsbJvBV2pyI3vPrF2lnu2LHom7A7YcY/S+FeIXCMG+1Cs4U5+jP1mCJ0ui20OJ6pWgutHERZk2+Jjm1Y9YYTHlvMAUUvOx5s8Yopflk+gH7sCmfWJ/8gc9GOmH3dnnPPrw6oROcZpQAlCMLMnC/QQ7wICR4/cTgDOVoqxz1j8Wpcxinu7r9P4KlazWjsgaQ/0CDp76X/vhpNLxUmX7AsseCoo4AbQD4yMZ8mAK+jdYUC6LQUa4zVSu7h61cMavbklVHilVnN/PF0/P95d20Ha5bH5s1b+zXJboGYvtHEvv9AnAKBMYKBPqmdWH/X5zTo5M4rcOgF4pBF9PJw/2VIXW/pB473ZFabtsMq/Wy+qI3VjuqRYOb+dP11eVXw+neOG1b6k6tNVLL7hlS6yV1Jc7TNavtFkxq2Hwym13vODbbGrZethrpM2zWwGa7Ibb3AZt1mPRQ8Pb64X5qYrfdxW6HJ8du7bEGBJig6RkETc8namrCpm36bf3FTR1fsev9n3fBdG68tza9t/OJmso2jPemmdN+b3uxA8mQIvX+3NYS/vvTur5+a+B4l0HUA6mRWe+L9+/B8v6yI66JuJzTmn10AFXH/tw1IZd2zi/0F3JxR4oFYRhBueawwcc4wilIpqU0yI8DwlDMWUxXMpwCt4h+EWJ+/ZVff+IbTzZWsvsiq/GbsuxvSOlOBGHAhmJuq73eW5yjppxj3r1GtghRhjdkCetuCZXUGzRufAhMAEXf6s3/1OyqK+Dj/GE2XSxuzInETk8knhjUunA0gEA9UGBiWl3HtAomnkVMS2LRxLTaWEpPO2Ggxa6qZ/x083Rrjgy16R5/wOsuQ1qe8Y5bobR3mnesxaRqSMuwuT82Hxu80rEx8sy53nbYfFroSotJVUdc/G/Ys9mV9bErO3lbNtZw1MBXl2uTrug8XeE1+Os/uC/Tsdj76tkTk67Q8s73G2LYHXhw/oEDJPt3vWH6+TBdvo47Ybp6+sQwXQ/Tvf6Y3oLD/j8ltw4+jzrMS/qqY2/YrIPNDdvxDvZqEipt5yXbzT1K76OWe2zgjfbk41AlgYYptJUp3Kd2rUHHqV3pM3SQ2mW35XfYFF8GUn5PkDv9Fw==&lt;/diagram&gt;&lt;/mxfile&gt;"><defs><clipPath id="mx-clip-435-27-120-28-0"><rect x="435" y="27" width="120" height="28"/></clipPath><clipPath id="mx-clip-405-27-20-28-0"><rect x="405" y="27" width="20" height="28"/></clipPath><clipPath id="mx-clip-435-62-120-24-0"><rect x="435" y="62" width="120" height="24"/></clipPath><clipPath id="mx-clip-435-88-120-24-0"><rect x="435" y="88" width="120" height="24"/></clipPath><clipPath id="mx-clip-435-114-120-24-0"><rect x="435" y="114" width="120" height="24"/></clipPath><clipPath id="mx-clip-635-27-120-28-0"><rect x="635" y="27" width="120" height="28"/></clipPath><clipPath id="mx-clip-605-27-20-28-0"><rect x="605" y="27" width="20" height="28"/></clipPath><clipPath id="mx-clip-635-62-120-24-0"><rect x="635" y="62" width="120" height="24"/></clipPath><clipPath id="mx-clip-635-88-120-24-0"><rect x="635" y="88" width="120" height="24"/></clipPath><clipPath id="mx-clip-605-88-20-24-0"><rect x="605" y="88" width="20" height="24"/></clipPath><clipPath id="mx-clip-35-27-120-28-0"><rect x="35" y="27" width="120" height="28"/></clipPath><clipPath id="mx-clip-5-27-20-28-0"><rect x="5" y="27" width="20" height="28"/></clipPath><clipPath id="mx-clip-35-62-120-24-0"><rect x="35" y="62" width="120" height="24"/></clipPath><clipPath id="mx-clip-235-32-120-24-0"><rect x="235" y="32" width="120" height="24"/></clipPath><clipPath id="mx-clip-205-32-20-24-0"><rect x="205" y="32" width="20" height="24"/></clipPath><clipPath id="mx-clip-235-58-120-24-0"><rect x="235" y="58" width="120" height="24"/></clipPath><clipPath id="mx-clip-205-58-20-24-0"><rect x="205" y="58" width="20" height="24"/></clipPath></defs><g transform="translate(0.5,0.5)"><rect x="400" y="0" width="160" height="144" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 400 26 L 400 0 L 560 0 L 560 26 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="479.5" y="17.5">PERSONS</text></g><path d="M 400 26 M 560 26 M 560 56 L 400 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-435-27-120-28-0)" font-size="12px"><text x="436.5" y="44.5">ID</text></g><path d="M 400 26 M 430 26 L 430 56 M 400 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-405-27-20-28-0)" font-size="12px"><text x="406.5" y="44.5">PK</text></g><path d="M 400 56 M 560 56 M 560 82 M 400 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-435-62-120-24-0)" font-size="12px"><text x="436.5" y="74.5">FIRST_NAME</text></g><path d="M 400 56 M 430 56 L 430 82 M 400 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 400 82 M 560 82 M 560 108 M 400 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-435-88-120-24-0)" font-size="12px"><text x="436.5" y="100.5">SECOND_NAME</text></g><path d="M 400 82 M 430 82 L 430 108 M 400 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 400 108 M 560 108 M 560 134 M 400 134" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-435-114-120-24-0)" font-size="12px"><text x="436.5" y="126.5">BIRTHDAY</text></g><path d="M 400 108 M 430 108 L 430 134 M 400 134" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 400 134 M 560 134 M 560 144 M 400 144" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 400 134 M 430 134 L 430 144 M 400 144" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><rect x="600" y="0" width="160" height="118" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 600 26 L 600 0 L 760 0 L 760 26 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="679.5" y="17.5">PHONES</text></g><path d="M 600 26 M 760 26 M 760 56 L 600 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-635-27-120-28-0)" font-size="12px"><text x="636.5" y="44.5">ID</text></g><path d="M 600 26 M 630 26 L 630 56 M 600 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-605-27-20-28-0)" font-size="12px"><text x="606.5" y="44.5">PK</text></g><path d="M 600 56 M 760 56 M 760 82 M 600 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-635-62-120-24-0)" font-size="12px"><text x="636.5" y="74.5">NUMBER</text></g><path d="M 600 56 M 630 56 L 630 82 M 600 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 600 82 M 760 82 M 760 108 M 600 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-635-88-120-24-0)" font-size="12px"><text x="636.5" y="100.5">PERSON_ID</text></g><path d="M 600 82 M 630 82 L 630 108 M 600 108" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-605-88-20-24-0)" font-size="12px"><text x="606.5" y="100.5">FK</text></g><path d="M 600 108 M 760 108 M 760 118 M 600 118" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 600 108 M 630 108 L 630 118 M 600 118" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 600 95 L 580 95 L 580 41 L 566.37 41" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 561.12 41 L 568.12 37.5 L 566.37 41 L 568.12 44.5 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><rect x="0" y="0" width="160" height="92" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 0 26 L 0 0 L 160 0 L 160 26 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="79.5" y="17.5">PROFESSIONS</text></g><path d="M 0 26 M 160 26 M 160 56 L 0 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-35-27-120-28-0)" font-size="12px"><text x="36.5" y="44.5">ID</text></g><path d="M 0 26 M 30 26 L 30 56 M 0 56" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-5-27-20-28-0)" font-size="12px"><text x="6.5" y="44.5">PK</text></g><path d="M 0 56 M 160 56 M 160 82 M 0 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-35-62-120-24-0)" font-size="12px"><text x="36.5" y="74.5">TITLE</text></g><path d="M 0 56 M 30 56 L 30 82 M 0 82" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 0 82 M 160 82 M 160 92 M 0 92" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 0 82 M 30 82 L 30 92 M 0 92" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><rect x="200" y="0" width="160" height="88" fill="#ffffff" stroke="#000000" pointer-events="none"/><path d="M 200 26 L 200 0 L 360 0 L 360 26 Z" fill="#e0e0e0" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" text-anchor="middle" font-size="12px"><text x="279.5" y="17.5">PERSONS_PROFESSIONS</text></g><path d="M 200 26 M 360 26 M 360 52 M 200 52" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-235-32-120-24-0)" font-size="12px"><text x="236.5" y="44.5">PERSON_ID</text></g><path d="M 200 26 M 230 26 L 230 52 M 200 52" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-205-32-20-24-0)" font-size="12px"><text x="206.5" y="44.5">FK</text></g><path d="M 200 52 M 360 52 M 360 78 M 200 78" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" font-weight="bold" text-decoration="underline" clip-path="url(#mx-clip-235-58-120-24-0)" font-size="12px"><text x="236.5" y="70.5">PROFESSION_ID</text></g><path d="M 200 52 M 230 52 L 230 78 M 200 78" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><g fill="#000000" font-family="Helvetica" clip-path="url(#mx-clip-205-58-20-24-0)" font-size="12px"><text x="206.5" y="70.5">FK</text></g><path d="M 200 78 M 360 78 M 360 88 M 200 88" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 200 78 M 230 78 L 230 88 M 200 88" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 200 65 L 180 65 L 180 41 L 166.37 41" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 161.12 41 L 168.12 37.5 L 166.37 41 L 168.12 44.5 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 360 39 L 380 39 L 380 41 L 393.63 41" fill="none" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/><path d="M 398.88 41 L 391.88 44.5 L 393.63 41 L 391.88 37.5 Z" fill="#000000" stroke="#000000" stroke-miterlimit="10" pointer-events="none"/></g></svg>



######schema.sql
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
######data.sql
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
####@ManyToOne, @OneToMany
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


####@ManyToMany
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


####Готово
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

