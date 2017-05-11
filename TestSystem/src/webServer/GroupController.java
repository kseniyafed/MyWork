package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupController extends AbstractTemplateController {

    public GroupController() throws IOException {

    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();
        int groupId = Integer.parseInt(extractGroupFromURI(he.getRequestURI()));
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);
        SessionDbGateway sessdbg;
        UserDbGateway udbg;
        GroupDbGateway gdbg;
        SubjectDbGateway sdbg;
        ResultDbGateway rdbg;
        try {
            sessdbg = new SessionDbGateway();
            udbg = new UserDbGateway();
            gdbg = new GroupDbGateway();
            sdbg = new SubjectDbGateway();
            rdbg = new ResultDbGateway();
            int idSession = sessdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sessdbg.getUserIdBySessId(idSession));
            ArrayList<Subject> subjects = new ArrayList();

            subjects = sdbg.findAll();
            ArrayList<User> students = udbg.getAllFromGroup(groupId);
            ArrayList<HashMap> results = new ArrayList();
            for (User student : students) {
                HashMap result = new HashMap();
                ArrayList<Integer> marks = new ArrayList();

                for (Subject subject : subjects) {
                    int mark = rdbg.getMark(student.getId(), (int) subject.get("idSubject"));
                    marks.add(mark);
                    
                }
                result.put("name", student.getName());
                result.put("marks", marks);
                results.add(result);

            }
            model.put("results", results);
            model.put("subjects", subjects);
            model.put("login", user.getLogin());
            model.put("group", gdbg.getNameById(groupId));

        } catch (SQLException ex) {
            Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(groupId);
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "GroupPage.ftl";
    }

    private String extractGroupFromURI(URI uri) {
        String[] uriParts = uri.getPath().split("/");
        try {

            return uriParts[uriParts.length - 1];
        } catch (NumberFormatException e) {
            return "";
        }
    }
}
