package ecobike.bean;

public class User {

    private int UID;
    private String name;
    private String phone;
    private String email;
    private String gender;

    public User() {

    }

    public User(int UID, String name, String phone, String email, String gender) {
        this.UID = UID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
