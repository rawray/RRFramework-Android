package com.rawray.rrframework.service.network;

/**
 * Created by rawray on 17-7-14.
 */

public enum NetworkStatus {

    NONE(0, "NOT AVAILABLE"),
    MOBILE(1, "MOBILE"),
    WIFI(2, "WIFI");

    int code;
    String name;

    NetworkStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
