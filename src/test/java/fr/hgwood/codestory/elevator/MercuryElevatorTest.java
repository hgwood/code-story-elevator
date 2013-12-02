package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Action.*;
import static fr.hgwood.codestory.elevator.Direction.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class MercuryElevatorTest {
    
    private final Elevator sut = new MercuryElevator(0, 2, 1);
    
    @Test public void goesUpAndDownTheBuilding() {
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
    }
    
    @Test public void stopsAtCalledFloors() {
        sut.call(1, UP);
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Open_Up));
        sut.userHasEntered();
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Up));
    }
    
    @Test public void stopsAtDestinationFloors() {
        sut.userHasEntered();
        sut.go(1);
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Open_Up));
        sut.userHasExited();
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Up));
    }
    
    @Test(expected=IllegalStateException.class) 
    public void respectsCabinSize() {
        sut.userHasEntered();
        sut.userHasEntered();
    }
    
    @Test public void doesntOpenIfTheCabinIsFullAndNoOneWantsToGetOut() {
        sut.userHasEntered();
        sut.call(1, UP);
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
    }
    
    @Test public void opensIfTheCabinIsFullButPeopleWantToGetOut() {
        sut.userHasEntered();
        sut.go(1);
        sut.call(1, UP);
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Open_Up));
        sut.userHasExited();
        sut.userHasEntered();
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Up));
    }

}
