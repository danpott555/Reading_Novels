package Model;

import java.util.Date;

/**
 *
 * @author Smily
 */
public class Novel {
    private int id;
    private String name;
    private String image;
    private String author;
    private String infor;
    private Date date;
    private int follow;

    public Novel() {
    }

    public Novel(int id, String name, String image, String author, String infor, Date date, int follow) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.author = author;
        this.infor = infor;
        this.date = date;
        this.follow = follow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }
    
    
    
}
