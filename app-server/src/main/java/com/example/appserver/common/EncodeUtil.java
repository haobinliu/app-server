package com.example.appserver.common;

import com.example.appserver.Dict.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EncodeUtil {


    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 初始化公钥私钥，生成公私钥
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(Constants.KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(Constants.PUBLIC_KEY, publicKey);
        keyMap.put(Constants.PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 获取公钥字符串
     * @param keyMap
     * @return
     */
    public static String createPublicKeyStr(Map<String,Object> keyMap){
        //获取公钥对象转换为key
        Key key = (Key) keyMap.get(Constants.PUBLIC_KEY);
        //编码返回字符串
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 生成私钥对象
     * @param keyMap
     * @return
     */
    public static String createPrivateKeyStr(Map<String,Object> keyMap){
        //获取私钥对象，转换为key
        Key key = (Key) keyMap.get(Constants.PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 编码返回字符串
     * @param key
     * @return
     */
    public static String encryptBASE64(byte[] key){
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * 生成公钥
     * @param key
     * @return
     */
    public static PublicKey createPublicKey(String key) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //对传入的key进行解码
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        //指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
        //获取私钥对象
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 生成私钥
     * @param key
     * @return
     */
    public static PrivateKey createPrivateKey(String key) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    /**
     * 公钥加密
     * @param plainText
     * @param publicKeyStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(byte[] plainText,String publicKeyStr) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //获取公钥
        PublicKey publicKey = createPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance(Constants.KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int textSizr = plainText.length;
        int tmp = 0;
        int i = 0;

        byte[] cache;

        while(textSizr-tmp > 0){
            if(textSizr-tmp>Constants.MAX_ENCRYPT_BLOCK){
                cache = cipher.doFinal(plainText, tmp, Constants.MAX_ENCRYPT_BLOCK);
            }else{
                cache = cipher.doFinal(plainText, tmp,textSizr - tmp);
            }
            out.write(cache,0,cache.length);
            i++;
            tmp = i * Constants.MAX_ENCRYPT_BLOCK;
        }
        byte[] encryp = out.toByteArray();
        out.close();
        return encryp;
    }

    /**
     * 私钥解密
     * @param decryptText
     * @param privateKeyStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrybt(byte[] decryptText,String privateKeyStr) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        PrivateKey privateKey = createPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance(Constants.KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        int textSize = decryptText.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int tmp = 0;
        int i = 0;
        byte[] cache;
        while(textSize - tmp >0){
            if(textSize - tmp>Constants.MAX_DECRYPT_BLOCK){
                cache = cipher.doFinal(decryptText,tmp,Constants.MAX_DECRYPT_BLOCK);
            }else {
                cache = cipher.doFinal(decryptText,tmp,textSize - tmp);
                out.write(cache,0,cache.length);
                i++;
                tmp = i*Constants.MAX_DECRYPT_BLOCK;
            }

        }
        byte[] plaintText = out.toByteArray();
        out.close();
        return plaintText;
    }

    /**
     * 签名
     * @param data
     * @param privateKeyStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static byte[] sign(byte[] data,String privateKeyStr) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        PrivateKey privateKey = createPrivateKey(privateKeyStr);
        Signature sig = Signature.getInstance(Constants.SIGNATURE_ALGORITHM);
        sig.initSign(privateKey);
        sig.update(data);
        return sig.sign();
    }

    /**
     *验签
     * @param data
     * @param sign
     * @param publicKeyStr
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verify(byte[] data,byte[] sign,String publicKeyStr) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        PublicKey publicKey = createPublicKey(publicKeyStr);
        Signature sig = Signature.getInstance(Constants.SIGNATURE_ALGORITHM);
        sig.initVerify(publicKey);
        sig.update(data);
        return sig.verify(sign);
    }


    /**
     * 对密码进行加密
     * @param str
     * @return
     */
    public final static String md5(String str){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(str.getBytes());
        return Base64Utils.encodeToString(messageDigest.digest());
    }




}
