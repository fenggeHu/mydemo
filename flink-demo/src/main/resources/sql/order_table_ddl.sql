CREATE TABLE kafka_source ( 
    id BIGINT ,
    itemId BIGINT ,
    price DOUBLE ,
    msg STRING ,
    orderDate  Date
) WITH (
    'connector' = 'kafka-0.11',
    'topic' = 'order',
    'properties.bootstrap.servers' = 'localhost:9092',
    'format' = 'json',
    'scan.startup.mode' = 'earliest-offset'
)