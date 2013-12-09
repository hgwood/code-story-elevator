package fr.hgwood.codestory.elevator;

import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.setPort;

import org.slf4j.LoggerFactory;

import spark.*;

import com.google.common.base.Joiner;

public class Server {
    
    public static void main(String[] args) {
        new Server(
            new LoggingGameMasterListener(
                new ResetableElevatorSystem(),
                LoggerFactory.getLogger("GameMasterListener")))
            .start(8080);
    }
    
    private final GameMasterListener listener;
    
    public Server(GameMasterListener listener) {
        this.listener = listener;
    }

    public void start(int port) {
        setPort(port);
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "";
            }
        });
        get(new Route("/call") {
            @Override
            public Object handle(Request request, Response response) {
                int atFloor = parseInt(request.queryParams("atFloor"));
                Direction to = Direction.valueOf(request.queryParams("to"));
                listener.call(atFloor, to);
                return "";
            }
        });
        get(new Route("/go") {
            @Override
            public Object handle(Request request, Response response) {
                int cabin = parseInt(request.queryParams("cabin"));
                int floorToGo = parseInt(request.queryParams("floorToGo"));
                listener.go(cabin, floorToGo);
                return "";
            }
        });
        get(new Route("/userHasEntered") {
            @Override
            public Object handle(Request request, Response response) {
                int cabin = parseInt(request.queryParams("cabin"));
                listener.userHasEntered(cabin);
                return "";
            }
        });
        get(new Route("/userHasExited") {
            @Override
            public Object handle(Request request, Response response) {
                int cabin = parseInt(request.queryParams("cabin"));
                listener.userHasExited(cabin);
                return "";
            }
        });
        get(new Route("/reset") {
            @Override
            public Object handle(Request request, Response response) {
                int lowestFloor = parseInt(request.queryParams("lowerFloor"));
                int highestFloor = parseInt(request.queryParams("higherFloor"));
                int cabinSize = parseInt(request.queryParams("cabinSize"));
                int cabinCount = parseInt(request.queryParams("cabinCount"));
                String cause = request.queryParams("cause");
                listener.reset(lowestFloor, highestFloor, cabinSize, cabinCount, cause);
                return "";
            }
        });
        get(new Route("/nextCommands") {
            @Override
            public Object handle(Request request, Response response) {
                return Joiner.on('\n').join(listener.nextCommands()).toUpperCase();
            }
        });
    }
    
    public void stop() {
        spark.Spark.stop();
    }
}
