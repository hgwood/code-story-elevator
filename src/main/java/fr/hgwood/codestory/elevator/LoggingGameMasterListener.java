package fr.hgwood.codestory.elevator;

import java.util.List;

import org.slf4j.Logger;

public class LoggingGameMasterListener implements GameMasterListener {
    
    private final GameMasterListener delegate;
    private final Logger log;

    public LoggingGameMasterListener(GameMasterListener delegate, Logger log) {
        this.delegate = delegate;
        this.log = log;
    }

    @Override public void call(int floor, Direction direction) {
        delegate.call(floor, direction);
    }

    @Override public void go(int cabin, int floor) {
        delegate.go(cabin, floor);
    }

    @Override public void userHasEntered(int cabin) {
        delegate.userHasEntered(cabin);
    }

    @Override public void userHasExited(int cabin) {
        delegate.userHasExited(cabin);
    }

    @Override public List<Action> nextCommands() {
        List<Action> actions = delegate.nextCommands();
        log.info("responded to next: {}", actions);
        return actions;
    }

    @Override public void reset(int lowestFloor, int highestFloor, int cabinSize, int cabinCount, String cause) {
        log.warn("reset! cause: {}", cause);
        delegate.reset(lowestFloor, highestFloor, cabinSize, cabinCount, cause);
    }

}
