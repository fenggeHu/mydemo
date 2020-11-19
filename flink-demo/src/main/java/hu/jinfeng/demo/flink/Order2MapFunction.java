package hu.jinfeng.demo.flink;

import hu.jinfeng.demo.model.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Slf4j
public class Order2MapFunction implements MapFunction<String, OrderDO> {
    @Override
    public OrderDO map(String s) throws Exception {
        if (StringUtils.isBlank(s)) return null;

        try {
            String[] split = s.split(",");
            OrderDO orderDO = new OrderDO();
            orderDO.setId(Long.valueOf(split[0]));
            orderDO.setItemId(Long.valueOf(split[1]));
            orderDO.setPrice(Double.valueOf(split[2]));

            return orderDO;
        } catch (Exception e) {
            log.error("Order2MapFunction: " + e.getMessage(), e);
        }

        return null;
    }
}
