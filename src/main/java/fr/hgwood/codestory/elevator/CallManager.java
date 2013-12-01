package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Direction.UP;

import java.util.Collection;

import com.google.common.collect.HashMultiset;

public class CallManager {
    
    private Collection<Integer> upCalls = HashMultiset.create();
    private Collection<Integer> downCalls = HashMultiset.create();
    
    public void add(int floor, Direction direction) {
        if (direction == UP) upCalls.add(floor);
        else downCalls.add(floor);
    }
    
    public void remove(int floor, Direction direction) {
        if (direction == UP) upCalls.remove(floor);
        else downCalls.remove(floor);
    }
    
    public boolean wasCalledAt(int floor, Direction direction) {
        if (direction == UP) return upCalls.contains(floor);
        else return downCalls.contains(floor);
    }

}
