package com.ligx.compact;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Author: ligongxing.
 * Date: 2017年03月06日.
 *
 * Description:
 * JDK deflate ——这是JDK中的又一个算法（zip文件用的就是这一算法）
 * 它与gzip的不同之处在于，你可以指定算法的压缩级别，这样你可以在压缩时间和输出文件大小上进行平衡
 * 可选的级别有0（不压缩），以及1(快速压缩)到9（慢速压缩）
 * 它的实现是java.util.zip.DeflaterOutputStream / InflaterInputStream
 */
public class DeflateUtil {

    public static byte[] compress(byte[] input) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(1);
        try {
            compressor.setInput(input);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        byte[] compressedByte = bos.toByteArray();
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressedByte;
    }

    public static byte[] uncompress(byte[] input) {
        if (input == null || input.length == 0) {
            return new byte[0];
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } catch (DataFormatException e){
            e.printStackTrace();
        } finally {
            decompressor.end();
        }
        return bos.toByteArray();
    }
}
