package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private ArrayBlockingQueue<Task> tasks;
    private Task currentTask;
    private AtomicInteger waitingT;
    private boolean isStopped;
    private int queueNo;
    private static int queueId=0;
    private static int maxPerQ=10;


    private int distortId() {
        queueId++;
        return queueId;
    }

    public Server() {
        tasks = new ArrayBlockingQueue<Task>(maxPerQ);
        waitingT = new AtomicInteger(0);
        queueNo = distortId();
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setWaitingTime(this.waitingT.get());
        waitingT.getAndAdd(task.getServiceTime());
    }

    @Override
    public void run() {
        while(!this.isStopped) {
            try {
                if(!this.getTasks().isEmpty()) {
                    currentTask = tasks.take();
                    Thread.sleep(1000L * currentTask.getServiceTime());
                    waitingT.addAndGet(-1 * currentTask.getServiceTime());
                    currentTask = null;
                }

            } catch(InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public ArrayBlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingT() {
        return waitingT;
    }

    public int getQueueNo() {
        return queueNo;
    }

    public static int getQueueId() {
        return queueId;
    }

    public Task getCurrentTask() {
        return currentTask;
    }
}
