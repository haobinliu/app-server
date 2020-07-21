package com.example.appserver.common;

import com.example.appserver.Dict.Constants;
import com.example.appserver.domain.ArchUser;
import org.omg.IOP.ENCODING_CDR_ENCAPS;
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

public class LoginUtil
{
    @Autowired
    private RedisTemplate redisTemplate;

    public String getPublicKey(ArchUser archUser) throws NoSuchAlgorithmException{
        //先从redis中取，如果不存在就在生成一次
        //RSAPrivateKey key = (String)redisTemplate.opsForValue().get("privateKey");
        Map<String,Object> keyMap;
        String publicKey;

        keyMap=EncodeUtil.initKey();
        publicKey = EncodeUtil.createPublicKeyStr(keyMap);
        String privateKey = EncodeUtil.createPrivateKeyStr(keyMap);
        String pubKeyStr = String.valueOf(archUser.getUserId()) + "_publicKey";
        String priKeyStr = String.valueOf(archUser.getUserId()) + "_privateKey";
        redisTemplate.opsForValue().set(pubKeyStr,publicKey, Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
        redisTemplate.opsForValue().set(priKeyStr,privateKey,Constants.TOKEN_EXPIRE_HOUR, TimeUnit.HOURS);
        return publicKey ;
    }

    public String decrybtPas(ArchUser archUser){
        String priKeyStr = String.valueOf(archUser.getUserId()) + "_privateKey";

        String privateKey = (String)redisTemplate.opsForValue().get("privateKey");

        try {
            byte[] passarr = EncodeUtil.decrybt(archUser.getPassword().getBytes(),privateKey);
            String password = EncodeUtil.md5(new String(passarr));
            return password;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


}
