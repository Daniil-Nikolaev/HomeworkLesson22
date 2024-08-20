import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {

        HttpServer server=HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/calculator", new CalculatorHandler());
        server.createContext("/history",new HistoryHandler());

        server.start();
    }
}