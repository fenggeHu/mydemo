package hu.jinfeng.demo.flink.task;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;

/**
 * 读取Kafka消息数据，输出到控制台。
 *
 * @Author hujinfeng  @Date 2020/11/19
 */
public class Kafka2PrintTask {
    public static void main(String[] args) throws Exception {
        //0, Kafka
        String sourceDDL = "CREATE TABLE kafka_source (\n" +
                " msg STRING\n" +
                " ,id BIGINT \n" +
                " ,itemId BIGINT \n" +
                " ,price DOUBLE" +
                ") WITH (\n" +
                " 'connector' = 'kafka-0.11',\n" +
                " 'topic' = 'order',\n" +
                " 'properties.bootstrap.servers' = 'localhost:9092',\n" +
                " 'format' = 'json',\n" +
                " 'scan.startup.mode' = 'earliest-offset'\n" +   //earliest-offset 、 latest-offset
                ")";
        //

        //0, sink
        String sinkDDL = "CREATE TABLE print_sink (\n" +
                " msg STRING\n" +
                " ,id BIGINT \n" +
                " ,itemId BIGINT \n" +
                " ,price DOUBLE" +
                ") WITH (\n" +
                " 'connector' = 'print'\n" +
                ")";

        //1, 创建执行环境
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .useBlinkPlanner()
                .inStreamingMode()
                .build();
        TableEnvironment tEnv = TableEnvironment.create(settings);

        //2, 注册source和sink
        tEnv.executeSql(sourceDDL);
        tEnv.executeSql(sinkDDL);

        //3, 数据提取
        Table sourceTab = tEnv.from("kafka_source");
        //4, 数据写入sink
        sourceTab.insertInto("print_sink");
        //5, 执行作业
        tEnv.execute("Flink kafka source print");
    }
}