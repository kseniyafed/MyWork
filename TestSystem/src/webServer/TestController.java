
package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Kseniya
 */
public class TestController extends AbstractTemplateController{
    public TestController() throws IOException {
        
    }
    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model= new HashMap();
        respond(model,he);
    }

    @Override
    protected String getTemplateFilename() {
        return "TestPage.ftl";
    }
    
}
