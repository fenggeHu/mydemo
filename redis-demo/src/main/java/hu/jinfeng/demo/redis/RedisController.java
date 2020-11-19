package hu.jinfeng.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author hujinfeng  @Date 2020/11/14
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/index")
    public String index() {
        return "Hello, WebFlux !";
    }

    @GetMapping("/getExpire")
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @GetMapping("/get")
    public DemoDO get(String key) {
        ValueOperations<String, DemoDO> value = redisTemplate.opsForValue();
        return value.get(key);
    }

    /**
     * spring-boot-starter-webflux /web 两种方式测试可用如下：
     * 1，需要 method = RequestMethod.PUT
     * 2，webflux方式：String key, Integer updated，这两个参数需要放到链接上才能被绑定
     * 3，web(Spring MVC)方式：放params或者body（form-data）都可以绑定上
     * 4，两种方式下，DemoDO里的属性放params或body都能映射上
     * 总结：Spring mvc参数绑定使用更方便
     * @param key
     * @param updated
     * @param demo
     * @return
     */
    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public DemoDO putData(String key, Integer updated, DemoDO demo) {
        ValueOperations<String, DemoDO> value = redisTemplate.opsForValue();
        if (1 == updated) {
            value.set(key, demo, 50, TimeUnit.SECONDS);
        } else {
            // 判断key值是否存在，存在则不存储，不存在则存储
            value.setIfAbsent(key, demo, 50, TimeUnit.SECONDS);
        }

        return value.get(key);
    }
}
