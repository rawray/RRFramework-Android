package com.rawray.rrframework.utils;

import com.rawray.rrframework.service.network.NetworkStateService;
import com.rawray.rrframework.service.network.NetworkStatus;

/**
 * Created by rawray on 17-7-14.
 */

public class NetworkUtils {

    public static boolean avaliable() {
        return !(NetworkStatus.NONE.equals(NetworkStateService.networkStatus));
    }

}
