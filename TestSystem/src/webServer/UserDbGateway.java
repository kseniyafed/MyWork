
package webServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author intel
 */
public class UserDbGateway {
    private Connection connection;
    public UserDbGateway()throws SQLException{
        connection= DriverManager.getConnection("jdbc:sqlite:data.db");
    }
    void createTable() throws SQLException{
        Statement stmt=connection.createStatement();
        stmt.execute("CREATE TABLE user ("
                +"idUser INT PRIMARY KEY NOT NULL,"
                +"fullName TEXT NOT NULL,"
                +"password TEXT NOT NULL,"
                +"idGroup INT NOT NULL,"
                +"login TEXT NOT NULL,"
                +"type TEXT NOT NULL");
    }
}
