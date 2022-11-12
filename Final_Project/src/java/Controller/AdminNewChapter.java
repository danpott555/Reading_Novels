package Controller;

import DAL.MyConnect;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Smily
 */
public class AdminNewChapter extends HttpServlet {

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
            out.println("<title>Servlet AdminNewChapter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminNewChapter at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("AdminNewChapter.jsp").forward(request, response);
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
        String name = request.getParameter("name").trim();
        Model.Novel n = myConnect.loadNovelByName(name);
        String chapter_title = request.getParameter("chapter_title");
        String content = request.getParameter("content");

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cập nhật chapter mới</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            if (n == null) {
                out.println("alert('Không tìm thấy truyện!')");
                out.println("window.location = './adminnewchapter'");
            } else {
                Model.Chapter lastestChapter = myConnect.loadLastestChapter1(n.getId());
                int id = Integer.parseInt(String.valueOf(n.getId()) + String.valueOf(lastestChapter.getChapter_number() + 1));
                myConnect.insertChapter(id, n.getId(), lastestChapter.getChapter_number() + 1, chapter_title, content);
                out.println("alert('Cập nhật thành công!')");
                out.println("window.location = './adminnewchapter'");
            }
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
