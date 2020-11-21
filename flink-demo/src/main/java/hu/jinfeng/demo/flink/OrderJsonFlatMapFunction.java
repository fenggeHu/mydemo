package hu.jinfeng.demo.flink;

import com.google.gson.Gson;
import hu.jinfeng.demo.model.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * @Author hujinfeng  @Date 2020/11/20
 **/
@Slf4j
public class OrderJsonFlatMapFunction implements FlatMapFunction<String, OrderDO> {
    private final transient Gson gson = new Gson();

    @Override
    public void flatMap(String value, Collector<OrderDO> out) throws Exception {
        log.warn(value);
        OrderDO recorder = gson.fromJson(value, OrderDO.class);
        out.collect(recorder);
    }
}
