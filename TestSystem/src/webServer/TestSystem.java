package webServer;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class TestSystem {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new LoginController());
        server.createContext("/authorize", new AuthoController());
        server.createContext("/teacherPage", new TeacherController());
        server.createContext("/studentPage", new StudentController());
        server.createContext("/teoryPage", new TeoryController());
        server.createContext("/testPage", new TestController());
        server.createContext("/resultPage", new ResultController());
        server.start();
    }
}