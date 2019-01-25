package com.company;

import com.company.CoarseGrainedSync.CGLinkedList;
import com.company.GrainSync.GSLinkedList;
import com.company.Utils.FileManager;
import com.company.Utils.FileOutput;
import com.company.Utils.OperationType;
import com.company.Utils.Worker;;import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //GSLinkedList list = new GSLinkedList();
        CGLinkedList list = new CGLinkedList();

        Worker addWorker = new Worker(10, list, OperationType.ADD);
        Worker deleteWorker = new Worker(7, list, OperationType.DELETE);
        Worker addWorker2 = new Worker(5, list, OperationType.ADD);
        Worker iterationWorker = new Worker(1, list, OperationType.ITERATION);

        final int[] threadsToWait = {3};
        Thread threadToNotify = iterationWorker;

        List<Worker> threads = new ArrayList<>();
        threads.add(addWorker);
        threads.add(deleteWorker);
        threads.add(addWorker2);

        for (Worker worker : threads) {
            worker.addListener(thread -> {
                threadsToWait[0]--;
                if (threadsToWait[0] == 0 && threadToNotify != null)
                {
                    ((Worker) threadToNotify).canIterate = false;
                }
            });
        }

        long startTime = System.currentTimeMillis();
        iterationWorker.start();
        addWorker.start();
        Thread.sleep(100);
        deleteWorker.start();
        addWorker2.start();

        try {
            addWorker.join();
            deleteWorker.join();
            addWorker2.join();
            iterationWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        String log = "Elapsed time: " + String.valueOf(endTime-startTime);
        FileManager.getInstance(FileOutput.LOG).writeToFile(log, FileOutput.LOG);
    }
}
