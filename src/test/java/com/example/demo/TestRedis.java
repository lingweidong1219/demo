package com.example.demo;

import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception{
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
    @Test
    public void testObj() throws Exception {
        User user=new User("1","a","MAN");

        RedisSerializer stringRedisSerializer=new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("test", user);

        Thread.sleep(1000);
        boolean exists=redisTemplate.hasKey("test");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }

}
