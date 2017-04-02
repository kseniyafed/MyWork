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
    int err=1;
    public AuthoController() throws IOException {
        
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");
        HashMap<String, String> formValues = parseFromValues(requestBody);
        UserDbGateway udbg;
        SessionDbGateway sdbg ;
        HashMap model= new HashMap();
        String redirectTo = "/";
        int userId;
        if (formValues.get("login") != null && formValues.get("password") != null) {

            try {
                udbg = new UserDbGateway();
                User user = udbg.getByLoginAndPassword(formValues.get("login"), formValues.get("password"));

                if (user != null) {
                    model.put("user",user);
                    userId=user.getId();
                    sdbg=new SessionDbGateway();
                    sdbg.insert(userId);
                    if(sdbg.getSessIdByUserId(userId)!=0){
                        he.getResponseHeaders().add("Set-Cookie","session="+sdbg.getSessIdByUserId(userId));
                    }
                    if (user.isTeacher()) {
                        redirectTo = "/teacherPage";
                    } else {
                        redirectTo = "/studentPage";
                    }
                } else {
                    System.out.println("Not OK");
                    redirectTo = "/?err=0";
                    //err=0;
                    //model.put("error",err);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AuthoController.class.getName()).log(Level.SEVERE, null, ex);

            }
            he.getResponseHeaders().add("Location", redirectTo);
            
            
            he.sendResponseHeaders(301, 0);
            respond(model,he);
            
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