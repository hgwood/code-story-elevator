package fr.hgwood.codestory.elevator;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import spark.*;

public class Server {
    public static void main(String[] args) {
        new Server().start(8080);
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
                return "";
            }
        });
        get(new Route("/go") {
            @Override
            public Object handle(Request request, Response response) {
                int floorToGo = parseInt(request.queryParams("floorToGo"));
                return "";
            }
        });
        get(new Route("/userHasEntered") {
            @Override
            public Object handle(Request request, Response response) {
                return "";
            }
        });
        get(new Route("/userHasExited") {
            @Override
            public Object handle(Request request, Response response) {
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
                return "";
            }
        });
        get(new Route("/nextCommand") {
            @Override
            public Object handle(Request request, Response response) {
                return "NOTHING";
            }
        });
    }
    
    public void stop() {
        spark.Spark.stop();
    }
}
