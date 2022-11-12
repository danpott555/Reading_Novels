package Controller;

import Model.*;
import DAL.MyConnect;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Smily
 */
public class Chapter extends HttpServlet {
    
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
            out.println("<title>Servlet Chapter</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Chapter at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        myConnect.loadGenre();
        myConnect.loadNovel();
        if (request.getParameter("truyen") != null && request.getParameter("chapter") != null) {
            int novel_id = Integer.parseInt(request.getParameter("truyen"));
            int chapter_number = Integer.parseInt(request.getParameter("chapter"));
            int id = Integer.parseInt(request.getParameter("truyen")+request.getParameter("chapter"));
            myConnect.loadNovelDetail(novel_id);
            myConnect.loadChapterList(novel_id);
            myConnect.loadLastestChapter1(novel_id);
            if (u != null) {
                myConnect.loadReadList(u.getUser());
                if (myConnect.isReadChapter(u.getUser(), id)) {
                    myConnect.updateReadList(id, u.getUser());
                } else {
                    myConnect.insertReadList(id, u.getUser());
                }
            }
            myConnect.loadChapter(novel_id, chapter_number);
            myConnect.loadComment(id);
        }
        request.setAttribute("myConnect", myConnect);
        request.getRequestDispatcher("Chapter.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        int novel_id = Integer.parseInt(request.getParameter("truyen"));
        int chapter_id = Integer.parseInt(request.getParameter("chapter"));
        int id = Integer.parseInt(request.getParameter("truyen")+request.getParameter("chapter"));
        String comment = request.getParameter("comment");
        myConnect.insertComment(id, u.getUser(), comment);
        doGet(request, response);
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
