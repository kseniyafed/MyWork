package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

class AuthoController extends AbstractTemplateController {

    public AuthoController() throws IOException {

    }

    @Override
    public void handle(HttpExchange he) throws IOException {

        String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");
        HashMap<String, String> formValues = parseHtmlQuery(requestBody);
        HashMap<String, Object> model = new HashMap<>();
        String redirectTo = "/";

        if (formValues.get("login") != null && formValues.get("password") != null) {

            try {
                UserDbGateway udbg = new UserDbGateway();
                User user = udbg.getByLoginAndPassword(formValues.get("login"), 
                            formValues.get("password"));

                if (user != null) {
                    model.put("user", user);
                    SessionDbGateway sdbg = new SessionDbGateway();
                    sdbg.insert(user.getId());
                    if (sdbg.getSessIdByUserId(user.getId()) != 0) {
                        he.getResponseHeaders().add("Set-Cookie",
                                        "session=" + sdbg.getSessIdByUserId(user.getId()));
                    }
                    if (user.isTeacher()) {
                        redirectTo = "/teacherPage";
                    } else {
                        redirectTo = "/studentPage";
                    }
                } else {

                    redirectTo = "/?err=0";

                }

            } catch (SQLException ex) {
                Logger.getLogger(AuthoController.class.getName()).log(Level.SEVERE, null, ex);

            }
            he.getResponseHeaders().add("Location", redirectTo);
            he.sendResponseHeaders(301, 0);
            respond(model, he);
        }
    }

    @Override
    protected String getTemplateFilename() {
        return "Authorization.ftl";
    }


}
