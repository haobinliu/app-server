package com.example.appserver;

import com.example.appserver.common.EncodeUtil;
import com.example.appserver.common.TokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.example.appserver.common.EncodeUtil.encrypt;
import static com.example.appserver.common.EncodeUtil.verify;

@SpringBootTest
class AppServerApplicationTests {

    @Autowired
    private TokenManager tokenManager;


    @Test
    void contextLoads() {
    }

    @Test
    public void tokenPru(){

        System.out.println(this.tokenManager.createToken(200));
    }

    @Test
    public void SRAtest(){
        Map<String,Object> kMap;
        EncodeUtil encodeUtil1 = new EncodeUtil();

        try {
            kMap = encodeUtil1.initKey();
            String publickeyStr = encodeUtil1.createPublicKeyStr(kMap);
            String privateKeyStr = encodeUtil1.createPrivateKeyStr(kMap);
            System.out.println("公钥-----------"+publickeyStr);
            System.out.println("私钥-----------"+privateKeyStr);
            String test = "nihao securaty!";
            System.out.println("测试数据-------"+test);
            byte[] encodeText = encodeUtil1.encrypt(test.getBytes(),publickeyStr);
            System.out.println("加密数据-------"+new String(encodeText));
            byte[] decodeText = encodeUtil1.decrybt(encodeText,privateKeyStr);
            System.out.println("解密数据-------"+new String(decodeText));
            System.out.println("-----------------------签名验签------------------------------");
            String verfiy = "标准验签内容";
            byte[] signturn = encodeUtil1.sign(verfiy.getBytes(),privateKeyStr);
            System.out.println("签名------"+new String(signturn));
            boolean sign = encodeUtil1.verify(verfiy.getBytes(),signturn,publickeyStr);
            System.out.println("验签------"+sign);

            System.out.println("md5:"+encodeUtil1.md5("admin123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> keyMap;
        EncodeUtil encodeUtil = new EncodeUtil();
        byte[] cipherText;
        String input = "Hello World!";
        try {
            keyMap = encodeUtil.initKey();
            String publicKey = encodeUtil.createPublicKeyStr(keyMap);
            System.out.println("公钥------------------");
            System.out.println(publicKey);
            String privateKey = encodeUtil.createPrivateKeyStr(keyMap);
            System.out.println("私钥------------------");
            System.out.println(privateKey);

            System.out.println("测试可行性-------------------");
            System.out.println("明文======="+input);

            cipherText = encrypt(input.getBytes(),publicKey);
            //加密后的东西
            System.out.println("密文======="+new String(cipherText));
            //开始解密
            byte[] plainText = encodeUtil.decrybt(cipherText,privateKey);
            System.out.println("解密后明文===== " + new String(plainText));
            System.out.println("验证签名-----------");

            String str="被签名的内容";
            System.out.println("\n原文:"+str);
            byte[] signature=encodeUtil.sign(str.getBytes(),privateKey);
            boolean status=verify(str.getBytes(), signature,publicKey);
            System.out.println("验证情况："+status);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
