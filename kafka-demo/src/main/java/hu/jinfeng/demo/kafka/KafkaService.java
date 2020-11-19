package hu.jinfeng.demo.kafka;

/**
 *
 */
public interface KafkaService {

    void send(String topic, String key, String value);

}
