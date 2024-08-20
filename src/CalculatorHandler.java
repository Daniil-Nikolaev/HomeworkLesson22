import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CalculatorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HistoryHandler history=new HistoryHandler();

        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = getParams(query);



        Double num1 = Double.parseDouble(params.get("num1"));
        Double num2 = Double.parseDouble(params.get("num2"));

        String result=num1+" %s "+num2+" = %s";

        String response= switch (params.get("type")) {
            case "sum" -> result.formatted("+",num1 + num2);
            case "sub" -> result.formatted("-",num1 - num2);
            case "mul" -> result.formatted("*",num1 * num2);
            case "div" -> result.formatted("/",num1 / num2);
            default -> throw new IllegalStateException("Unexpected value: " + params.get("type"));
        };

        history.write(response);

        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();

    }

    private static Map<String,String> getParams(String query){
        String[] split = query.split("&");
        Map<String,String> params = new HashMap<>();

        for (String s : split) {
            String[] split1 = s.split("=");
            params.put(split1[0], split1[1]);
        }

        return params;
    }
}
