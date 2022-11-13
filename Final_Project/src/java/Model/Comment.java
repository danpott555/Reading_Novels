package Model;

import java.util.Date;

/**
 *
 * @author Smily
 */
public class Comment {
    private int chapter_id;
    private String user;
    private String name;
    private int level;
    private int exp;
    private String image;
    private String comment;
    private Date date;

    public Comment() {
    }

    public Comment(int chapter_id, String user, String name, int level, int exp, String image, String comment, Date date) {
        this.chapter_id = chapter_id;
        this.user = user;
        this.name = name;
        this.level = level;
        this.exp = exp;
        this.image = image;
        this.comment = comment;
        this.date = date;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
