package BusinessLogic;

import model.Server;
import model.Task;

import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxServers;
    private int maxTaskServer;
    private Strategy strategy;

    public Scheduler(int maxServers,int maxTaskServer) {

    }

    public void changeStrategy(SelectionPolicy policy) {

        if(policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new TimeStrategy();
        }
    }

    public void dispatchTask(Task t) {

    }

    public List<Server> getServers() {
        return servers;
    }
}
