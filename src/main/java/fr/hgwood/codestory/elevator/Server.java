package fr.hgwood.codestory.elevator;

import static spark.Spark.*;
import spark.*;

public class Server {
    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        setPort(8080);
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
