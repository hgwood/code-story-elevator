package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.TestUtils.TestPort;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
