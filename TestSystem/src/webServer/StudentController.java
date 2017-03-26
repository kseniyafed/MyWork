package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String cookieStr=he.getRequestHeaders().get("Cookie").get(0);
        int idUser=Integer.parseInt(getUser(cookieStr));
        UserDbGateway udbg;
        try {
            udbg = new UserDbGateway();
            User user = udbg.getById(idUser);
            System.out.println(user.getName());
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @Override
    protected String getTemplateFilename() {
        return "MainPageStudent.ftl";
    }

    private String getUser(String cookieStr) {
        String id="";
        String cookies[]=cookieStr.split(";");
        for(int i=0;i<cookies.length;i++){
           if(cookies[i].contains("enteredUser")){
              String nameAndValue[]=cookies[i].split("=");
              id=nameAndValue[1];
           }
        }
        return id;
    }
}