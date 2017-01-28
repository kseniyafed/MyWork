/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webServer;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author intel
 */
public class DbConnection {
    private DbConnection inst = new DbConnection();
    Connection connection= DriverManager.getConnection("jdbc:sqlite:DbTestSystem.db");
    public static DbConnection instance() {
    return inst;
}

}

