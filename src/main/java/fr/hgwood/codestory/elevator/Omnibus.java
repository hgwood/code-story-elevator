package fr.hgwood.codestory.elevator;

import static com.google.common.collect.Sets.newHashSet;
import static fr.hgwood.codestory.elevator.Action.*;
import static fr.hgwood.codestory.elevator.Direction.DOWN;
import static fr.hgwood.codestory.elevator.Direction.UP;

import java.util.Set;

public class Omnibus implements Elevator {
    
    private int lowestFloor;
    private int highestFloor;
    private int cabinSize;
    private Set<Integer> floorsWherePeopleWantToOut = newHashSet();
    private int currentFloor;
    private Direction currentDirection;
    private boolean isOpened;
    private int currentNumberOfUsers;
    private final CallManager callManager;
    
    public Omnibus(int lowestFloor, int highestFloor, int cabinSize) {
        this(new CallManager(), lowestFloor, highestFloor, cabinSize, UP);
    }
    
    public Omnibus(CallManager callManager, int lowestFloor, int highestFloor, int cabinSize, Direction initialDirection) {
        this.callManager = callManager;
        this.lowestFloor = lowestFloor;
        this.highestFloor = highestFloor;
        this.cabinSize = cabinSize;
        this.currentDirection = initialDirection;
    }

    @Override public void call(int atFloor, Direction to) {
        callManager.add(atFloor, to);
    }

    @Override public void go(int floorToGo) {
        floorsWherePeopleWantToOut.add(floorToGo);
    }

    @Override public void userHasEntered() {
        if (cabinIsFull()) throw new IllegalStateException("cabin is full!");
        currentNumberOfUsers += 1;
    }

    @Override public void userHasExited() {
        if (cabinIsEmpty()) throw new IllegalStateException("can't exit: no one in the cabin!");
        currentNumberOfUsers -= 1;
    }

    @Override public Action next() {
        if (currentFloor == highestFloor) currentDirection = DOWN;
        if (currentFloor == lowestFloor) currentDirection = UP;
        if (isOpened) return close();
        if (floorsWherePeopleWantToOut.contains(currentFloor)) return open();
        if (!cabinIsFull() && callManager.wasCalledAt(currentFloor, currentDirection)) return open();
        if (currentDirection == UP) return up();
        else return down();
    }
    
    private boolean cabinIsFull() {
        return currentNumberOfUsers >= cabinSize;
    }
    
    private boolean cabinIsEmpty() {
        return currentNumberOfUsers <= 0;
    }
    
    private Action open() {
        callManager.remove(currentFloor, currentDirection);
        floorsWherePeopleWantToOut.remove(currentFloor);
        isOpened = true;
        if (currentDirection == UP) return Open_Up;
        else return Open_Down;
    }
    
    private Action close() {
        isOpened = false;
        return Close;
    }
    
    private Action up() {
        currentFloor += 1;
        return Up;
    }
    
    private Action down() {
        currentFloor -= 1;
        return Down;
    }

}
