package fr.hgwood.codestory.elevator;

import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class MercuryElevatorSystem implements ElevatorSystem {
    
    private final ImmutableList<Elevator> elevators;
    private final CallManager callManager;

    public MercuryElevatorSystem(ImmutableList<Elevator> elevators, CallManager callManager) {
        this.elevators = elevators;
        this.callManager = callManager;
    }

    @Override public void call(int floor, Direction direction) {
        callManager.add(floor, direction);
    }

    @Override public void go(int cabin, int floor) {
        elevators.get(cabin).go(floor);
    }

    @Override public void userHasEntered(int cabin) {
        elevators.get(cabin).userHasEntered();
    }

    @Override public void userHasExited(int cabin) {
        elevators.get(cabin).userHasExited();
    }

    @Override public List<Action> nextCommands() {
        List<Action> actions = newArrayList();
        for (Elevator elevator : elevators)
            actions.add(elevator.next());
        return actions;
    }
    
    @Override public String toString() {
        return toStringHelper(this.getClass())
            .add("elevators", elevators)
            .add("callManager", callManager)
            .toString();
    }

}
