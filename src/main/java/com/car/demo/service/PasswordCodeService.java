package com.car.demo.service;

import org.springframework.stereotype.Service;

/**
 * cookie字符串加密解密模型 适用于符号、数字、字母，不适用于中文
 */
@Service
public class PasswordCodeService {
    /**
     * 解密：把一个加密后的字符串在原有基础上-1
     * @param data 加密后的字符串
     * @return 返回解密后的新字符串
     */
    public String decode(String data) {
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //遍历
        for(int i=0;i<b.length;i++) {
            b[i] -= b.length%3*2-i;//在原有的基础上- b.length%3*2-i
        }
        return new String(b);
    }
    /**
     * 加密，把一个字符串在原有的基础上+1
     * @param data 需要解密的原字符串
     * @return 返回解密后的新字符串
     */
    public String encode(String data) {
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //遍历
        for(int i=0;i<b.length;i++) {
            b[i] += b.length%3*2-i;//在原有的基础上+ b.length%3*2-i
        }
        return new String(b);
    }
}
