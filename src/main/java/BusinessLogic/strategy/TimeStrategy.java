package BusinessLogic.strategy;

import BusinessLogic.strategy.Strategy;
import model.Server;
import model.Task;

import java.util.Comparator;
import java.util.List;

public class TimeStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        servers.stream().min(Comparator.comparingInt(o -> o.getWaitingT().get())).get().addTask(t);

    }
}