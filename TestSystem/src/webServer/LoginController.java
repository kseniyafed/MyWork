package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

/**
 *
 * @author intel
 */
class LoginController extends AbstractTemplateController {

    public LoginController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();

        HashMap parameters = parseQuery(he.getRequestURI());
        if (parameters != null) {
            String errors[] = (String[]) parameters.get("err");

            for (int i = 0; i < errors.length; i++) {
                if (errors[i].equals("0")) {
                    model.put("err", "Неверный логин или пароль!");
                }
            }
        }
        respond(model, he);

    }

    protected String getTemplateFilename() {
        return "Authorization.ftl";
    }

    private HashMap parseQuery(URI requestURI) {
        HashMap result = new HashMap();
        String addr = requestURI.getQuery();

        String[] err;
        if (addr != null) {
            if (addr.contains("&")) {
                String strBetwAmp[] = addr.split("&");
                err = new String[strBetwAmp.length];
                int j = 0;
                for (int i = 0; i < strBetwAmp.length; i++) {
                    String strBetwEqu[] = strBetwAmp[i].split("=");
                    err[j] = strBetwAmp[1];
                    j++;
                }

            } else {

                String strBetwEqu[] = addr.split("=");
                err = new String[1];
                err[0] = strBetwEqu[1];
            }
            result.put("err", err);
        } else {
            result = null;
        }

        return result;

    }
}
