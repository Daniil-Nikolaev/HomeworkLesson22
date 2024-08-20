import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class HistoryHandler implements HttpHandler {
    File history=new File("history.txt");

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response=String.valueOf(get());
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();

    }

    public void write(String s) throws IOException {
        OutputStream out = new FileOutputStream(history,true);
        out.write(s.getBytes());
        out.write("\n".getBytes());
    }

    public StringBuilder get() throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        String string;
        BufferedReader br=new BufferedReader(new FileReader(history));

        while ((string= br.readLine())!=null){
            stringBuilder.append(string);
            stringBuilder.append("\n");
        }
        return stringBuilder;


    }


}
