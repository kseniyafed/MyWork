package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupDbGateway extends DbGateway {

    public GroupDbGateway() throws SQLException {
    }

    public ArrayList<Group> findAll() throws SQLException {

        Statement stmt = getConnection().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Groups");
        ArrayList<Group> groups = new ArrayList();

        while (result.next()) {
            if (createGroup(result) != null) {
                Group group = createGroup(result);
                groups.add(group);
            }
        }

        stmt.close();

        return groups;
    }

    private Group createGroup(ResultSet result) throws SQLException {
        Group group = new Group();
        if (!result.getString("name").equals("0")) {
            group.put("idGroup", result.getInt("idGroup"));
            group.put("name", result.getString("name"));
            return group;
        } else {
            return null;
        }
    }

    public String getNameById(int idGroup) throws SQLException {
        PreparedStatement stmt = 
                getConnection().prepareStatement("SELECT * FROM Groups WHERE idGroup = ?");
        stmt.setInt(1, idGroup);
        ResultSet result = stmt.executeQuery();

        if (!result.isClosed()) {
            Group group = createGroup(result);
            return (String) group.get("name");
        } else {
            stmt.close();
            return null;
        }
    }
}
