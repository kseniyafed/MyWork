package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDbGateway extends DbGateway {

    public SessionDbGateway() throws SQLException {
    }

    void insert(int idUser) throws SQLException {
        Statement stmt = getConnection().createStatement();

        PreparedStatement prstmt = getConnection().
                prepareStatement("SELECT * FROM Session WHERE idUser = ?");
        prstmt.setInt(1, idUser);

        ResultSet result = prstmt.executeQuery();
        if (!result.isClosed()) {
            delete(idUser);

        }
        stmt.execute("INSERT INTO Session(idUser) VALUES (\""
                + idUser + "\")");

        stmt.close();
    }

    private void delete(int idUser) throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("DELETE FROM Session WHERE idUser = " + idUser);
        stmt.close();
    }

    public int getSessIdByUserId(int idUser) throws SQLException {
        int notFound = 0;

        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Session WHERE idUser = ? ");
        stmt.setInt(1, idUser);

        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            return result.getInt("idSession");

        } else {
            stmt.close();
            return notFound;
        }

    }

    public int getUserIdBySessId(int idSession) throws SQLException {
        int notFound = 0;

        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Session WHERE idSession = ? ");
        stmt.setInt(1, idSession);

        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            return result.getInt("idUser");

        } else {
            stmt.close();
            return notFound;
        }
    }

    public int getSessionIdFromCookie(String cookieStr) {
        int idSession = 0;
        String cookies[] = cookieStr.split(";");
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].contains("session")) {
                String nameAndValue[] = cookies[i].split("=");
                idSession = Integer.parseInt(nameAndValue[1]);
            }
        }
        return idSession;
    }

    void update(int idSession, int idSubject) throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("UPDATE Session SET idSubject = \"" 
                + idSubject + "\" WHERE idSession = " + idSession);
        stmt.close();
    }

    public int getSubjIdBySessId(int idSession) throws SQLException {
        int notFound = 0;

        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Session WHERE idSession = ? ");
        stmt.setInt(1, idSession);

        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            return result.getInt("idSubject");

        } else {
            stmt.close();
            return notFound;
        }
    }
}
