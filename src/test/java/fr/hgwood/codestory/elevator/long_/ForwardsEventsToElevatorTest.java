package fr.hgwood.codestory.elevator.long_;

import static fr.hgwood.codestory.elevator.Direction.DOWN;
import static fr.hgwood.codestory.elevator.Direction.UP;
import static fr.hgwood.codestory.elevator.long_.TestUtils.requestTo;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;

public class ForwardsEventsToElevatorTest {
    
    @Rule public ServerFixture fixture = new ServerFixture();
    
    @Test public void call() throws Exception {
        requestTo("/call?atFloor=0&to=UP");
        verify(fixture.callManager).add(0, UP);
        requestTo("/call?atFloor=1&to=DOWN");
        verify(fixture.callManager).add(1, DOWN);
    }

}
