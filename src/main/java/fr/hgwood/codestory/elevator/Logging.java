package fr.hgwood.codestory.elevator;

import java.util.List;

import org.slf4j.Logger;

public class Logging implements GameMasterListener {
    
    private final GameMasterListener delegate;
    private final Logger log;

    public Logging(GameMasterListener delegate, Logger log) {
        this.delegate = delegate;
        this.log = log;
    }

    @Override public void call(int floor, Direction direction) {
        log.trace("received call at floor {} going {}", floor, direction);
        delegate.call(floor, direction);
    }

    @Override public void go(int cabin, int floor) {
        log.trace("cabin {} received go to floor {}", cabin, floor);
        delegate.go(cabin, floor);
    }

    @Override public void userHasEntered(int cabin) {
        log.trace("user has entered in cabin {}", cabin);
        delegate.userHasEntered(cabin);
    }

    @Override public void userHasExited(int cabin) {
        log.trace("user has exited cabin {}", cabin);
        delegate.userHasExited(cabin);
    }

    @Override public List<Action> nextCommands() {
        List<Action> actions = delegate.nextCommands();
        log.trace("responded to next: {}", actions);
        return actions;
    }

    @Override public void reset(int lowestFloor, int highestFloor, int cabinSize, int cabinCount, String cause) {
        log.warn("reset! cause: {}", cause);
        log.info("reset parameters: lowestFloor={}, highestFloor={}, cabinSize={}, cabinCount={}", lowestFloor, highestFloor, cabinSize, cabinCount);
        log.info("state before reset was: {}", delegate);
        delegate.reset(lowestFloor, highestFloor, cabinSize, cabinCount, cause);
        log.info("state after reset was: {}", delegate);
    }

}
