package com.company;

import com.google.common.base.Stopwatch;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Parinte extends Thread {

    private int interval;
    private Stopwatch stopwatch;

    public Parinte(int interval, Stopwatch stopwatch) {
        this.interval = interval;
        this.stopwatch = stopwatch;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (stopwatch) {
                while ( (int) (stopwatch.elapsedTime(TimeUnit.SECONDS)) % interval != 0) {
                    FileManager.getInstance().writeToFile("elp time " + (int) stopwatch.elapsedTime(TimeUnit.SECONDS) );
                    try {
                        FileManager.getInstance().writeToFile("Thread cu interval: " + interval + " asteapta");
                        stopwatch.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                FileManager.getInstance().writeToFile("Thread cu interval: " + interval);
                stopwatch.notify();
            }
            try {
                Thread.sleep(interval*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
