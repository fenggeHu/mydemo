CREATE TABLE order_source (
    id BIGINT ,
    itemId BIGINT ,
    price DOUBLE ,
    msg STRING
    ,orderDate  time
) WITH (
    'connector' = 'kafka-0.11',
    'topic' = 'order',
    'properties.bootstrap.servers' = 'localhost:9092',
    'format' = 'json',
    'scan.startup.mode' = 'earliest-offset'
)
