import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class SecureServer {

    static Pattern sqlPattern = Pattern.compile("('|--|;|\\bOR\\b|\\bAND\\b)", Pattern.CASE_INSENSITIVE);
    static String logFile = "logs/security-log.txt";

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        server.createContext("/login", (HttpExchange exchange) -> {
            URI uri = exchange.getRequestURI();
            String query = uri.getQuery();

            String response;

            if (query != null && sqlPattern.matcher(query).find()) {

                String logEntry = "SQL Injection detected | Time: "
                        + LocalDateTime.now()
                        + " | Query: " + query + "\n";

                writeLog(logEntry);

                response = "Malicious request blocked!";
            } else {
                response = "Login failed (Invalid credentials)";
            }

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Secure Server running on http://localhost:8081");
    }

    static void writeLog(String logEntry) {
        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}