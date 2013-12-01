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

}
