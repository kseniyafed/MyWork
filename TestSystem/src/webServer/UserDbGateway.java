package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDbGateway extends DbGateway {

    public UserDbGateway() throws SQLException {
    }

    public User getById(int idUser) throws SQLException {
        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM User WHERE idUser = ?");
        stmt.setInt(1, idUser);
        ResultSet result = stmt.executeQuery();
        if (!result.isClosed()) {
            User user = new User(result.getString("login"), 
                    result.getString("type"), 
                    result.getString("fullName"),
                    result.getInt("idGroup"), 
                    result.getInt("idUser"));
            return user;
        } else {
            stmt.close();
            return null;
        }
    }

    public User getByLoginAndPassword(String login, String password) throws SQLException {

        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM User WHERE login = ? AND password = ?");
        stmt.setString(1, login);
        stmt.setString(2, password);
        ResultSet result = stmt.executeQuery();

        if (!result.isClosed()) {
            User user = new User(result.getString("login"),
                    result.getString("type"),
                    result.getString("fullName"),
                    result.getInt("idGroup"),
                    result.getInt("idUser"));
            return user;
        } else {
            stmt.close();
            return null;
        }

    }

    public ArrayList<User> getAllFromGroup(int idGroup) throws SQLException {
        ArrayList<User> students = new ArrayList();
        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM User WHERE idGroup = ? ");
        stmt.setInt(1, idGroup);
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            User student = createUser(result);
            students.add(student);
        }

        return students;
    }

    private User createUser(ResultSet result) throws SQLException {
        User user = new User(result.getString("login"), 
                result.getString("type"),
                result.getString("fullName"),
                result.getInt("idGroup"),
                result.getInt("idUser"));

        user.put("idUser", result.getInt("idUser"));
        user.put("fullName", result.getString("fullName"));
        user.put("idGroup", result.getInt("idGroup"));
        user.put("type", result.getString("type"));
        return user;
    }
}
