package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Direction.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CallManagerTest {
    
    private final CallManager sut = new CallManager();
    
    @Test public void test() {
        sut.add(0, UP);
        assertTrue(sut.wasCalledAt(0, UP));
        assertFalse(sut.wasCalledAt(1, UP));
        assertFalse(sut.wasCalledAt(0, DOWN));
        sut.remove(0, UP);
        assertFalse(sut.wasCalledAt(0, UP));
    }
    
    @Test public void aReservationErasesCalls() {
        sut.add(0, UP);
        sut.reserve(0, UP, 1);
        assertFalse(sut.wasCalledAt(0, UP));
    }
    
    @Test public void aReservationForOneDoesntEraseMoreThanOneCall() {
        sut.add(0, UP);
        sut.add(0, UP);
        sut.reserve(0, UP, 1);
        assertTrue(sut.wasCalledAt(0, UP));
    }
    
    @Test public void aReservationForTwoErasesTwoCalls() {
        sut.add(0, UP);
        sut.add(0, UP);
        sut.reserve(0, UP, 2);
        assertFalse(sut.wasCalledAt(0, UP));
    }
    
    @Test public void canTellIfThereAreSomeUpCallsAbove() {
        sut.add(1, UP);
        assertTrue(sut.hasCalls(0, UP));
    }
    
    @Test public void canTellIfThereAreSomeUpCallsBelow() {
        sut.add(0, UP);
        assertTrue(sut.hasCalls(1, DOWN));
    }
    
    @Test public void canTellIfThereAreSomeDownCallsAbove() {
        sut.add(1, DOWN);
        assertTrue(sut.hasCalls(0, UP));
    }
    
    @Test public void canTellIfThereAreSomeDownCallsBelow() {
        sut.add(0, DOWN);
        assertTrue(sut.hasCalls(1, DOWN));
    }
    
    @Test public void canTellIfThereAreNoCallsAboveOrBelow() {
        assertFalse(sut.hasCalls(0, UP));
        assertFalse(sut.hasCalls(0, DOWN));
    }
    
    @Test public void doesntTellThereAreSomeCallsAboveIfThereAreBelow() {
        sut.add(0, UP);
        assertFalse(sut.hasCalls(1, UP));
    }
    
    @Test public void canTellIfThereAreNoCallsAtAll() {
        assertFalse(sut.hasCalls());
    }
    
    @Test public void canTellIfThereAreSomeCalls() {
        sut.add(0, UP);
        assertTrue(sut.hasCalls());
    }

}
