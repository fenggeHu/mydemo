CREATE TABLE order_source (
    id BIGINT ,
    itemId BIGINT ,
    price DOUBLE ,
    msg STRING ,
    orderDate  TIMESTAMP ,
    WATERMARK FOR orderDate AS orderDate - INTERVAL '5' SECOND
) WITH (
    'connector' = 'kafka-0.11',
    'topic' = 'order',
    'properties.bootstrap.servers' = 'localhost:9092',
    'format' = 'json',
    'scan.startup.mode' = 'earliest-offset'
)
