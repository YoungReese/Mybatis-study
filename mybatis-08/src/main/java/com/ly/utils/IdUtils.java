package com.ly.utils;

import java.util.UUID;

/**
 * liyang 2020-10-13
 *
 * 生成id的工具类
 * 内部封装了uuid，改造了一下uuid输出格式
 */
public class IdUtils {
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
