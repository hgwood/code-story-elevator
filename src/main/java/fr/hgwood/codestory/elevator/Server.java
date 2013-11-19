package fr.hgwood.codestory.elevator;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import spark.*;

public class Server {
    public static void main(String[] args) {
        new Server(new Omnibus()).start(8080);
    }
    
    private final Elevator elevator;
    
    public Server(Elevator elevator) {
        this.elevator = elevator;
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
                elevator.call(atFloor, to);
                return "";
            }
        });
        get(new Route("/go") {
            @Override
            public Object handle(Request request, Response response) {
                int floorToGo = parseInt(request.queryParams("floorToGo"));
                elevator.go(floorToGo);
                return "";
            }
        });
        get(new Route("/userHasEntered") {
            @Override
            public Object handle(Request request, Response response) {
                elevator.userHasEntered();
                return "";
            }
        });
        get(new Route("/userHasExited") {
            @Override
            public Object handle(Request request, Response response) {
                elevator.userHasExited();
                return "";
            }
        });
        get(new Route("/reset") {
            @Override
            public Object handle(Request request, Response response) {
                int lowerFloor = parseInt(request.queryParams("lowerFloor"));
                int higherFloor = parseInt(request.queryParams("higherFloor"));
                int cabinSize = parseInt(request.queryParams("cabinSize"));
                String cause = request.queryParams("cause");
                elevator.reset(lowerFloor, higherFloor, cabinSize);
                return "";
            }
        });
        get(new Route("/nextCommand") {
            @Override
            public Object handle(Request request, Response response) {
                return elevator.next().toString().toUpperCase();
            }
        });
    }
    
    public void stop() {
        spark.Spark.stop();
    }
}
