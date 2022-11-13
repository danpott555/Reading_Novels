package Model;

/**
 *
 * @author Smily
 */
public class GenreOfNovel {
    private int novel_id;
    private String genre_id;
    private String genre_name;

    public GenreOfNovel() {
    }

    public GenreOfNovel(int novel_id, String genre_id, String genre_name) {
        this.novel_id = novel_id;
        this.genre_id = genre_id;
        this.genre_name = genre_name;
    }

    public int getNovel_id() {
        return novel_id;
    }

    public void setNovel_id(int novel_id) {
        this.novel_id = novel_id;
    }

    public String getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(String genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
    
    
}
