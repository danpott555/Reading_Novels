package Model;

import java.util.Date;

/**
 *
 * @author Smily
 */
public class Chapter {
    private int id;
    private int novel_id;
    private int chapter_number;
    private String chapter_title;
    private int view;
    private String content;
    private Date date;

    public Chapter() {
    }

    public Chapter(int id, int novel_id, int chapter_number, String chapter_title, int view, String content, Date date) {
        this.id = id;
        this.novel_id = novel_id;
        this.chapter_number = chapter_number;
        this.chapter_title = chapter_title;
        this.view = view;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNovel_id() {
        return novel_id;
    }

    public void setNovel_id(int novel_id) {
        this.novel_id = novel_id;
    }

    public int getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(int chapter_number) {
        this.chapter_number = chapter_number;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
}
