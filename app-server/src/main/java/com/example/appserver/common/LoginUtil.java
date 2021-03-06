package com.example.appserver.common;

import com.example.appserver.common.dict.Constants;
import com.example.appserver.model.entity.User;
import com.example.appserver.model.req.UserAuthReq;
import com.example.appserver.service.UserAuthService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author dsl
 */
public class LoginUtil
{
    @Autowired
    private static RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserAuthService userAuthService;

    public String getPublicKey() throws NoSuchAlgorithmException{
        //先从redis中取，如果不存在就在生成一次
        String privateKey = redisTemplate.opsForValue().get("privateKey");
        String publicKey = redisTemplate.opsForValue().get("publicKey");
        Map<String,Object> keyMap;

        if("".equals(privateKey) || StringUtil.isNullOrEmpty(privateKey)
                || "".equals(publicKey) || StringUtil.isNullOrEmpty(publicKey)){
            keyMap=EncodeUtil.initKey();
            publicKey = EncodeUtil.createPublicKeyStr(keyMap);
            privateKey = EncodeUtil.createPrivateKeyStr(keyMap);
            redisTemplate.opsForValue().set("publicKey",publicKey, Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
            redisTemplate.opsForValue().set("privateKey",privateKey,Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
        }

        return publicKey ;

    }

    public static String decrybtPas(User user){
        String privateKey = (String)redisTemplate.opsForValue().get("privateKey");
        try {
            byte[] passarr = EncodeUtil.decrybt(user.getPassWord().getBytes(),privateKey);
            return EncodeUtil.md5(new String(passarr));

        } catch (NoSuchAlgorithmException | IOException
                | InvalidKeySpecException | NoSuchPaddingException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static UserAuthReq loginAuth(UserAuthReq req){
        User user = new User();
        user.setPassWord(req.getPassWord());

        String encodePassWord = decrybtPas(user);
        req.setPassWord(encodePassWord);

        return req;
    }


}
