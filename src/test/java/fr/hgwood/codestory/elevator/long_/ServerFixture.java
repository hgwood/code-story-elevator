package fr.hgwood.codestory.elevator.long_;

import static fr.hgwood.codestory.elevator.long_.TestUtils.TestPort;
import static org.mockito.Mockito.mock;

import org.junit.rules.ExternalResource;

import fr.hgwood.codestory.elevator.GameMasterListener;
import fr.hgwood.codestory.elevator.SparkGameMaster;

public class ServerFixture extends ExternalResource {
    
    public final GameMasterListener listener = mock(GameMasterListener.class);
    private SparkGameMaster server;
    
    @Override protected void before() {
        server = new SparkGameMaster(listener);
        server.start(TestPort);
    }
    
    @Override protected void after() {
        server.stop();
    }

}
