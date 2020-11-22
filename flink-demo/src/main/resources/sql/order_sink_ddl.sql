CREATE TABLE order_sink (
    wStart String,
    itemId BIGINT,
    amount DOUBLE
 ) WITH (
 'connector' = 'print'
)