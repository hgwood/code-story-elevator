package fr.hgwood.codestory.elevator;

import static fr.hgwood.codestory.elevator.Action.Open;
import static fr.hgwood.codestory.elevator.TestUtils.TestPort;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

import org.junit.rules.ExternalResource;

public class EndToEndFixture extends ExternalResource {
    
    public final Elevator elevator = mock(Elevator.class);
    private Server server;
    
    @Override protected void before() {
        when(elevator.next()).thenReturn(Open);
        server = new Server(singletonList(elevator));
        server.start(TestPort);
    }
    
    @Override protected void after() {
        server.stop();
    }

}
