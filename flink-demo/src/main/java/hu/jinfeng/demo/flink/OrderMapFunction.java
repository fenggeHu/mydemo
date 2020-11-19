package hu.jinfeng.demo.flink;

import hu.jinfeng.demo.model.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Slf4j
public class OrderMapFunction implements MapFunction<String, OrderDO> {
    @Override
    public OrderDO map(String s) throws Exception {
        log.warn(s);

        String[] split = s.split(",");
        OrderDO orderDO = new OrderDO();
        orderDO.setId(Long.valueOf(split[0]));
        orderDO.setItemId(Long.valueOf(split[1].split(",")[1]));
        orderDO.setPrice(Double.valueOf(split[2].split(",")[2]));

        return orderDO;
    }
}
