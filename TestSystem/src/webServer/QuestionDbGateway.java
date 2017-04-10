
package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kseniya
 */
public class QuestionDbGateway extends DbGateway{
    public QuestionDbGateway() throws SQLException {
    }
    
    public ArrayList<Question> findAllByIdSubject(int idSubject) throws SQLException {
        ArrayList<Question> questions=new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Question WHERE idSubject = ? ORDER BY number");
        stmt.setInt(1, idSubject);
        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            while(result.next()) {
                Question question = createQuestion(result);
                questions.add(question);
            }
            return questions;
        } else {
            stmt.close();
            return null;
        }
    
    } 

    private Question createQuestion(ResultSet result) throws SQLException {
       Question question = new Question();
      
        question.put("idQuestion", result.getInt("idQuestion"));
        question.put("idSubject", result.getInt("idSubject"));
        question.put("number", result.getInt("number"));
        question.put("formulation", result.getString("formulation"));
        question.put("answer", result.getString("answer"));
        return question;
    }
}
