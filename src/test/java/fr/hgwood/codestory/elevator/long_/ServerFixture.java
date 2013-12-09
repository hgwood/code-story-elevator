package fr.hgwood.codestory.elevator.long_;

import static fr.hgwood.codestory.elevator.long_.TestUtils.TestPort;
import static org.mockito.Mockito.mock;

import org.junit.rules.ExternalResource;

import fr.hgwood.codestory.elevator.GameMasterListener;
import fr.hgwood.codestory.elevator.Server;

public class ServerFixture extends ExternalResource {
    
    public final GameMasterListener listener = mock(GameMasterListener.class);
    private Server server;
    
    @Override protected void before() {
        server = new Server(listener);
        server.start(TestPort);
    }
    
    @Override protected void after() {
        server.stop();
    }

}
