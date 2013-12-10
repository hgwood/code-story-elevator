package fr.hgwood.codestory.elevator;

import org.slf4j.LoggerFactory;

public class Main {
    
    public static void main(String[] args) {
        new SparkGameMaster(
            new SynchronizingGameMasterListener(
                new LoggingGameMasterListener(
                    new ResetableElevatorSystem(),
                    LoggerFactory.getLogger("GameMasterListener"))))
            .start(8080);
    }

}
