package hu.jinfeng.demo.flink.task;

import hu.jinfeng.demo.flink.Order2MapFunction;
import hu.jinfeng.demo.model.OrderDO;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @Author hujinfeng  @Date 2020/11/19
 **/
public class SocketTextStreamSourceTask {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource dataStreamSource = env.socketTextStream("localhost", 7777);

        dataStreamSource.map(new Order2MapFunction()).filter(new FilterFunction<OrderDO>() {
            @Override
            public boolean filter(OrderDO value) throws Exception {
                return null != value && value.getPrice() > 50;
            }
        }).keyBy("itemId")
                .timeWindow(Time.seconds(30), Time.seconds(5))
                .sum("price")
                .print();

        env.execute(SocketTextStreamSourceTask.class.getName());
    }
}
