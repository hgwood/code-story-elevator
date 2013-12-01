package fr.hgwood.codestory.elevator;

import static com.google.common.collect.Sets.newHashSet;
import static fr.hgwood.codestory.elevator.Direction.*;

import java.util.Set;

public class CallManager {
    
    private Set<Integer> upCalls = newHashSet();
    private Set<Integer> downCalls = newHashSet();
    
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
