package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.TestUtils.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BasicAcceptanceTest {

    private Server sut = new Server();

    @Before
    public void setup() {
        sut.start(TestPort);
    }

    @After
    public void tearDown() {
        sut.stop();
    }

    @Test
    public void respondsOKToRoot() throws Exception {
        assertThat(requestTo("/"), responds(200));
    }

    @Test
    public void respondsOKToCall() throws Exception {
        assertThat(requestTo("/call?atFloor=0&to=UP"), responds(200));
    }

    @Test
    public void respondsOKToGo() throws Exception {
        assertThat(requestTo("/go?floorToGo=0"), responds(200));
    }

    @Test
    public void respondsOKToUserHasEntered() throws Exception {
        assertThat(requestTo("/userHasEntered"), responds(200));
    }

    @Test
    public void respondsOKToUserHasExited() throws Exception {
        assertThat(requestTo("/userHasExited"), responds(200));
    }

    @Test
    public void respondsOKToReset() throws Exception {
        assertThat(requestTo("/reset?lowerFloor=0&higherFloor=1&cabinSize=1&cause=information+message"), responds(200));
    }

    @Test
    public void respondsNothingToNextCommand() throws Exception {
        assertThat(requestTo("/nextCommand"), responds("NOTHING"));
    }

}
