package fr.hgwood.codestory.elevator.long_;

import static fr.hgwood.codestory.elevator.Action.*;
import static fr.hgwood.codestory.elevator.Direction.DOWN;
import static fr.hgwood.codestory.elevator.Direction.UP;
import static fr.hgwood.codestory.elevator.long_.TestUtils.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.*;

// Can't run all the tests at once.
// Spark is not meant to be used like this (creating multiple instances). It simply doesn't work. I'll have to fix that.
//@Ignore
public class ForwardsEventsToElevatorTest {
    
    @ClassRule public static final ServerFixture fixture = new ServerFixture();
    
    @Test public void call() throws Exception {
        requestTo("/call?atFloor=0&to=UP");
        verify(fixture.listener).call(0, UP);
        requestTo("/call?atFloor=1&to=DOWN");
        verify(fixture.listener).call(1, DOWN);
    }
    
    @Test public void go() throws Exception {
        requestTo("/go?cabin=0&floorToGo=0");
        verify(fixture.listener).go(0, 0);
        requestTo("/go?cabin=1&floorToGo=1");
        verify(fixture.listener).go(1, 1);
    }
    
    @Test public void userHasEntered() throws Exception {
        requestTo("/userHasEntered?cabin=0");
        verify(fixture.listener).userHasEntered(0);
        requestTo("/userHasEntered?cabin=1");
        verify(fixture.listener).userHasEntered(1);
    }
    
    @Test public void userHasExited() throws Exception {
        requestTo("/userHasExited?cabin=0");
        verify(fixture.listener).userHasExited(0);
        requestTo("/userHasExited?cabin=1");
        verify(fixture.listener).userHasExited(1);
    }
    
    @Test public void nextCommands() throws Exception {
        when(fixture.listener.nextCommands()).thenReturn(asList(Open, Close));
        assertThat(requestTo("/nextCommands"), responds("OPEN\nCLOSE"));
    }
    
    @Test public void reset() throws Exception {
        String resetUrl = "/reset?" +
            "lowerFloor=0&" +
            "higherFloor=1&" + 
            "cabinSize=1&" + 
            "cabinCount=1&" +
            "cause=information+message";
        requestTo(resetUrl);
        verify(fixture.listener).reset(0, 1, 1, 1, "information message");
        resetUrl = "/reset?" +
            "lowerFloor=-5&" +
            "higherFloor=40&" + 
            "cabinSize=3&" + 
            "cabinCount=4&" +
            "cause=information";
        requestTo(resetUrl);
        verify(fixture.listener).reset(-5, 40, 3, 4, "information");
    }

}
