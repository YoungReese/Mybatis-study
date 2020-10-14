package com.ly.utils;

import org.apache.ibatis.cache.Cache;


/**
 * liyang 2020-10-15
 * 仅仅用来测试一下，所有方法均为空
 */
public class MyCache implements Cache {

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
