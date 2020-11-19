package hu.jinfeng.demo.flink;

import com.google.gson.Gson;
import hu.jinfeng.demo.flink.task.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;

import java.io.Serializable;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Slf4j
public class OrderJsonMapFunction implements MapFunction<String, OrderDO>, Serializable {
    private final Gson gson = new Gson();

    @Override
    public OrderDO map(String s) throws Exception {
        log.warn(s);

        try {
            return gson.fromJson(s, OrderDO.class);
        } catch (Exception e) {
            log.error(s, e);
        }
        return null;
    }
}
