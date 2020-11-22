package hu.jinfeng.demo.kafka.controller;

import com.google.gson.Gson;
import hu.jinfeng.demo.kafka.KafkaService;
import hu.jinfeng.demo.model.OrderDO;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@RestController
@RequestMapping("/kafka")
public class MakeLogController {

    @Autowired
    private KafkaService kafkaService;

    private Gson gson = new Gson();

    /**
     * 造kafka日志
     */
    @GetMapping(value = "/order")
    public String startLog(String topic, int num, int interval) throws InterruptedException {
        if (interval < 1) {
            interval = 1000;
        }
        for (int i = 0; i < num; i++) {
            OrderDO value = new OrderDO();
            value.setId(System.currentTimeMillis());
            value.setItemId(1000000L + i);
            value.setPrice(Double.valueOf(String.format("%.2f", RandomUtils.nextDouble(10, 200))));
            value.setMsg("订单号:" + value.getId() +", 商品ID:" + value.getItemId());
//            value.setOrderDate(new Timestamp(new Date().getTime()));
            value.setOrderDate(new Date());
            kafkaService.send(topic, null, gson.toJson(value));

            Thread.sleep(interval);
        }

        return "success";
    }

}
