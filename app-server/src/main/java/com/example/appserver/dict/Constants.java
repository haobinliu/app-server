package com.example.appserver.dict;

public class Constants {
    /**
     * redis超时时间
     */
    public static final Long TOKEN_EXPIRE_HOUR = 12L;

    /**
     *密钥算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 私钥
     */
    public static final String  PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 加密方式
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA最大加密明文大小
     */
    public static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    public static final int MAX_DECRYPT_BLOCK = 128;
}
