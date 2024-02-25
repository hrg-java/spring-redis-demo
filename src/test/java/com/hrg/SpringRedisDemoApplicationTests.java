package com.hrg;

import com.hrg.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringRedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testString() {
        redisTemplate.opsForValue().set("hrg", "hrg");
        Object name = redisTemplate.opsForValue().get("hrg");
        System.out.println(name);
    }

    @Test
    void testSaveUser() {
        redisTemplate.opsForValue().set("user:100", new User("hrg", 33));
        User o = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println(o);
    }
}
