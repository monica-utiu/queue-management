package BusinessLogic;

import GUI.SimulationFrame;
import model.Task;

import java.util.List;

public class SimulationManager implements Runnable{

    // ex data, this should all be read from UI
    public int timeLimit=100;
    public int maxProcessingTime=10;
    public int minProcessingTime=2;
    public int noServers=3;
    public int noClients=100;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    // the back end stuff
    // responsible with queues management and client distribution
    private Scheduler scheduler;
    // gui frame
    private SimulationFrame frame;
    // task pool
    private List<Task> genTasks;

    public SimulationManager() {

    }

    private void generateTasks() {

    }

    @Override
    public void run() {
        int currTime=0;
        while(currTime<timeLimit) {

        }
        currTime++;
    }

    public static void main(String[] args) {
        SimulationManager simulationManager = new SimulationManager();
        Thread t=new Thread(simulationManager);
        t.start();
    }
}
