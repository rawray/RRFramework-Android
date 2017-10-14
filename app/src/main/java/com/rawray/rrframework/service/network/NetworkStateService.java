package com.rawray.rrframework.service.network;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by rawray on 17-7-14.
 */

public class NetworkStateService extends Service {

    public static final String BROADCAST_ACTION_NETWORKSTATE = "BROADCAST_ACTION_NETWORKSTATE";
    public static final String BROADCAST_ACTION_NETWORKSTATE_KEY_NETWORK_STATE = "KEY_NETWORK_STATE";
    public static NetworkStatus networkStatus;

    private ConnectivityManager connectivityManager;
    private NetworkInfo info;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    String name = info.getTypeName();
                    if (name.equals(NetworkStatus.WIFI.getName())) {
                        networkStatus = NetworkStatus.WIFI;
                    } else {
                        networkStatus = NetworkStatus.MOBILE;
                    }
                } else {
                    Log.w("NetworkStateService", "没有可用网络!");
                    networkStatus = NetworkStatus.NONE;

                    Intent it = new Intent();
                    it.putExtra(BROADCAST_ACTION_NETWORKSTATE_KEY_NETWORK_STATE, networkStatus);
                    it.setAction(BROADCAST_ACTION_NETWORKSTATE);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                }
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

}
