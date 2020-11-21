package hu.jinfeng.demo.flink.task;

import com.google.gson.Gson;
import hu.jinfeng.demo.flink.OrderJsonFlatMapFunction;
import hu.jinfeng.demo.flink.OrderJsonMapFunction;
import hu.jinfeng.demo.model.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * datastream api
 *
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Slf4j
public class KafkaSourceTask1 {

    public static void main(String[] args) throws Exception {

        // Kafka参数
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.2.201:9092");
//        properties.setProperty("zookeeper.connect", "192.168.2.201:2181");
        properties.setProperty("group.id", "flink-group");

        String kafkaTopic = "demo";
        //1，连接数据源
        FlinkKafkaConsumer011<String> consumer =
                new FlinkKafkaConsumer011<>(kafkaTopic, new SimpleStringSchema(), properties);
        //2，加入数据源
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(1000, CheckpointingMode.EXACTLY_ONCE);

        DataStream<String> dataStream = env.addSource(consumer);
//        dataStream.print();
        dataStream.map(new MapFunction<String, OrderDO>() {
            final transient Gson gson = new Gson();
            @Override
            public OrderDO map(String value) throws Exception {
                OrderDO recorder = gson.fromJson(value, OrderDO.class);
                return recorder;
            }
        })
//        dataStream.flatMap(new OrderJsonFlatMapFunction())
                .keyBy(value -> value.getItemId())
                .timeWindow(Time.seconds(60), Time.seconds(6))
                .sum("price")
                .addSink(new SinkFunction() {
                    @Override
                    public void invoke(Object value, Context context) throws Exception {
                        System.out.println(value);
                        System.out.println("============context==========");
                        System.out.println(context);
                    }
                });

        env.execute("Log message receive");
    }
}
