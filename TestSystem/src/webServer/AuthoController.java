package webServer;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author intel
 */
class AuthoController extends AbstractTemplateController {

    public AuthoController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");
        HashMap<String, String> formValues = parseFromValues(requestBody);
        UserDbGateway udbg;
        String redirectTo="/";
        if (formValues.get("login") != null && formValues.get("password") != null) {

            try {
                udbg = new UserDbGateway();
                HashMap user = udbg.getByLoginAndPassword(formValues.get("login"), formValues.get("password"));
                if (udbg.getByLoginAndPassword(formValues.get("login"), formValues.get("password")) != null) {
                    System.out.println("OK");
                    if(udbg.getUserType(formValues.get("login"),formValues.get("password")).equals("учитель"))
                        redirectTo="/teacherPage";
                    if(udbg.getUserType(formValues.get("login"),formValues.get("password")).equals("ученик"))
                        redirectTo="/studentPage";
                } else {
                    System.out.println("Not OK");
                    redirectTo="/";
                }

            } catch (SQLException ex) {
                Logger.getLogger(AuthoController.class.getName()).log(Level.SEVERE, null, ex);

            }
            he.getResponseHeaders().add("Location", redirectTo);
            he.sendResponseHeaders(301, 0);
        }

    }

    @Override
    protected String getTemplateFilename() {
        return "Authorization.ftl";
    }

    private HashMap<String, String> parseFromValues(String formValuesEncoded) throws UnsupportedEncodingException {
        String formValuesDecoded = URLDecoder.decode(formValuesEncoded, "UTF-8");
        String[] formValues = formValuesDecoded.split("&");
        HashMap<String, String> result = new HashMap<>();
        for (String formValue : formValues) {
            String[] valueParts = formValue.split("=");
            if (valueParts.length == 1) {
                result.put(valueParts[0], null);
            } else {
                result.put(valueParts[0], valueParts[1]);
            }
        }
        return result;
    }
}