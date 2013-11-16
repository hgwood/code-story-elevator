package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.TestUtils.*;
import static org.junit.Assert.assertThat;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationTest {
    
    @Mock
    private Elevator elevator;
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
        assertThat(requestTo("/call"), responds(200));
    }
    
    @Test
    public void respondsOKToGo() throws Exception {
        assertThat(requestTo("/go"), responds(200));
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
        assertThat(requestTo("/reset"), responds(200));
    }
    
    @Test
    public void respondsNothingToNextCommand() throws Exception {
        assertThat(requestTo("/nextCommand"), responds("NOTHING"));
    }

}
