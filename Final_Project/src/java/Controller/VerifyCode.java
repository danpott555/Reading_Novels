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
public class VerifyCode extends HttpServlet {

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

//            
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
    /**
     * Handles the HTTP <code>POST</code> method.
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
        request.getRequestDispatcher("/verify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String authcode = request.getParameter("authcode");
//            String authcode = (String)request.getSession().getAttribute("authcode");
        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String password_2 = request.getParameter("password_2");
        int ques_id = Integer.parseInt(request.getParameter("ques_id"));
        String ans = request.getParameter("ans");
        if (code.equals(authcode)) {
            myConnect.insertUser(user, password, name, password_2, ques_id, ans, email);
            response.sendRedirect("login");

        } else {
            request.setAttribute("authcode", authcode);
//                    HttpSession ses = request.getSession();
//                    ses.setAttribute("authcode", authcode);
            request.setAttribute("user", user);
            request.setAttribute("password", password);
            request.setAttribute("name", name);
            request.setAttribute("password_2", password_2);
            request.setAttribute("ques_id", ques_id);
            request.setAttribute("ans", ans);
            request.setAttribute("email", email);
            doGet(request, response);
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
