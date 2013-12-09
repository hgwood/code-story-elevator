package fr.hgwood.codestory.elevator;

import java.util.List;

public interface GameMasterListener {
    
    void call(int floor, Direction direction);
    void go(int cabin, int floor);
    void userHasEntered(int cabin);
    void userHasExited(int cabin);
    List<Action> nextCommands();
    void reset(int lowestFloor, int highestFloor, int cabinSize, int cabinCount);
    

}
