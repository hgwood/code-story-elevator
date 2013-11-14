package fr.hgwood.codestory.elevator;

import spark.*;

import static spark.Spark.get;

public class Server {
    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        get(new Route("/:path") {
            @Override
            public Object handle(Request request, Response response) {
                String path = request.params(":path");
                if (path.equals("nextCommand")) {
                    return "NOTHING";
                } else {
                    response.status(404);
                    return "Page not found";
                }
            }
        });
    }
}
