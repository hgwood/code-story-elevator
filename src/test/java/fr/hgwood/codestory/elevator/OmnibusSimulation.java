package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Action.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OmnibusSimulation {
    
    private final Elevator sut = new Omnibus(0, 19, 10);

    @Test public void run() {
        assertThat(sut.next(), is(Up)); //1
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        sut.call(10, null);
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        sut.call(16, null);
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up)); //9
        sut.call(5, null);
        assertThat(sut.next(), is(Up)); // 10
        assertThat(sut.next(), is(Open_Up));
        sut.userHasEntered();
        sut.go(1);
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up)); //16
        assertThat(sut.next(), is(Open_Up));
        sut.userHasEntered();
        sut.go(19);
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up));
        assertThat(sut.next(), is(Up)); //19
        assertThat(sut.next(), is(Open_Down));
        sut.userHasExited();
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down)); //15
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        sut.call(9, null);
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down)); //9
        assertThat(sut.next(), is(Open_Down));
        sut.userHasEntered();
        sut.go(1);
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Down)); //8
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down)); //5
        assertThat(sut.next(), is(Open_Down));
        sut.userHasEntered();
        sut.go(1);
        assertThat(sut.next(), is(Close));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down));
        assertThat(sut.next(), is(Down)); //1
        assertThat(sut.next(), is(Open_Down));
        sut.userHasExited();
        sut.userHasExited();
        sut.userHasExited();
        assertThat(sut.next(), is(Close));
    }
    
}
