package com.hrg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrg.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisStringTests {

    /**
     * 比较常用 自己去手动序列化和反序列化
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("hrg", "hrg");
        Object name = stringRedisTemplate.opsForValue().get("hrg");
        System.out.println(name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testSaveUser() throws JsonProcessingException {
        User user = new User("hrg2", 33);
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:101", json);
        String jsonUser = stringRedisTemplate.opsForValue().get("user:101");
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println(user1);
    }

    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:400", "name", "hrg");
        stringRedisTemplate.opsForHash().put("user:400", "age", "2112");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println(entries);
    }
}
