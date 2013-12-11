package fr.hgwood.codestory.elevator;

import static com.google.common.base.Objects.toStringHelper;
import static fr.hgwood.codestory.elevator.Direction.UP;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class ResetableElevatorSystem implements GameMasterListener {
    
    private ElevatorSystem delegate;

    public ResetableElevatorSystem() {
        reset(0, 1, 1, 1, "");
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
        return delegate.nextCommands();
    }

    @Override public void reset(int lowestFloor, int highestFloor, int cabinSize, int cabinCount, String cause) {
        ImmutableList.Builder<Elevator> elevators = ImmutableList.builder();
        CallManager callManager = new CallManager();
        int idleFloorStep = (highestFloor - lowestFloor) / (cabinCount + 1);
        Direction direction = UP;
        for (int i = 0; i < cabinCount; i++) {
            elevators.add(new MercuryElevator(callManager, lowestFloor, highestFloor, cabinSize, direction, (i + 1) * idleFloorStep));
            direction = direction.reverse();
        }
        delegate = new MercuryElevatorSystem(elevators.build(), callManager);
    }
    
    @Override public String toString() {
        return toStringHelper(this.getClass())
            .add("delegate", delegate)
            .toString();
    }

}
