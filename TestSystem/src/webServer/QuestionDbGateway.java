
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
    public QuestionDbGateway() throws SQLException {}
        public ArrayList<Question> findAllByIdSubject(int idSubject) throws SQLException {
        
        Statement stmt = getConnection().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Subject");
        ArrayList<Question> questions = new ArrayList();
        
        while(result.next()) {
            Question question = createQuestion(result);
            questions.add(question);
        }
        
        stmt.close();
        
        return questions;
    
    } 

    private Question createQuestion(ResultSet result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
