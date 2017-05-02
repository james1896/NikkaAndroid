package com.never.nikkaandroid.base;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by toby on 02/05/2017.
 */

public class QRTimer {
    Timer timer;
    TimerTask timerTask;
    int  t = 10;
    QRTimerInterface timeInface;

    public  QRTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.e("QRTimer",""+t--);
                if(t == 0){
                    t = 10;
                    timeInface.runBlock();
                }
            }
        };
    }

    public void start(){

        this.timer.schedule(timerTask,1000,1000);
    }

    public void setTimerInterface(QRTimerInterface timerInterface){
        this.timeInface = timerInterface;
    }
}

