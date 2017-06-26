package com.rawray.rrframework.utils;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by rawray on 17-6-22.
 */

public class HashBiMap<K, V> extends HashMap<K, V> {

    public V getValue(Object key) {
        return get(key);
    }

    public K getKey(Object value) {
        Iterator iter = entrySet().iterator();
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            Object val = entry.getValue();
            if (val.equals(value)) {
                return (K) entry.getKey();
            }
        }
        return null;
    }
}
