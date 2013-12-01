package fr.hgwood.codestory.elevator;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import static fr.hgwood.codestory.elevator.Direction.*;

import java.util.Collections;
import java.util.List;

import spark.*;

public class Server {
    public static void main(String[] args) {
        new Server().start(8080);
    }
    
    private List<Elevator> elevators;
    
    public Server() {
        this(Collections.<Elevator>emptyList());
    }
    
    public Server(List<Elevator> elevators) {
        this.elevators = elevators;
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
                for (Elevator elevator : elevators)
                    elevator.call(atFloor, to);
                return "";
            }
        });
        get(new Route("/go") {
            @Override
            public Object handle(Request request, Response response) {
                int cabin = parseInt(request.queryParams("cabin"));
                int floorToGo = parseInt(request.queryParams("floorToGo"));
                elevators.get(cabin).go(floorToGo);
                return "";
            }
        });
        get(new Route("/userHasEntered") {
            @Override
            public Object handle(Request request, Response response) {
                int cabin = parseInt(request.queryParams("cabin"));
                elevators.get(cabin).userHasEntered();
                return "";
            }
        });
        get(new Route("/userHasExited") {
            @Override
            public Object handle(Request request, Response response) {
                int cabin = parseInt(request.queryParams("cabin"));
                elevators.get(cabin).userHasExited();
                return "";
            }
        });
        get(new Route("/reset") {
            @Override
            public Object handle(Request request, Response response) {
                int lowerFloor = parseInt(request.queryParams("lowerFloor"));
                int higherFloor = parseInt(request.queryParams("higherFloor"));
                int cabinSize = parseInt(request.queryParams("cabinSize"));
                int cabinCount = parseInt(request.queryParams("cabinCount"));
                String cause = request.queryParams("cause");
                System.out.println("reset! cause: " + cause);
                elevators = newArrayList();
                Direction direction = UP;
                CallManager callManager = new CallManager();
                for (int i = 0; i < cabinCount; i++) {
                    elevators.add(new Omnibus(callManager, lowerFloor, higherFloor, cabinSize, direction));
                    direction = direction.reverse();
                }
                return "";
            }
        });
        get(new Route("/nextCommands") {
            @Override
            public Object handle(Request request, Response response) {
                StringBuilder commands = new StringBuilder();
                for (Elevator elevator : elevators) {
                    commands.append(elevator.next().toString().toUpperCase());
                    commands.append("\n");
                }
                return commands.toString();
            }
        });
    }
    
    public void stop() {
        spark.Spark.stop();
    }
}
