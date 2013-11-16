package fr.hgwood.codestory.elevator;

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
                request.queryParams("atFloor");
                return "";
            }
        });
        get(new Route("/go") {
            @Override
            public Object handle(Request request, Response response) {
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
