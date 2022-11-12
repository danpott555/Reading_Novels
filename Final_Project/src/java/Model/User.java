package Model;

/**
 *
 * @author Smily
 */
public class User {
    private String user;
    private String password;
    private String name;
    private String password_2;
    private int level;
    private int exp;
    private int ques_id;
    private String ans;
    private String email;
    private String image;

    public User() {
    }

    public User(String user, String password, String name, String password_2, int level, int exp, int ques_id, String ans, String email, String image) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.password_2 = password_2;
        this.level = level;
        this.exp = exp;
        this.ques_id = ques_id;
        this.ans = ans;
        this.email = email;
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword_2() {
        return password_2;
    }

    public void setPassword_2(String password_2) {
        this.password_2 = password_2;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getQues_id() {
        return ques_id;
    }

    public void setQues_id(int ques_id) {
        this.ques_id = ques_id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
}
