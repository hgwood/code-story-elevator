package fr.hgwood.codestory.elevator;

import org.slf4j.LoggerFactory;

public class Main {
    
    public static void main(String[] args) {
        new SparkGameMaster(
            new Synchronized(
                new Logging(
                    new ResetableElevatorSystem(),
                    LoggerFactory.getLogger(GameMasterListener.class))))
            .start(8080);
    }

}
