package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Direction.UP;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class CallManager {
    
    private Multiset<Integer> upCalls = HashMultiset.create();
    private Multiset<Integer> downCalls = HashMultiset.create();
    
    public void add(int floor, Direction direction) {
        if (direction == UP) upCalls.add(floor);
        else downCalls.add(floor);
    }
    
    public void remove(int floor, Direction direction) {
        if (direction == UP) upCalls.remove(floor);
        else downCalls.remove(floor);
    }
    
    private void remove(int floor, Direction direction, int n) {
        if (direction == UP) upCalls.remove(floor, n);
        else downCalls.remove(floor, n);
    }
    
    public boolean wasCalledAt(int floor, Direction direction) {
        if (direction == UP) return upCalls.contains(floor);
        else return downCalls.contains(floor);
    }

    public void reserve(int currentFloor, Direction currentDirection, int n) {
        remove(currentFloor, currentDirection, n);
    }

}
