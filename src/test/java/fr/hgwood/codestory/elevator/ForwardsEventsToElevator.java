package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Direction.DOWN;
import static fr.hgwood.codestory.elevator.Direction.UP;
import static fr.hgwood.codestory.elevator.TestUtils.requestTo;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class ForwardsEventsToElevator extends AcceptanceTest {
    
    @Test public void call() throws Exception {
        requestTo("/call?atFloor=0&to=UP");
        verify(elevator).call(0, UP);
        requestTo("/call?atFloor=1&to=DOWN");
        verify(elevator).call(1, DOWN);
    }

}
