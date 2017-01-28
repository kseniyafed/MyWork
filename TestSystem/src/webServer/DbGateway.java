package webServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author intel
 */
public abstract class DbGateway {
    //Connection connection;
    
    DbConnection dbc;
   
    public DbGateway()throws SQLException{
        //connection= DriverManager.getConnection("jdbc:sqlite:DbTestSystem.db");
        dbc=dbc.instance();
    }
    //abstract void create();
    public Connection getConnection(){
        return dbc.getConnection();
    }
}