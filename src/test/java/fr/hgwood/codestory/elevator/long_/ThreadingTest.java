package fr.hgwood.codestory.elevator.long_;

import static fr.hgwood.codestory.elevator.Direction.UP;

import java.util.Random;

import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fr.hgwood.codestory.elevator.*;

public class ThreadingTest {
    
    private final int highestFloor = 10;
    private final int cabinCount = 10;
    private final Random random = new Random();
    private final GameMasterListener sut = 
        new Synchronized(
        new Logging(
            new ResetableElevatorSystem(), 
            LoggerFactory.getLogger(ThreadingTest.class)));
    
    @BeforeMethod public void setup() {
        sut.reset(0, highestFloor, 10, cabinCount, "");
    }
    
    @Test(threadPoolSize = 20, invocationCount = 100)
    public void test() throws Exception {
        sut.call(random.nextInt(highestFloor), UP);
        sut.go(random.nextInt(cabinCount), random.nextInt(highestFloor));
        int n = random.nextInt(cabinCount);
        sut.userHasEntered(n);
        sut.userHasExited(n);
        sut.nextCommands();
    }

}
