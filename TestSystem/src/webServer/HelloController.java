package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;



/**
 *
 * @author intel
 */
class HelloController implements HttpHandler {

    public HelloController() {
      
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
      respond(he);
       
    }
    protected void respond(HttpExchange exchange) throws IOException{
        String hello="Hello world";
        byte[] bytes=hello.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);
        try(final OutputStream os=exchange.getResponseBody()){
            os.write(bytes);
        }
    }
    
}
