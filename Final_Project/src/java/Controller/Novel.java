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
public class Novel extends HttpServlet {

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
            out.println("<title>Servlet Novel</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Novel at " + request.getContextPath() + "</h1>");
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
        if (u != null) {
            myConnect.loadFollowNovel(u.getUser());
            if (request.getParameter("truyen") != null) {
                int novel_id = Integer.parseInt(request.getParameter("truyen"));
                myConnect.isFollowNovel(u.getUser(), novel_id);
                myConnect.loadNovelDetail(novel_id);
                myConnect.loadChapterList(novel_id);
                myConnect.loadGenreOfNovel1(novel_id);
                myConnect.loadLastestChapter(novel_id);
                myConnect.loadLastestChapter1(novel_id);
                myConnect.chapterReadOfNovel(u.getUser(), novel_id);
            }
        } else {
            if (request.getParameter("truyen") != null) {
                int novel_id = Integer.parseInt(request.getParameter("truyen"));
                myConnect.loadNovelDetail(novel_id);
                myConnect.loadChapterList(novel_id);
                myConnect.loadGenreOfNovel1(novel_id);
                myConnect.loadLastestChapter(novel_id);
                myConnect.loadLastestChapter1(novel_id);
            }
        }
        request.setAttribute("myConnect", myConnect);
        request.getRequestDispatcher("/Novel.jsp").forward(request, response);
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
        int fl = Integer.parseInt(request.getParameter("fl"));
        if (u != null) {
            if (fl == 1) {
                myConnect.insertFollowNovel(u.getUser(), novel_id);
                myConnect.updateFollow(true, novel_id);
            }
            if (fl == 0) {
                myConnect.deleteFollowNovel(u.getUser(), novel_id);
                myConnect.updateFollow(false, novel_id);
            }
        }

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
