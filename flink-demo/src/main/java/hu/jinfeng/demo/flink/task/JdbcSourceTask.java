package hu.jinfeng.demo.flink.task;

import hu.jinfeng.demo.model.ItemDO;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.concurrent.TimeUnit;

/**
 * @Author hujinfeng  @Date 2020/11/24
 **/
public class JdbcSourceTask {

    public static void main(String[] args) {

        //1，创建流处理环境
        StreamExecutionEnvironment sEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        //1.1，设置重启策略
        sEnv.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, Time.of(10, TimeUnit.SECONDS)));
        //1.2，设置时间特性
        sEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        //1.3，设置自动水位间隔时间（ms）//Watermarks是一种衡量Event Time进展的机制，基于已经收集的消息来估算是否还有消息未到达
        sEnv.getConfig().setAutoWatermarkInterval(1000);
        //1.4，启动流作业的检查点
        sEnv.enableCheckpointing(5000);

        JdbcConnectionOptions jdbcOptions = new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                .withUrl("jdbc:mysql://localhost:3308/con?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false")
                .withDriverName("com.mysql.cj.jdbc.Driver")
                .withUsername("admin").withPassword("admin@123456").build();


//        DataStreamSource<ItemDO> mysqlDataSource =

    }
}
