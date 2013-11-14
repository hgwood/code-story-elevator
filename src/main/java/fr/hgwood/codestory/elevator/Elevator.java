package fr.hgwood.codestory.elevator;

public interface Elevator {
    
    void call(int atFloor, Direction to);
    void go(int floorToGo);
    void userHasEntered();
    void userHasExited();
    Action next();

}
