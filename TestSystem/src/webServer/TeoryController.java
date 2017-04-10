
package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kseniya
 */
public class TeoryController extends AbstractTemplateController{
    public TeoryController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();
        String subjectName = extractIdFromURI(he.getRequestURI());
        String cookieStr=he.getRequestHeaders().get("Cookie").get(0);
        SubjectDbGateway sjdbg;
        Subject subject=new Subject();
        SessionDbGateway sessdbg;
       
            try { 
                if (subjectName!="") {
                    sjdbg=new SubjectDbGateway();
                    sessdbg=new SessionDbGateway();
                    subject=sjdbg.findByName(subjectName);
                    System.out.println(sessdbg.getSessionIdFromCookie(cookieStr));
                    sessdbg.update(sessdbg.getSessionIdFromCookie(cookieStr), (int) subject.get("idSubject"));
    
                }
            } catch (SQLException ex) {
                Logger.getLogger(TeoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        model.put("subject", subject);
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "TeoryPage.ftl";
    }

    private String extractIdFromURI(URI uri) {
        String[] uriParts = uri.getPath().split("/");
        try {
            System.out.println(uriParts[uriParts.length - 1]);
            return uriParts[uriParts.length - 1];
        } catch (NumberFormatException e) {
            return "";
        }
    }
}
