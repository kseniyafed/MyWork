package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public class ResultController extends AbstractTemplateController {

    public ResultController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap model = new HashMap();
        String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");

        HashMap<String, String> formValues = parseFromValues(requestBody);
        QuestionDbGateway qdbg;
        SessionDbGateway sdbg;
        UserDbGateway udbg;
        ResultDbGateway rdbg;
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);

        try {
            rdbg = new ResultDbGateway();
            udbg = new UserDbGateway();
            qdbg = new QuestionDbGateway();
            sdbg = new SessionDbGateway();
            int idSession = sdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sdbg.getUserIdBySessId(idSession));
            model.put("login", user.getLogin());
            ArrayList<Question> questions = qdbg.
                    findAllByIdSubject(sdbg.getSubjIdBySessId(idSession));
            boolean[] answers = new boolean[questions.size()];
            int countTrueAns = 0;
            for (Question question : questions) {
                String id = question.get("idQuestion").toString();

                if (formValues.get(id) != null) {
                    if (qdbg.checkAnswer(id, formValues.get(id)) == true) {
                        countTrueAns++;
                    }
                }
            }
            rdbg.insert(user.getId(), 
                    sdbg.getSubjIdBySessId(idSession), getMark(countTrueAns, questions.size()));

            model.put("mark", getMark(countTrueAns, questions.size()));
        } catch (SQLException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "ResultPage.ftl";
    }

    private int getMark(int trueAnsw, int allAnsw) {
        double ratio = (double) trueAnsw / (double) allAnsw * 100;
        int mark;
        if (ratio <= 50) {
            mark = 2;
        } else if (ratio <= 70) {
            mark = 3;
        } else if (ratio <= 80) {
            mark = 4;
        } else {
            mark = 5;
        }
        return mark;
    }

    private HashMap<String, String> parseFromValues(String formValuesEncoded) 
            throws UnsupportedEncodingException {
        String formValuesDecoded = URLDecoder.decode(formValuesEncoded, "UTF-8");
        System.out.println(formValuesDecoded);
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
