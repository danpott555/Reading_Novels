package Controller;

import DAL.MyConnect;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 *
 * @author Smily
 */
@MultipartConfig
public class AdminNewNovel extends HttpServlet {

    MyConnect myConnect;

    public void init() {
        myConnect = new MyConnect();

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminPage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        myConnect.loadGenre();
        myConnect.loadNovel();
        myConnect.loadNovelSortByFollow();
        for (int i = 1; i < myConnect.getNovels().size() + 1; i++) {
            myConnect.loadGenreOfNovel(i);
            myConnect.loadLastestChapter(i);
        }
        request.setAttribute("myConnect", myConnect);
        request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        String name = request.getParameter("name");
        Part part = request.getPart("image");
        String realPath = "/img/novel";
        String fileName = part.getSubmittedFileName();
        String image = realPath + "/" + fileName;
        String author = request.getParameter("author");
        String genre1 = request.getParameter("genre1");
        String genre2 = request.getParameter("genre2");
        String genre3 = request.getParameter("genre3");
        String infor = request.getParameter("infor");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int id = myConnect.getNovels().size() + 1;
        myConnect.insertNovel(id, name, image, author, infor);

        if (!genre1.equals("none")) {
            myConnect.insertGenre(id, genre1);
        }
        if (!genre2.equals("none")) {
            myConnect.insertGenre(id, genre2);
        }
        if (!genre3.equals("none")) {
            myConnect.insertGenre(id, genre3);
        }
        myConnect.insertChapter(id * 10 + 1, id, 1, title, content);

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Đăng truyện mới</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Đăng truyện thành công!')");
            out.println("window.location = './adminnewnovel'");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
