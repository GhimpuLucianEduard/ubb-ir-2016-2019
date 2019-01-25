package com.company.Utils;

import com.company.Interfaces.Iterator;
import com.company.Interfaces.LinkedList;
import com.company.Interfaces.ThreadListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.company.Utils.Constants.AddedToList;
import static com.company.Utils.Constants.LOG_DATE_FORMAT;
import static com.company.Utils.Constants.RANDOM;

public class Worker extends Thread {

    private final List<ThreadListener> listeners = new ArrayList<>();
    int count;
    LinkedList list;
    OperationType operationType;
    public boolean canIterate = true;

    public Worker(int count, LinkedList list, OperationType operationType) {
        this.count = count;
        this.list = list;
        this.operationType = operationType;
    }

    @Override
    public void run() {
        switch (operationType) {
            case ADD:
                add();
                break;
            case DELETE:
                delete();
                break;
            case ITERATION:
                int iterationCount = 0;
                while (canIterate)
                {
                    iterate(iterationCount);
                    iterationCount++;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
        }
    }

    private void add() {
        for (int i = 0; i < count; i++) {
            double value = RANDOM.nextDouble();
            list.insert(value);
            String logString = LOG_DATE_FORMAT.format(new Date()) + ": " + Thread.currentThread().getName() + " [OPERATION] [ADD] Value: " + value;
            FileManager.getInstance(FileOutput.LOG).writeToFile(logString, FileOutput.LOG);
            AddedToList.add(value);
        }
        notifyListeners();
    }

    private void delete() {
        for (int i = 0; i < count; i++) {
            int index = RANDOM.nextInt(AddedToList.size());
            boolean shouldNotContinue = (AddedToList == null || AddedToList.size() == 0 || AddedToList.get(index) == null);
            if (!shouldNotContinue)
            {
                double value = AddedToList.get(index);
                list.delete(value);
                AddedToList.remove(value);
                String logString = LOG_DATE_FORMAT.format(new Date()) + ": " + Thread.currentThread().getName() + " [OPERATION] [DELETE] Value: " + value;
                FileManager.getInstance(FileOutput.LOG).writeToFile(logString, FileOutput.LOG);
            }
        }
        notifyListeners();
    }

    public void iterate(int iterationCount) {
        Iterator iterator = list.getIterator();
        String logString = LOG_DATE_FORMAT.format(new Date()) + ": " + Thread.currentThread().getName() + " [OPERATION] [ITERATION] Nr: " + iterationCount;
        FileManager.getInstance(FileOutput.LOG).writeToFile(logString, FileOutput.LOG);

        while (iterator.isValid())
        {
            Double element = iterator.getNext();
            FileManager.getInstance(FileOutput.VALUES).writeToFile(element.toString(), FileOutput.VALUES);
        }
    }

    private final void notifyListeners() {
        for (ThreadListener listener : listeners) {
            listener.notifyThreadComplete(this);
        }
    }

    public final void addListener(ThreadListener listener) {
        listeners.add(listener);
    }
    public final void removeListener(ThreadListener listener) {
        listeners.remove(listener);
    }
}
