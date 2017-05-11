package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultDbGateway extends DbGateway {

    public ResultDbGateway() throws SQLException {
    }

    public void insert(int idUser, int idSubject, int mark) throws SQLException {
        Statement stmt = getConnection().createStatement();

        stmt.execute("INSERT INTO Result(idUser, idSubject, mark) VALUES (\""
                + idUser + "\", \""
                + idSubject + "\", \""
                + mark + "\")"
        );
        stmt.close();
    }

    public boolean isPassed(int idUser, int idSubject) throws SQLException {
        PreparedStatement prstmt = getConnection().
                prepareStatement("SELECT * FROM Result WHERE idUser = ? and idSubject = ?");
        prstmt.setInt(1, idUser);
        prstmt.setInt(2, idSubject);

        ResultSet result = prstmt.executeQuery();
        if (!result.isClosed()) {
            return true;
        } else {
            return false;
        }
    }

    public int getMark(int idUser, int idSubject) throws SQLException {
        PreparedStatement prstmt = getConnection().
                prepareStatement("SELECT mark FROM Result WHERE idUser = ? and idSubject = ?");
        prstmt.setInt(1, idUser);
        prstmt.setInt(2, idSubject);

        ResultSet result = prstmt.executeQuery();
        if (!result.isClosed()) {
            return result.getInt("mark");
        } else {
            return 0;
        }
    }
}
