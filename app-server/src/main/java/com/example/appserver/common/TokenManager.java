package com.example.appserver.common;

import com.example.appserver.common.dict.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author dsl
 */
@Component
public class TokenManager {

    @Autowired
    private static StringRedisTemplate redisTemplate;
    /**
     * 生成一个令牌
     * @param userId 用户ID
     * @return 返回令牌
     */
    public static String createToken(int userId) {
        //生成Token,使用uuid
        UUID uuid = UUID.randomUUID();
        String token = userId+"_arch"
                +"_"+uuid.toString().replaceAll("-","");

        //将生成的token存入redis
        String key = userId + "_token";
        redisTemplate.opsForValue().set(key,token, Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
        return token;
    }

    /**
     * 判断返回的Token是否正确
     * @param token
     * @return true or false
     */
    public static boolean checkToken(String token){
        //判断返回头是否存在
        if(token == null || "".equals(token)){
            return false;
        }
        String[] tokenArr = token.split("_");
        if(tokenArr.length != 3){
            return false;
        }
        //从redis取出token进行检查
        String key = tokenArr[0]+"_token";

        String redisToken = redisTemplate.opsForValue().get(key);
        if(redisToken == null || !"".equals(redisToken)){
            return false;
        }else if(!token.equals(redisToken)){
            return false;
        }
        Boolean hasKey = redisTemplate.hasKey(key);
        if (!hasKey) {
            return false;
        }
        //当校验通过时，为确保保持活性，需要重新写入token
        redisTemplate.opsForValue().set(key, token, Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
        return true;
    }

    /**
     * 当用户注销时返回注销状态
     * @param token
     * @return true or false
     */
    public static boolean cmToken(String token){
        //判断返回头是否存在token
        if(token == null || "".equals(token)){
            return false;
        }
        String[] tokenArr = token.split("_");

        if(tokenArr.length != 3){
            return false;
        }

        //从redis取出token进行检查
        String key = tokenArr[0]+"_token";
        String or_token = redisTemplate.opsForValue().get(key);
        if(or_token == null || !"".equals(or_token)){
            return false;
        }else if(!token.equals(or_token)){
            return false;
        }
        //删除token
        redisTemplate.delete(key);
        return true;
    }
}
