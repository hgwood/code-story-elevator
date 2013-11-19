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
    private Set<Integer> floorsToStopAt = newHashSet();
    private int currentFloor;
    private Direction currentDirection = UP;
    private boolean isOpened;
    private int currentNumberOfUsers;
    
    public Omnibus() {
        
    }
    
    public Omnibus(int lowestFloor, int highestFloor, int cabinSize) {
        this.lowestFloor = lowestFloor;
        this.highestFloor = highestFloor;
        this.cabinSize = cabinSize;
    }

    @Override public void call(int atFloor, Direction to) {
        floorsToStopAt.add(atFloor);
    }

    @Override public void go(int floorToGo) {
        floorsToStopAt.add(floorToGo);

    }

    @Override public void userHasEntered() {
        if (currentNumberOfUsers >= cabinSize) 
            throw new ElevatorException("cabin is full!");
        currentNumberOfUsers += 1;
    }

    @Override public void userHasExited() {
        if (currentNumberOfUsers <= 0) 
            throw new ElevatorException("can't exit: no one in the cabin!");
        currentNumberOfUsers -= 1;
    }

    @Override public Action next() {
        if (isOpened) {
            return close();
        }
        if (floorsToStopAt.contains(currentFloor)) {
            return open();
        }
        if (currentFloor == highestFloor) {
            currentDirection = DOWN;
        }
        if (currentFloor == lowestFloor) {
            currentDirection = UP;
        }
        if (currentDirection == UP) {
            return up();
        } else {
            return down();
        }
    }
    
    private Action open() {
        floorsToStopAt.remove(currentFloor);
        isOpened = true;
        return Open;
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

    @Override
    public void reset(int lowerFloor, int higherFloor, int cabinSize) {
        this.lowestFloor = lowerFloor;
        this.highestFloor = higherFloor;
        this.cabinSize = cabinSize;
        this.floorsToStopAt = newHashSet();
        this.currentFloor = 0;
        this.currentDirection = UP;
        this.isOpened = false;
        this.currentNumberOfUsers = 0;
    }

}
