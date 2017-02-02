package webServer;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author intel
 */
public abstract class DbGateway {

    DbConnection dbc;

    public DbGateway() throws SQLException {

        dbc = dbc.instance();
    }

    public Connection getConnection() {
        return dbc.getConnection();
    }
}