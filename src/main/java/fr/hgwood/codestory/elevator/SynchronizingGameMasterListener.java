package fr.hgwood.codestory.elevator;

import java.util.List;

public class SynchronizingGameMasterListener implements GameMasterListener {
    
    private final GameMasterListener delegate;

    public SynchronizingGameMasterListener(GameMasterListener delegate) {
        this.delegate = delegate;
    }

    @Override public synchronized void call(int floor, Direction direction) {
        delegate.call(floor, direction);
    }

    @Override public synchronized void go(int cabin, int floor) {
        delegate.go(cabin, floor);
    }

    @Override public synchronized void userHasEntered(int cabin) {
        delegate.userHasEntered(cabin);
    }

    @Override public synchronized void userHasExited(int cabin) {
        delegate.userHasExited(cabin);
    }

    @Override public synchronized List<Action> nextCommands() {
        return delegate.nextCommands();
    }

    @Override public synchronized void reset(int lowestFloor, int highestFloor, int cabinSize, int cabinCount, String cause) {
        delegate.reset(lowestFloor, highestFloor, cabinSize, cabinCount, cause);
    }

}
