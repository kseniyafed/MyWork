/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author intel
 */
public class DbConnection {
    private final static DbConnection inst = new DbConnection();
    Connection connection;
    public DbConnection(){
        try {
            connection= DriverManager.getConnection("jdbc:sqlite:DbTestSystem.db");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
             //System.out.println("Упс");
        }
    } 
 
    public static DbConnection instance() {
    return inst;
    }
    public Connection getConnection(){
        return connection;
    }

}

