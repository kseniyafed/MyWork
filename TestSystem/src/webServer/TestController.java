package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestController extends AbstractTemplateController {

    public TestController() throws IOException {

    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();
        QuestionDbGateway qdbg;
        SessionDbGateway sdbg;
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);
        try {
            qdbg = new QuestionDbGateway();
            sdbg = new SessionDbGateway();
            int idSession = sdbg.getSessionIdFromCookie(cookieStr);
            ArrayList<Question> questions = qdbg.
                    findAllByIdSubject(sdbg.getSubjIdBySessId(idSession));
            model.put("questions", questions);
        } catch (SQLException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "TestPage.ftl";
    }

}
