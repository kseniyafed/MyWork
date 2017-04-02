package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kseniya
 */
public class SessionDbGateway extends DbGateway{
    public SessionDbGateway() throws SQLException {
    }
    void insert(int idUser) throws SQLException {
        
        Statement stmt = getConnection().createStatement();
        
        PreparedStatement prstmt = getConnection().prepareStatement("SELECT * FROM Session WHERE idUser = ?");
        prstmt.setInt(1, idUser);
        
        ResultSet result = prstmt.executeQuery();
        if (!result.isClosed()) {
            delete(idUser);
            System.out.println("удалена предыдущая сессия пользователя"+idUser);
        } 
        stmt.execute("INSERT INTO Session(idUser) VALUES (\""
                + idUser + "\")" );    
          
        stmt.close();
    }

    private void delete(int idUser) throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("DELETE FROM Session WHERE idUser = " + idUser);
        stmt.close();
    }
    public int getSessIdByUserId(int idUser) throws SQLException{
        int notFound=0;
        
        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Session WHERE idUser = ? ");
        stmt.setInt(1, idUser);
        
        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            return result.getInt("idSession");
            
        } else {
            stmt.close();
            return notFound;
        }
       
    }
    public int getUserIdBySessId(int idSession) throws SQLException{
        int notFound=0;
        
        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM Session WHERE idSession = ? ");
        stmt.setInt(1, idSession);
        
        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            return result.getInt("idUser");
            
        } else {
            stmt.close();
            return notFound;
        }
       
    }
}
    