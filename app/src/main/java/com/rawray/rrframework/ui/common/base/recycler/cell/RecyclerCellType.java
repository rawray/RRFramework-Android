package com.rawray.rrframework.ui.common.base.recycler.cell;

import com.rawray.rrframework.utils.HashBiMap;

/**
 * Created by rawray on 17-6-22.
 */

public class RecyclerCellType {

    public static HashBiMap<Integer, Class<?>> cellTypeMaps = new HashBiMap<>();

    //----------------------- Static Methed -----------------------------------
    public static int typeFromClass(Class<? extends AbsRecyclerCell> clazz) {
        int code = 0;
        Integer key = cellTypeMaps.getKey(clazz);
        if (key == null) {
            code = generateCode();
            cellTypeMaps.put(code, clazz);
        } else {
            code = key;
        }
        return code;
    }

    public static Class<?> classFromType(int type) {
        return cellTypeMaps.getValue(type);
    }

    //----------------------- Private Methed ----------------------------------
    private static int generateCode() {
        return cellTypeMaps.size() + 1;
    }

}
