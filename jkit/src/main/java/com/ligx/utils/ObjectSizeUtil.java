package com.ligx.utils;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * Author: ligongxing.
 * Date: 2019/03/01.
 */
public class ObjectSizeUtil {

    public static void objectSize(Object obj) {
        //查看对象内部信息
        print(ClassLayout.parseInstance(obj).toPrintable());

        //查看对象外部信息
        print(GraphLayout.parseInstance(obj).toPrintable());

        //获取对象总大小
        print("total size : " + GraphLayout.parseInstance(obj).totalSize());
    }


    private static void print(String msg) {
        System.out.println(msg);
        System.out.println("-------------------");
    }
}
