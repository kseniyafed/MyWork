package webServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author intel
 */
public class DbGateway {
    private Connection connection;

    public DbGateway() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:data.db");
    }

}
