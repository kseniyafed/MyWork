
package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author intel
 */
public class UserDbGateway extends DbGateway {

    public UserDbGateway() throws SQLException {

    }
    public String getUserType(String login, String password) throws SQLException{
        String type="";
        if(getByLoginAndPassword(login,password)!=null){
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM User WHERE login = ? AND password = ? AND type = 'учитель'");
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            if (!result.isClosed()) {
                type="учитель";
            }else{
                /*stmt = getConnection().prepareStatement("SELECT * FROM User WHERE login = ? AND password = ? AND type = 'учитель'");
                stmt.setString(1, login);
                stmt.setString(2, password);
                result = stmt.executeQuery();
                if (!result.isClosed()) {*/
                type="ученик";
               // }
            }
        }
        return type;
    }
    public HashMap getByLoginAndPassword(String login, String password) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM User WHERE login = ? AND password = ?");
        stmt.setString(1, login);
        stmt.setString(2, password);

        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            HashMap user = new HashMap();
            user.put("login", result.getString("login"));
            user.put("password", result.getString("password"));
            
            return user;
        } else {
            stmt.close();
            return null;
        }

    }

}