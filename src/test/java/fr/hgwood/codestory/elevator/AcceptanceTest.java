package fr.hgwood.codestory.elevator;

import static com.google.common.collect.Lists.newArrayList;
import static fr.hgwood.codestory.elevator.TestUtils.TestPort;
import static fr.hgwood.codestory.elevator.Action.*;
import static org.mockito.Mockito.*;

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
        when(elevator.next()).thenReturn(Open);
        sut = new Server(newArrayList(elevator));
        sut.start(TestPort);
    }

    @After public void tearDown() {
        sut.stop();
    }

}
