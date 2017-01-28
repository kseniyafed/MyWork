
package webServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author intel
 */
public class UserDbGateway extends DbGateway{
    public UserDbGateway()throws SQLException{
    
    }
   public  HashMap getByLoginAndPassword(String login, String password) throws SQLException{
        HashMap user=null;
        Statement stmt= getConnection().createStatement();
        if(stmt.executeQuery("SELECT * FROM User WHERE login="+login)==stmt.executeQuery("SELECT * FROM User WHERE password="+password))
           user.put("user", stmt.executeQuery("SELECT * FROM User WHERE login="+login));
        else {
            
        }
        
        return user;
   }
}
