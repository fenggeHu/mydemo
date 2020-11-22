package hu.jinfeng.demo.flink.task;

import hu.jinfeng.demo.flink.util.SQLBuilder;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.Encoder;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.OnCheckpointRollingPolicy;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
public class FlinkSQLTask {

    public static void main(String[] args) throws Exception {
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

        //2，运行环境的设置
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .useBlinkPlanner()
                .inStreamingMode()
                .build();

        //3，创建Flink SQL API环境
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(sEnv, settings);

        //4，Flink SQL
        //4.1 数据源表
        String sourceDDL = SQLBuilder.readLocalSQL("sql/order_table_ddl.sql");
        //4.2 目标表
        String sinkDDL = SQLBuilder.readLocalSQL("sql/order_sink_ddl.sql");
        //4.3 数据查询
        String querySQL = //"INSERT INTO order_sink " +
                "SELECT TUMBLE_START(orderDate, INTERVAL '5' SECOND) AS wStart, itemId, sum(price) AS amount " +
                        "FROM order_source  " +
                        "GROUP BY TUMBLE(orderDate, INTERVAL '5' SECOND), itemId ";


        //5, 注册source和sink
        tEnv.executeSql(sourceDDL);
        tEnv.executeSql(sinkDDL);

        //6, sql查询
//        String plan = tEnv.explainSql(querySQL);
//        System.out.println(plan);
        Table result = tEnv.sqlQuery(querySQL);

        //7, 结果表
        DataStream<Row> resultStream = tEnv.toAppendStream(result, Row.class);
        resultStream.print();

        sEnv.execute();
    }
}
