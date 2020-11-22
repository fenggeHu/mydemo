package hu.jinfeng.demo.flink.task;

import hu.jinfeng.demo.flink.util.SQLBuilder;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
public class FlinkSQLTask {

    public static void main(String[] args) {

//        Properties properties = new Properties();
//        properties.setProperty("connector","kafka-0.11");
//        properties.setProperty("topic","order");
//        properties.setProperty("properties_bootstrap_servers","localhost:9092");
//        properties.setProperty("format","json");
//        properties.setProperty("scan_startup_mode","earliest-offset");

        String sourceDDL = SQLBuilder.readLocalSQL("sql/order_table_ddl.sql");

        System.out.println(sourceDDL);
    }
}
