package com.vicning.android.bibilicopycat;

import com.vicning.android.bibilicopycat.common.Secret;
import com.vicning.android.bibilicopycat.utils.MD5Util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void md5Test() {
//        String appkey = "f3bb208b3d081dc8";
        /*String appkey = "1d8b6e7d45233436";
        String cid = "9226090";
        String secretkey = "560c52ccd288fed045859ed18bffd973";
        String sign = string2MD5("appkey=" + appkey + "&cid=" + cid + secretkey);
        System.out.println(sign);
        String url = "http://interface.bilibili.com/playurl?appkey=" + appkey + "&cid=" + cid + "&sign=" + sign;
        System.out.println(url);*/

        try {
            String key = "老菊";
            String encode = URLEncoder.encode(key, "utf-8");
            System.out.println(encode);

            LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();

            String urlChinese =
                    "appkey=1d8b6e7d45233436&" +
                    "keyword=" + encode + "&" +
                    "main_ver=v3&" +
                    "page=2&" +
                    "pagesize=20" +
                     Secret.appSecret;

            String sign = MD5Util.string2MD5(urlChinese);

            String url = "http://api.bilibili.com/search?" +
                    "appkey=1d8b6e7d45233436&" +
                    "keyword=" + key + "&" +
                    "main_ver=v3&" +
                    "page=2&" +
                    "pagesize=20&" +
                    "sign="  + sign;
            System.out.println(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}