package fr.hgwood.codestory.elevator;

import java.util.List;

public interface ElevatorSystem {
    
    void call(int floor, Direction direction);
    void go(int cabin, int floor);
    void userHasEntered(int cabin);
    void userHasExited(int cabin);
    List<Action> nextCommands();

}
