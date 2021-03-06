package hu.jinfeng.demo.flink;

import com.google.gson.Gson;
import hu.jinfeng.demo.model.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;

import java.io.Serializable;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Slf4j
public class OrderJsonMapFunction implements MapFunction<String, OrderDO>, Serializable {

    @Override
    public OrderDO map(String s) throws Exception {
        log.warn(s);

        try {
            return new Gson().fromJson(s, OrderDO.class);
        } catch (Exception e) {
            log.error(s, e);
        }
        return null;
    }
}
