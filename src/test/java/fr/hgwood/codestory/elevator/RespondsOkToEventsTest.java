package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.TestUtils.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RespondsOkToEventsTest {

    private Server sut = new Server();

    @Before public void setup() {
        sut.start(TestPort);
    }

    @After public void tearDown() {
        sut.stop();
    }

    @Test public void call() throws Exception {
        assertThat(requestTo("/call?atFloor=0&to=UP"), respondsOk());
    }

    @Test public void go() throws Exception {
        assertThat(requestTo("/go?floorToGo=0"), respondsOk());
    }

    @Test public void userHasEntered() throws Exception {
        assertThat(requestTo("/userHasEntered"), respondsOk());
    }

    @Test public void userHasExited() throws Exception {
        assertThat(requestTo("/userHasExited"), respondsOk());
    }

    @Test public void reset() throws Exception {
        String resetUrl = "/reset?" +
            "lowerFloor=0&" +
            "higherFloor=1&" + 
            "cabinSize=1&" + 
            "cause=information+message";
        assertThat(requestTo(resetUrl), respondsOk());
    }

    @Test public void nextCommand() throws Exception {
        assertThat(requestTo("/nextCommand"), respondsOk());
    }

}
