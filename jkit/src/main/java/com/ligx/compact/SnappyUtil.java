package com.ligx.compact;

import org.xerial.snappy.Snappy;

import java.io.IOException;

/**
 * Author: ligongxing.
 * Date: 2017年03月06日.
 * Description: 这是Google开发的一个非常流行的压缩算法，它旨在提供速度与压缩比都相对较优的压缩算法。
 */
public class SnappyUtil {

    public static byte[] compress(byte[] srcBytes){
        try {
            return Snappy.compress(srcBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static byte[] uncompress(byte[] srcBytes){
        try {
            return Snappy.uncompress(srcBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
