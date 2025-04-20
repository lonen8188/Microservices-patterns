    department-service -> port: 8088

    ## application.properties #####################
    spring.application.name=department-service
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3307/department_db?allowPublicKeyRetrieval=true&useSSL=false
    spring.datasource.username=root
    spring.datasource.password=1234

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.open-in-view=false
    ###############################################

    user-service -> port: 8087

    ## application.properties #####################
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    server.port=8088
    spring.application.name=user-service
    spring.datasource.url=jdbc:mysql://localhost:3308/user_db?allowPublicKeyRetrieval=true&useSSL=false
    spring.datasource.username=root
    spring.datasource.password=1234

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.open-in-view=false
    ###############################################

    - department-service 입력
    http://127.0.0.1:8087/api/departments -> POST 방식으로 호출
    {
        "id" : 1, 
        "departmentName" : "IT Admin",
        "departmentAddress" : "Busan",
        "departmentCode" : "1"
    }
    http://127.0.0.1:8087/api/users -> POST 방식으로 호출
    - user-service 입력
    {
        "firstName" : "Dennis",
        "lastName" : "Lee",
        "email" : "itgenius1004@gmail.com",
        "departmentId" : "1"
    }

    consumer-service 
    producer-service 

    bin/kafka-console-consumer.sh --topic mytopic2 --from-beginning --bootstrap-server localhost:9092

    * Apache Kafka 직접 실행하기
    $ bin/zookeeper-server-start.sh config/zookeeper.properties
    $ bin/kafka-server-start.sh config/server.properties

    order-service
    payment-service
    stock-service
    delivery-service

    주문 : order-service -> port: 8080 
    결재 : payment-service -> port: 8081
    재고 : stock-service -> port: 8082
    배송 : delivery-service -> port: 8083

    mysql -> port: 3310
    ordersdb, paymentsdb, stockdb, deliverydb

    -> 재고에 노트북 100개 추가
    curl --location 'http://127.0.0.1:8082/api/addItems' \
    --header 'Content-Type: application/json' \
    --data '{
        "item": "notebook",
        "quantity": 100
    }'

    -> 노트북 10개 주문
    curl --location 'http://127.0.0.1:8080/api/orders' \
    --header 'Content-Type: application/json' \
    --data '{
        "item": "notebook",
        "quantity": 10,
        "amount": 2000000,
        "paymentMode": "Credit Card",
        "orderId": 1,
        "address": "Seoul"
    }'


    * Docker 이미지 생성
    mvn compile -> class
    mvn package -> jar
    docker build -t user-service:1.0 .

    * 빌드팩을 사용하여 애플리케이션을 OCI 이미지로 패키징 -> Docker 이미지 생성
    mvn spring-boot:build-image 

    * Docker 이미지 실행
    - Docker 명령으로 실행
    - Docker Compose yml로 실행


    * 마이크로서비스 간의 동기식 통신 방식
    1. RestTemplate(O)
    2. WebClient(X)
    2. Spring Cloud Open Feign(O)

    * 마이크로서비스 간의 비동기식 통신 방식
    1. 메시지 브로커 기반 통신
    - Kafka사용
    - RabbitMQ사용용
    2. 이벤트 기반 아키텍처
    - Spring Cloud Stream
    3. 이벤트 소싱 패턴
    - Axon Framework사용


    * 배포본 내보내기, 가져오기
    https://learn.microsoft.com/ko-kr/windows/wsl/basic-commands?source=recommendations
