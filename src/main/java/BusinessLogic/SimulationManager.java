package BusinessLogic;

import BusinessLogic.strategy.SelectionPolicy;
import GUI.view.SimulationFrame;
import model.Server;
import model.Task;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationManager implements Runnable{

    // ex data, this should all be read from UI
    public int timeLimit=30;
    public int maxProcessingTime=10;
    public int minProcessingTime=2;
    public int minArrivalTime=1;
    public int maxArrivalTime=5;
    public int noServers=3;
    public int noClients=10;
    public int maxTaskServer=10;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    public int totalWaitingTime = 0;
    public float avgServiceTime=0;
    public float avgWaitingTime=0;
    public int peakTime=0;
    public int maxClientsAtTime=0;
    public int currClients=0;

    // the back end stuff
    // responsible with queues management and client distribution
    private Scheduler scheduler;
    // gui frame
    private SimulationFrame frame;
    //private Controller controller;
    // task pool
    private List<Task> genTasks;

    private PrintStream fileOut;
    private PrintStream originalOut;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int minArrivalTime, int maxArrivalTime, int noServers, int noClients,SimulationFrame sim) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.noServers = noServers;
        this.noClients = noClients;
        this.frame=sim;

        try {
            fileOut = new PrintStream("./out.txt");
            originalOut = System.out;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scheduler = new Scheduler(noServers, selectionPolicy);
        genTasks = generateTasks();
        genTasks.stream().forEach(task -> System.out.println(task.toString()));
        scheduler.getServers().forEach(server -> new Thread(server).start());
    }

    private List<Task> generateTasks() {
        List<Task> t = new ArrayList<>();
        for (int i = 0; i < this.noClients; i++) {
            t.add(new Task(ThreadLocalRandom.current().nextInt(minArrivalTime, maxArrivalTime + 1),
                    ThreadLocalRandom.current().nextInt(minProcessingTime, maxProcessingTime + 1)));
            avgServiceTime+=t.get(i).getServiceTime();
        }
        avgServiceTime /= t.size();
        return t;
    }

    private void printBoth(String message) {
        System.setOut(originalOut);
        System.out.println(message);
        System.setOut(fileOut);
        System.out.println(message);
    }

    public void update() {
        genTasks.stream().forEach(task -> avgWaitingTime += task.getWaitingTime());
        avgWaitingTime /= noClients;
        System.out.println("Stopping simulation : ");
        frame.updateMinOut("Total clients served : " + this.noClients);
        frame.updateMinOut("Average service time : " + this.avgServiceTime);
        frame.updateMinOut("Average waiting time : " + this.avgWaitingTime);
        frame.updateMinOut("Peak hour : " + peakTime + " with " + maxClientsAtTime + " clients.");
        System.out.println("STOP");
    }

    @Override
    public void run() {
        int currTime=0;
        while(currTime<=timeLimit) {
            int finalCurrTime = currTime;
            currClients=0;
            genTasks.stream()
                    .filter(task -> task.getArrivalTime() == finalCurrTime)
                    .forEach(task -> scheduler.dispatchTask(task));
            //update stuff
            frame.updateLogs();
            frame.updateCurrentTime(String.valueOf(finalCurrTime));
            scheduler.getServers().forEach(server -> frame.updateQueue(server));
            scheduler.getServers().forEach(server -> currClients+=server.getTasks().size());
            if(currClients>maxClientsAtTime) {
                maxClientsAtTime=currClients;
                peakTime = currTime;
            }

            printBoth("Current time of execution: "+currTime);
            printQ(originalOut);
            printQ(fileOut);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currTime++;
        }
        update();
        for (Server server : scheduler.getServers()) {
            server.setStopped(true);
        }
    }

    public void printQ(PrintStream p) {
        System.setOut(p);

        scheduler.getServers().
                forEach(server -> {
                    System.out.print("Queue "+ server.getQueueNo()+": ");
                    if(server.getCurrentTask()!=null)
                        System.out.print(server.getCurrentTask().toString());
                    System.out.println(server.getTasks().toString());
                        }
                       );
        System.setOut(originalOut);
    }


}
