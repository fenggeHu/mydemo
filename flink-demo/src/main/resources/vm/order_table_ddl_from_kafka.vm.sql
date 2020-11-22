CREATE TABLE kafka_source ( 
    id BIGINT ,
    itemId BIGINT ,
    price DOUBLE ,
    msg STRING ,
    orderDate  Date
) WITH (
    'connector' = '${connector}',
    'topic' = '${topic}',
    'properties.bootstrap.servers' = '${properties_bootstrap_servers}',
    'format' = '${format}',
    'scan.startup.mode' = '${scan_startup_mode}'
)