package BusinessLogic;

import BusinessLogic.strategy.SelectionPolicy;
import BusinessLogic.strategy.ShortestQueueStrategy;
import BusinessLogic.strategy.Strategy;
import BusinessLogic.strategy.TimeStrategy;
import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Server> servers;
    private Strategy strategy;

    public Scheduler(int maxServers, SelectionPolicy strategy) {
       servers = new ArrayList<>();
       this.changeStrategy(strategy);
       for(int i=0;i<maxServers;i++) {
           servers.add(new Server());

       }
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

        strategy.addTask(servers,t);
    }

    public List<Server> getServers() {
        return servers;
    }
}
