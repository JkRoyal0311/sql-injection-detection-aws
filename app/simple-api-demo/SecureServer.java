import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class SecureServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        server.createContext("/login", (HttpExchange exchange) -> {
            URI uri = exchange.getRequestURI();
            String query = uri.getQuery();

            String response;

            if (query != null && query.contains("OR")) {
                response = "Attack detected and blocked!";
            } else {
                response = "Login failed";
            }

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Secure Server running on http://localhost:8081");
    }
}