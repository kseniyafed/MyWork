package webServer;

/**
 *
 * @author intel
 */
public class User {
    private String id;
    private String login;
    private String type;
    private String name;
    private String group;

    public User(String login, String type, String name, String group, String id) {
        this.login = login;
        this.group = group;
        this.type = type;
        this.name = name;
        this.id=id;

    }

    public boolean isTeacher() {
        boolean isTeacher = false;

        if (type.equals("учитель")) {
            isTeacher = true;
        }
        return isTeacher;
    }

    public String getLogin() {

        return login;
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public String getGroup() {

        return group;
    }
    public String getId() {

        return id;
    }
}
