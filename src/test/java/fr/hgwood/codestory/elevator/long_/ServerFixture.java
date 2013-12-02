package fr.hgwood.codestory.elevator.long_;

import static fr.hgwood.codestory.elevator.Action.Open;
import static fr.hgwood.codestory.elevator.long_.TestUtils.TestPort;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

import org.junit.rules.ExternalResource;

import fr.hgwood.codestory.elevator.*;

public class ServerFixture extends ExternalResource {
    
    public final Elevator elevator = mock(Elevator.class);
    public final CallManager callManager = mock(CallManager.class);
    private Server server;
    
    @Override protected void before() {
        when(elevator.next()).thenReturn(Open);
        server = new Server(singletonList(elevator), callManager);
        server.start(TestPort);
    }
    
    @Override protected void after() {
        server.stop();
    }

}
