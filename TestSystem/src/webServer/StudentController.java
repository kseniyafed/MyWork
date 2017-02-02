package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author intel
 */
class StudentController extends AbstractTemplateController {

    public StudentController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();
        respond(model, he);

    }

    @Override
    protected String getTemplateFilename() {
        return "MainPageStudent.ftl";
    }
}