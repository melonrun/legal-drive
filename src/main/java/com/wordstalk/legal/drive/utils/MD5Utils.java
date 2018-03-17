package com.wordstalk.legal.drive.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * Created by guoyong on 2018/1/19.
 */
public class MD5Utils {

    private final static String LD_SORT = "LD_SECRET_SORT";

    public static String hexMD5(String value){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update((value+LD_SORT).getBytes("utf-8"));
            byte[] digest = messageDigest.digest();
            return Hex.encodeHexString(digest);
        }catch(Exception e){
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
        String value = "partner";
        System.out.println(hexMD5(value));
    }
}
