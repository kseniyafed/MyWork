package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author intel
 */
class TeacherController extends AbstractTemplateController {

    public TeacherController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);

        UserDbGateway udbg;
        SessionDbGateway sdbg;
        SubjectDbGateway sjdbg;
        GroupDbGateway gdbg;
        try {
            udbg = new UserDbGateway();
            sdbg = new SessionDbGateway();
            sjdbg = new SubjectDbGateway();
            gdbg = new GroupDbGateway();
            

            int idSession = sdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sdbg.getUserIdBySessId(idSession));
            model.put("login", user.getLogin());

            ArrayList<Subject> subjects = new ArrayList();
            ArrayList<Group> groups= new ArrayList();

            subjects = sjdbg.findAll();
            groups=gdbg.findAll();

            model.put("subjects", subjects);
            model.put("groups", groups);

        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        respond(model, he);

    }

    @Override
    protected String getTemplateFilename() {
        return "MainPageTeacher.ftl";
    }
}
