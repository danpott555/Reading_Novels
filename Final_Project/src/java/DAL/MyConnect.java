package DAL;

import Model.*;
import java.util.*;
import java.sql.*;
import java.util.Date;
/**
 *
 * @author Smily
 */
public class MyConnect {
    Connection con;
    private ArrayList<Novel> novels = new ArrayList<>();
    private ArrayList<Genre> genres = new ArrayList<>();
    private HashMap<Integer, QuestionList> quesMap = new HashMap<>();
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<GenreOfNovel> novelGenres = new ArrayList<>();
    private Novel n = new Novel();
    private ArrayList<Chapter> chapterList = new ArrayList<>();
    private Chapter c = new Chapter();
    private Chapter lc = new Chapter();
    private ArrayList<Chapter> lastestChapterList = new ArrayList<>();
    private ArrayList<Novel> novelSortByFollow = new ArrayList<>();
    private ArrayList<Novel> followList = new ArrayList<>();
    private boolean isFollow = false;
    private ArrayList<Comment> commentList = new ArrayList<>();
    private ArrayList<Novel> readList = new ArrayList<>();
    private boolean isRead = false;
    private ArrayList<Chapter> chapterReadOfNovel = new ArrayList<>();

    public MyConnect() {
        try {
            con = new DBcontext().getConnection();
        } catch (Exception e) {
        }
    }

    public ArrayList<Chapter> getChapterReadOfNovel() {
        return chapterReadOfNovel;
    }

    public void setChapterReadOfNovel(ArrayList<Chapter> chapterReadOfNovel) {
        this.chapterReadOfNovel = chapterReadOfNovel;
    }
    
    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public ArrayList<Novel> getReadList() {
        return readList;
    }

    public void setReadList(ArrayList<Novel> readList) {
        this.readList = readList;
    }
    
    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    
    public boolean isIsFollow() {
        return isFollow;
    }

    public void setIsFollow(boolean isFollow) {
        this.isFollow = isFollow;
    }
    
    public ArrayList<Novel> getFollowList() {
        return followList;
    }

    public void setFollowList(ArrayList<Novel> followList) {
        this.followList = followList;
    }
    
    public ArrayList<Novel> getNovelSortByFollow() {
        return novelSortByFollow;
    }

    public void setNovelSortByFollow(ArrayList<Novel> novelSortByFollow) {
        this.novelSortByFollow = novelSortByFollow;
    }
    
    

    public ArrayList<Chapter> getLastestChapterList() {
        return lastestChapterList;
    }

    public void setLastestChapterList(ArrayList<Chapter> lastestChapterList) {
        this.lastestChapterList = lastestChapterList;
    }

    public Chapter getLc() {
        return lc;
    }

    public void setLc(Chapter lc) {
        this.lc = lc;
    }
    

    public Novel getN() {
        return n;
    }

    public void setN(Novel n) {
        this.n = n;
    }

    public Chapter getC() {
        return c;
    }

    public void setC(Chapter c) {
        this.c = c;
    }
    
    

    public ArrayList<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(ArrayList<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
    
    

    public ArrayList<Novel> getNovels() {
        return novels;
    }

    public void setNovels(ArrayList<Novel> novels) {
        this.novels = novels;
    }
    
    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public ArrayList<GenreOfNovel> getNovelGenres() {
        return novelGenres;
    }

    public void setNovelGenres(ArrayList<GenreOfNovel> novelGenres) {
        this.novelGenres = novelGenres;
    }
    

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public HashMap<Integer, QuestionList> getQuesMap() {
        return quesMap;
    }

    public void setQuesMap(HashMap<Integer, QuestionList> quesMap) {
        this.quesMap = quesMap;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
        
    public String status = "ok";
    
    public void loadNovel(){
        novels = new ArrayList<>();
        String sql = "SELECT * FROM Novel_HE160853 ORDER BY date DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String author = rs.getString(4);
                String infor = rs.getString(5);
                Date date = rs.getDate(6);
                int follow = rs.getInt(7);
                novels.add(new Novel(id, name, image, author, infor, date, follow));
            }
        } catch (Exception e) {
        }
    }
    
    public void loadGenre(){
        genres = new ArrayList<>();
        String sql = "SELECT * FROM Genre_HE160853";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                genres.add(new Genre(id, name));
            }
        } catch (Exception e) {
            status = "Error at load Genre!" + e.getMessage();
        }
    }
        
    public void loadQuestion(){
        quesMap = new HashMap<>();
        String sql = "SELECT * FROM QuestionList_HE160853";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                quesMap.put(id, new QuestionList(id, name));
            }
        } catch (Exception e) {
            status = "Error at load Question!" + e.getMessage();
        }
    }
    
    public void loadUser(){
        userList = new ArrayList<>();
        String sql = "SELECT * FROM User_HE160853";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                String user = rs.getString(1);
                String password = rs.getString(2);
                String name = rs.getString(3);
                String password_2 = rs.getString(4);
                int level = rs.getInt(5);
                int exp = rs.getInt(6);
                int ques_id = rs.getInt(7);
                String ans = rs.getString(8);
                String email = rs.getString(9);
                String image = rs.getString(10);
                userList.add(new User(user, password, name, password_2, level, exp, ques_id, ans, email, image));
            }
        } catch (Exception e) {
            status = "Error at load User!" + e.getMessage();
        }
    }
    
    public void insertUser(String user, String password, String name, String password_2, int ques_id, String ans, String email){
        String sql = "INSERT INTO User_HE160853 values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, password_2);
            ps.setInt(5, 1);
            ps.setInt(6, 0);
            ps.setInt(7, ques_id);
            ps.setString(8, ans);
            ps.setString(9, email);
            ps.setString(10, "/img/user/default.jpg");
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void loadNovelByGenre(String genreId){
        novels = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM Novel_HE160853 n join ListGenreOfNovel_HE160853 l on n.id = l.novel_id join Genre_HE160853 g on l.genre_id = g.id\n"
                +"WHERE g.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, genreId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String author = rs.getString(4);
                String infor = rs.getString(5);
                Date date = rs.getDate(6);
                int follow = rs.getInt(7);
                novels.add(new Novel(id, name, image, author, infor, date, follow));
            }
        } catch (Exception e) {
        }
    }
    
    public void loadGenreOfNovel(int n_id){
        novelGenres = new ArrayList<>();
        String sql = "SELECT *\n" +
               "FROM ListGenreOfNovel_HE160853 l join Genre_HE160853 g on l.genre_id = g.id\n" +
                "WHERE novel_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, n_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int novel_id = rs.getInt(1);
                String genre_id = rs.getString(2);
                String genre_name = rs.getString(4);
                novelGenres.add(new GenreOfNovel(novel_id, genre_id, genre_name));
            }
        } catch (Exception e) {
        }
    }
    
    public void loadGenreOfNovel1(int n_id){
        novelGenres = new ArrayList<>();
        String sql = "SELECT *\n" +
               "FROM ListGenreOfNovel_HE160853 l join Genre_HE160853 g on l.genre_id = g.id\n" +
                "WHERE novel_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, n_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int novel_id = rs.getInt(1);
                String genre_id = rs.getString(2);
                String genre_name = rs.getString(4);
                novelGenres.add(new GenreOfNovel(novel_id, genre_id, genre_name));
            }
        } catch (Exception e) {
        }
    }
    
    public void searchNovel(String searchNovel){
        novels = new ArrayList<>();
        String sql = "SELECT * FROM Novel_HE160853\n" +
                    "WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setNString(1, "%"+searchNovel+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String author = rs.getString(4);
                String infor = rs.getString(5);
                Date date = rs.getDate(6);
                int follow = rs.getInt(7);
                novels.add(new Novel(id, name, image, author, infor, date, follow));
            }
        } catch (Exception e) {
            
        }
        
    }

    public Novel loadNovelDetail(int novel_id){
        String sql = "SELECT * FROM Novel_HE160853\n" +
                    "WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, novel_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String author = rs.getString(4);
                String infor = rs.getString(5);
                Date date = rs.getDate(6);
                int follow = rs.getInt(7);
                n = new Novel(id, name, image, author, infor, date, follow);
                return n;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void loadChapterList(int novel_id){
        chapterList = new ArrayList<>();
        String sql = "SELECT * FROM Chapter_HE160853 WHERE novel_id = ? ORDER BY chapter_number DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, novel_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                int chapter_number = rs.getInt(3);
                String chapter_title = rs.getString(4);
                int view = rs.getInt(5);
                String content = rs.getString(6);
                Date date = rs.getDate(7);
                chapterList.add(new Chapter(id, novel_id, chapter_number, chapter_title, view, content, date));
            }
        } catch (Exception e) {
        }
    }
    
    public Chapter loadChapter(int novel_id, int chapter_number){
        String sql = "SELECT * FROM Chapter_HE160853 WHERE novel_id = ? AND chapter_number = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, novel_id);
            ps.setInt(2, chapter_number);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String chapter_title = rs.getString(4);
                int view = rs.getInt(5);
                String content = rs.getString(6);
                Date date = rs.getDate(7);
                c = new Chapter(id, novel_id, chapter_number, chapter_title, view, content, date);
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean checkChapterExist(Chapter chapter){
        for (Chapter ch : lastestChapterList) {
            if (ch.getNovel_id() == chapter.getNovel_id()) {
                return true;
            }
        }
        return false;
    }
    
    public void loadLastestChapter(int novel_id){
//        lastestChapterList = new ArrayList<>();
        String sql = "SELECT TOP 1 * FROM Chapter_HE160853 WHERE novel_id = ? ORDER BY chapter_number DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, novel_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                int id = rs.getInt(1);
                int chapter_number = rs.getInt(3);
                String chapter_title = rs.getString(4);
                int view = rs.getInt(5);
                String content = rs.getString(6);
                Date date = rs.getDate(7);
                Chapter c = new Chapter(id, novel_id, chapter_number, chapter_title, view, content, date);
                if (!checkChapterExist(c)) {
                    lastestChapterList.add(c);
                }
                
            }
        } catch (Exception e) {
        }
    }
    
    public Chapter loadLastestChapter1(int novel_id){
        String sql = "SELECT TOP 1 * FROM Chapter_HE160853 WHERE novel_id = ? ORDER BY chapter_number DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, novel_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                int chapter_number = rs.getInt(3);
                String chapter_title = rs.getString(4);
                int view = rs.getInt(5);
                String content = rs.getString(6);
                Date date = rs.getDate(7);
                lc = new Chapter(id, novel_id, chapter_number, chapter_title, view, content, date);
                return lc;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void loadNovelSortByFollow(){
        novelSortByFollow = new ArrayList<>();
        String sql = "SELECT * FROM Novel_HE160853 ORDER BY follow DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String author = rs.getString(4);
                String infor = rs.getString(5);
                Date date = rs.getDate(6);
                int follow = rs.getInt(7);
                novelSortByFollow.add(new Novel(id, name, image, author, infor, date, follow));
            }
        } catch (Exception e) {
        }
        
    }
    
    public void changePassword(String password, String user){
        String sql = "UPDATE User_HE160853 SET password = ? WHERE [user] = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, user);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void changeName(String name, String user){
        String sql = "UPDATE User_HE160853 SET name = ? WHERE [user] = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setNString(1, name);
            ps.setString(2, user);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void loadFollowNovel(String user){
        followList = new ArrayList<>();
        String sql = "SELECT * FROM User_HE160853 u join Follow_HE160853 f on u.[user] = f.[user] join Novel_HE160853 n on n.id = f.novel_id WHERE u.[user] = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(13);
                String name = rs.getString(14);
                String image = rs.getString(15);
                String author = rs.getString(16);
                String infor = rs.getString(17);
                Date date = rs.getDate(18);
                int follow = rs.getInt(19);
                followList.add(new Novel(id, name, image, author, infor, date, follow));
            }
        } catch (Exception e) {
        }
    }
    
    public boolean isFollowNovel(String user, int novel_id){
        isFollow = false;
        String sql = "SELECT * FROM Follow_HE160853 WHERE [user] = ? AND novel_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, novel_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isFollow = true;
                return isFollow;
            }
        } catch (Exception e) {
        }
        return isFollow;
    }
    
    public void updateFollow(boolean rise, int novel_id){
        String sql = "";
        if (rise) {
            sql = "UPDATE Novel_HE160853 SET follow = follow + 1 WHERE id = ?";
        } else {
            sql = "UPDATE Novel_HE160853 SET follow = follow - 1 WHERE id = ?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, novel_id);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void insertFollowNovel(String user, int novel_id){
        String sql = "INSERT INTO Follow_HE160853 values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, novel_id);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void deleteFollowNovel(String user, int novel_id){
        String sql = "DELETE FROM Follow_HE160853 WHERE [user] = ? AND novel_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, novel_id);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void loadComment(int chapter_id){
        commentList = new ArrayList<>();
        String sql = "SELECT cc.chapter_id, cc.[user], u.name, u.level, u.exp, u.image, comment, cc.date FROM Chapter_HE160853 c join ChapterComment_HE160853 cc on c.id = cc.chapter_id join User_HE160853 u on cc.[user] = u.[user] WHERE cc.chapter_id = ? ORDER BY cc.date DESC, time DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chapter_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                String user = rs.getString(2);
                String name = rs.getString(3);
                int level = rs.getInt(4);
                int exp = rs.getInt(5);
                String image = rs.getString(6);
                String comment = rs.getString(7);
                Date date = rs.getDate(8);
                commentList.add(new Comment(chapter_id, user, name, level, exp, image, comment, date));
            }
        } catch (Exception e) {
        }
    }
    
    public void insertComment(int chapter_id, String user, String comment){
        String sql = "INSERT INTO ChapterComment_HE160853(chapter_id,[user],comment,date) VALUES (?,?,?,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chapter_id);
            ps.setString(2, user);
            ps.setNString(3, comment);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void loadReadList(String user){
        readList = new ArrayList<>();
        String sql = "SELECT TOP 1 n.id, n.name, n.image, n.author, n.infor, n.date, n.follow FROM Novel_HE160853 n join Chapter_HE160853 c on n.id = c.novel_id join ReadList_HE160853 r on c.id = r.chapter_id join User_HE160853 u on r.[user] = u.[user] WHERE r.[user] = ? ORDER BY r.time DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String author = rs.getString(4);
                String infor = rs.getString(5);
                Date date = rs.getDate(6);
                int follow = rs.getInt(7);
                readList.add(new Novel(id, name, image, author, infor, date, follow));
            }
        } catch (Exception e) {
        }
    }
    
    public void insertReadList(int chapter_id, String user){
        String sql = "INSERT INTO ReadList_HE160853 (chapter_id,[user],date) VALUES (?,?,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chapter_id);
            ps.setString(2, user);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void updateReadList(int chapter_id, String user){
        String sql = "UPDATE ReadList_HE160853 SET date = CURRENT_TIMESTAMP WHERE chapter_id = ? AND [user] = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, chapter_id);
            ps.setString(2, user);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public boolean isReadChapter(String user, int chapter_id){
        isRead = false;
        String sql = "SELECT * FROM ReadList_HE160853 WHERE [user] = ? AND chapter_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, chapter_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isRead = true;
                return isRead;
            }
        } catch (Exception e) {
        }
        return isRead;
    }
    
    public void chapterReadOfNovel(String user, int novel_id){
        chapterReadOfNovel = new ArrayList<>();
        String sql = "SELECT id, novel_id, chapter_number, chapter_title, [view], content, c.date FROM ReadList_HE160853 r join Chapter_HE160853 c on r.chapter_id = c.id WHERE [user] = ? AND novel_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, novel_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int id = rs.getInt(1);
                int chapter_number = rs.getInt(3);
                String chapter_title = rs.getString(4);
                int view = rs.getInt(5);
                String content = rs.getString(6);
                Date date = rs.getDate(7);
                chapterReadOfNovel.add(new Chapter(id, novel_id, chapter_number, chapter_title, view, content, date));
            }
        } catch (Exception e) {
        }
    }
    
    public void updatePassword(String user, String password){
        String sql = "UPDATE User_HE160853 SET password = ? WHERE [user] = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, user);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public User checkUser(String user, String email, String password_2, int ques_id, String ans){
        User u;
        String sql = "SELECT * FROM User_HE160853 WHERE [user] = ? AND email = ? AND password_2 = ? AND ques_id = ? AND ans = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, email);
            ps.setString(3, password_2);
            ps.setInt(4, ques_id);
            ps.setNString(5, ans);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString(2);
                String name = rs.getString(3);
                int level = rs.getInt(5);
                int exp = rs.getInt(6);
                String image = rs.getString(10);
                u = new User(user, password, name, password_2, level, exp, ques_id, ans, email, image);
                return u;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void insertNovel(int id, String name, String image, String author, String infor){
        String sql = "INSERT INTO Novel_HE160853 VALUES(?,?,?,?,?,CURRENT_TIMESTAMP,0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setNString(2, name);
            ps.setString(3, image);
            ps.setNString(4, author);
            ps.setNString(5, infor);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void insertGenre(int novel_id, String genre_id){
        String sql = "INSERT INTO ListGenreOfNovel_HE160853 VALUES(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, novel_id);
            ps.setString(2, genre_id);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public void insertChapter(int id, int novel_id, int chapter_number, String chapter_title, String content){
        String sql = "INSERT INTO Chapter_HE160853 VALUES(?,?,?,?,0,?,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, novel_id);
            ps.setInt(3, chapter_number);
            ps.setNString(4, chapter_title);
            ps.setNString(5, content);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    public Novel loadNovelByName(String name){
        Novel novel;
        String sql = "SELECT * FROM Novel_HE160853 WHERE name = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setNString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String image = rs.getString(3);
                String author = rs.getString(4);
                String infor = rs.getString(5);
                Date date = rs.getDate(6);
                int follow = rs.getInt(7);
                novel = new Novel(id, name, image, author, infor, date, follow);
                return novel;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    
    
    public static void main(String[] args) {
        MyConnect myConnect = new MyConnect();
    }

}
