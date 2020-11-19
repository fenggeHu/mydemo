package hu.jinfeng.demo.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hujinfeng  @Date 2020/11/14
 **/
public class LettuceTest {

    public static void main(String[] args) {

        RedisURI redisUri = RedisURI.builder()                    // <1> 创建单机连接的连接信息
                .withHost("localhost")
                .withPort(6379)
                .withPassword("123456")
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);   // <2> 创建客户端
        StatefulRedisConnection<String, String> connection = redisClient.connect();     // <3> 创建线程安全的连接
        RedisCommands<String, String> redisCommands = connection.sync();                // <4> 创建同步命令
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        //set string
        String result = redisCommands.set("name", "go home", setArgs);
        System.out.println(result);
        result = redisCommands.get("name");
        System.out.println(result);
        //写入map
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "jinfeng");
        map.put("last_name", "hu");
//        map.put("age", "15");
        redisCommands.mset(map);
        result = redisCommands.get("name") + "-" + redisCommands.get("last_name");
        System.out.println(result);
        map.put("name", "leo");
        map.put("last_name", "cai");
        //nx：键必须 不存在，才可以设置成功，用于 添加。
        redisCommands.msetnx(map);
        result = redisCommands.get("name") + "-" + redisCommands.get("last_name");
        System.out.println(result);
        //
        Long l = redisCommands.incrby("age", 3);
        System.out.println(l);

        boolean success = redisCommands.expireat("age", System.currentTimeMillis() + 5000);
        System.out.println(success);
        // ... 其他操作
        connection.close();   // <5> 关闭连接
        redisClient.shutdown();  // <6> 关闭客户端
    }
}
