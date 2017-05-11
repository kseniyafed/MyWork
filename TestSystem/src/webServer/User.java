package webServer;

import java.util.HashMap;

public class User extends HashMap<String, Object>{
    private int id;
    private String login;
    private String type;
    private String name;
    private int group;

    public User(String login, String type, String name, int group, int id) {
        this.login = login;
        this.group = group;
        this.type = type;
        this.name = name;
        this.id=id;
    }

    User() {
       
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

    public int getGroup() {

        return group;
    }
    public int getId() {

        return id;
    }
}
