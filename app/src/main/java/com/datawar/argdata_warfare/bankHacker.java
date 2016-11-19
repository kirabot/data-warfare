package com.datawar.argdata_warfare;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by Kill-o-bite on 11/17/2016.
 */

public class bankHacker {

    protected String name;
    protected String description;

    private int progress;
    private Random r = new Random();
    int timeHelper;
    final int start;
    final int end;
    int hourNow;
    // Calendar hoursNow = Calendar.getInstance();


    bankHacker(){
        timeHelper = (int)(r.nextInt(24) + 1);
        // commented for test purpose
        start = timeHelper;
        end = timeHelper +1;
        hourNow = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        // testing for sure
//        start = hourNow;
//        end = start +1;
    }



    public void setProgress(int k){
        this.progress = k;
    }

    public int getProgress(){
        return this.progress;
    }

    public void timer(boolean check){

    }

    public int reward(){
        return r.nextInt(10000-1000+1)+1000;
    }
}
