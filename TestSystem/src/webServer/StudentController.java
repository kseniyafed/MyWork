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
        
        String cookieStr=he.getRequestHeaders().get("Cookie").get(0);
        int idSession=getSessionIdFromCookie(cookieStr);
        UserDbGateway udbg;
        SessionDbGateway sdbg;
        try {
            udbg = new UserDbGateway();
            sdbg=new SessionDbGateway();
            
            User user = udbg.getById(sdbg.getUserIdBySessId(idSession));
            model.put("name", user.getName());
           // System.out.println("user="+user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        respond(model, he);

    }

    @Override
    protected String getTemplateFilename() {
        return "MainPageStudent.ftl";
    }

    private int getSessionIdFromCookie(String cookieStr) {
        int idSession=0;
        String cookies[]=cookieStr.split(";");
        for(int i=0;i<cookies.length;i++){
           if(cookies[i].contains("session")){
              String nameAndValue[]=cookies[i].split("=");
              idSession=Integer.parseInt(nameAndValue[1]);
           }
        }
        return idSession;
    }
}