package hu.jinfeng.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String DEFAULT_TOPIC = "demo";

    @Override
    public void send(final String topic, String key, String value) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, key, value);

        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onFailure(Throwable throwable) {
                log.error("kafka send msg err, ex = {}, topic = {}, data = {}", throwable, null == topic ? DEFAULT_TOPIC : topic, value);
            }

            @Override
            public void onSuccess(SendResult<String, String> integerStringSendResult) {
                log.info("kafka send msg success, topic = {}, data = {}", null == topic ? DEFAULT_TOPIC : topic, value);
            }
        });
    }
}
