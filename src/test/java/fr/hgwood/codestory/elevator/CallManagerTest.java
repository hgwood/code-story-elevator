package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Direction.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CallManagerTest {
    
    @Test public void test() {
        CallManager sut = new CallManager();
        sut.add(0, UP);
        assertTrue(sut.wasCalledAt(0, UP));
        assertFalse(sut.wasCalledAt(1, UP));
        assertFalse(sut.wasCalledAt(0, DOWN));
        sut.remove(0, UP);
        assertFalse(sut.wasCalledAt(0, UP));
    }
    
    @Test public void aReservationErasesCalls() {
        CallManager sut = new CallManager();
        sut.add(0, UP);
        sut.reserve(0, UP, 1);
        assertFalse(sut.wasCalledAt(0, UP));
    }
    
    @Test public void aReservationForOneDoesntEraseMoreThanOneCall() {
        CallManager sut = new CallManager();
        sut.add(0, UP);
        sut.add(0, UP);
        sut.reserve(0, UP, 1);
        assertTrue(sut.wasCalledAt(0, UP));
    }
    
    @Test public void aReservationForTwoErasesTwoCalls() {
        CallManager sut = new CallManager();
        sut.add(0, UP);
        sut.add(0, UP);
        sut.reserve(0, UP, 2);
        assertFalse(sut.wasCalledAt(0, UP));
    }

}
