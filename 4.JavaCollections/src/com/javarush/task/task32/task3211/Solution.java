package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
    public static void main(final String... args) throws Exception {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(final ByteArrayOutputStream byteArrayOutputStream, final String md5) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] baos = byteArrayOutputStream.toByteArray();
        final byte[] hash = md.digest(baos);

        final StringBuilder sb = new StringBuilder(2 * hash.length);
        for (final byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        final String baosMd5 = sb.toString();

        return baosMd5.equals(md5);
    }
}
