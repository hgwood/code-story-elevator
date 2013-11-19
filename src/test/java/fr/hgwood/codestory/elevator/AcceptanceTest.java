package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.TestUtils.TestPort;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class AcceptanceTest {
    
    @Mock protected Elevator elevator;
    private Server sut;

    @Before public void setup() {
        sut = new Server(elevator);
        sut.start(TestPort);
    }

    @After public void tearDown() {
        sut.stop();
    }

}
