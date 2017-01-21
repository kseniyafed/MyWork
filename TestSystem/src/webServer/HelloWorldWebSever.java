
package webServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
public class HelloWorldWebSever{
 public static void main(String[] args) throws IOException{
   HttpServer server=HttpServer.create(new InetSocketAddress(8030),0);
   server.createContext("/");
    server.start();
    }
}
    
   