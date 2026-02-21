import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class VulnerableServer {

    static Map<String, String> users = new HashMap<>();

    public static void main(String[] args) throws Exception {
        users.put("admin", "admin123");
        users.put("user", "user123");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/login", (HttpExchange exchange) -> {
            URI uri = exchange.getRequestURI();
            String query = uri.getQuery();

            String response;

            if (query != null && query.contains("OR")) {
                response = "Login successful (SQL Injection simulated)";
            } else {
                response = "Login failed";
            }

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Vulnerable Server running on http://localhost:8080");
    }
}