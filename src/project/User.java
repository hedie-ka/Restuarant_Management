package project;

public abstract class User {
    protected String name;
    protected String ID;
    protected String password;
    protected String phone;
    protected String email = null;

    public User(String name, String ID, String password, String phone) {
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.ID;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return this.phone;
    }

}
