package webServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author intel
 */
public abstract class DbGateway {
    Connection connection;
    public DbGateway()throws SQLException{
        connection= DriverManager.getConnection("jdbc:sqlite:data.db");
    }
    abstract void create();
}
