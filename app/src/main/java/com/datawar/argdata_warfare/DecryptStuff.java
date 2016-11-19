package com.datawar.argdata_warfare;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import java.util.Random;

/**
 * Created by Kill-o-bite on 11/17/2016.
 */

public class DecryptStuff {
    Random r = new Random();
    DecryptStuff(){

    }

    public static boolean isConnected(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }

    public int howHardIsIt(){
        int difficulty = r.nextInt(9000-3000+1) + 3000;

        return difficulty;
    }

}
