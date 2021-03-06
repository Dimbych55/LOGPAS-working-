package com.example.logpas;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String toMd5(String str){
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(str.getBytes(),0,str.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}

