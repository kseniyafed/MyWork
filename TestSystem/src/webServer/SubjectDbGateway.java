
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
public class SubjectDbGateway extends DbGateway{
    public SubjectDbGateway() throws SQLException {
    }
    private Subject createSubject(ResultSet result) throws SQLException {
        Subject subject = new Subject();
      
        subject.put("idSubject", result.getInt("idSubject"));
        subject.put("number", result.getInt("number"));
        subject.put("name", result.getString("name"));
        subject.put("teory", result.getString("teory"));
        return subject;
    }
    ArrayList<Subject> findAll() throws SQLException {
        
        Statement stmt = getConnection().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Subject");
        ArrayList<Subject> subjects = new ArrayList();
        
        while(result.next()) {
            Subject subject = createSubject(result);
            subjects.add(subject);
        }
        
        stmt.close();
        
        return subjects;
    }
    Subject findByName(String name) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Subject WHERE name = ?");
        stmt.setString(1, name);
        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            Subject subject = createSubject(result);
            return subject;
        } else {
            stmt.close();
            return null;
        }
       
        
    }
}
