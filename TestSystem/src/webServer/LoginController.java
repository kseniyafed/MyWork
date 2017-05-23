package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

class LoginController extends AbstractTemplateController {

    public LoginController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();

        int error = extractError(he.getRequestURI());
        
        if (error == 0) {
            model.put("err", "Неверный логин или пароль!");
        } 
        respond(model, he);

    }

    protected String getTemplateFilename() {
        return "Authorization.ftl";
    }

    private int extractError(URI requestURI) {
        String addr = requestURI.getQuery();
        int err=-1;
        if (addr != null) {
                String strBetwEqu[] = addr.split("=");
                err = Integer.parseInt(strBetwEqu[1]);
            }
        return err;
    }
}
