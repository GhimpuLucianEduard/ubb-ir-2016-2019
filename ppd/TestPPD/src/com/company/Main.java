package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        PrintWriter pw = null;
        try {
            pw = new PrintWriter("C:\\Users\\Fabby\\Documents\\TestPPD\\src\\com\\company\\log.log");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
        FileManager.CALENDAR = Calendar.getInstance();
        Stopwatch stopwatch = new Stopwatch();

        // Read n and delta
        System.out.println("Numar de parinti: ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int stop = 10;
        List<Integer> intervale = new ArrayList<>();
        List<Parinte> parintes = new ArrayList<>();
        while (num > 0) {
            System.out.println("Parinte : " + num);
            parintes.add(new Parinte(in.nextInt(), stopwatch));
            num--;
        }

        stopwatch.start();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    try {
                        Thread.sleep(1000);
                        FileManager.getInstance().writeToFile("time: " + stopwatch.elapsedTime(TimeUnit.SECONDS));
                        synchronized (stopwatch) {
                            stopwatch.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
        thread.start();

        parintes.forEach(t -> {
            t.start();
        });

        parintes.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(1500);
//        Parinte p1 = new Parinte(2, stopwatch);
//        Parinte p2 = new Parinte(3, stopwatch);
//        Parinte p3 = new Parinte(4, stopwatch);
//        Parinte p4 = new Parinte(5, stopwatch);


//        p1.start();
//        p2.start();
//        p3.start();
//        p4.start();
//
//        p1.join();
//        p2.join();
//        p3.join();
//        p4.join();
    }
}
