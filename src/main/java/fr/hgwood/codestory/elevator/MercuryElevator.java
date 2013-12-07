package fr.hgwood.codestory.elevator;

import static com.google.common.collect.Sets.newHashSet;
import static fr.hgwood.codestory.elevator.Action.*;
import static fr.hgwood.codestory.elevator.Direction.DOWN;
import static fr.hgwood.codestory.elevator.Direction.UP;

import java.util.Set;

public class MercuryElevator implements Elevator {
    
    private final CallManager callManager;
    private final int lowestFloor;
    private final int highestFloor;
    private final int middleFloor;
    private final int cabinSize;
    private final Set<Integer> floorsWherePeopleWantToOut = newHashSet();
    private int currentFloor;
    private Direction currentDirection;
    private boolean isOpened;
    private int currentNumberOfUsers;
    
    public MercuryElevator(int lowestFloor, int highestFloor, int cabinSize) {
        this(new CallManager(), lowestFloor, highestFloor, cabinSize, UP);
    }
    
    public MercuryElevator(CallManager callManager, int lowestFloor, int highestFloor, int cabinSize, Direction initialDirection) {
        this.callManager = callManager;
        this.lowestFloor = lowestFloor;
        this.highestFloor = highestFloor;
        this.middleFloor = (highestFloor - lowestFloor) / 2;
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
        //callManager.remove(currentFloor, currentDirection); // this is done at reservation time now
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
        if (!cabinIsFull() && callManager.wasCalledAt(currentFloor, currentDirection)) {
            callManager.reserve(currentFloor, currentDirection, freeSpace());
            return open();
        }
        if (cabinIsEmpty() && callManager.wasCalledAt(currentFloor, currentDirection.reverse())) {
            currentDirection = currentDirection.reverse();
            callManager.reserve(currentFloor, currentDirection, freeSpace());
            return open();
        }
        if(cabinIsEmpty() && !callManager.hasCalls(currentFloor, currentDirection)) {
            currentDirection = currentDirection.reverse();
        }
        if(cabinIsEmpty() && !callManager.hasCalls()) {
            if (currentFloor > middleFloor) currentDirection = DOWN;
            else if (currentFloor < middleFloor) currentDirection = UP;
            else return Nothing;
        }
        if (currentFloor == highestFloor) currentDirection = DOWN;
        if (currentFloor == lowestFloor) currentDirection = UP;
        if (currentDirection == UP) return up();
        else return down();
    }
    
    private boolean cabinIsFull() {
        return currentNumberOfUsers >= cabinSize;
    }
    
    private boolean cabinIsEmpty() {
        return currentNumberOfUsers <= 0;
    }
    
    private int freeSpace() {
        return cabinSize - currentNumberOfUsers;
    }
    
    private Action open() {
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
