package Controller;

import DAL.MyConnect;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Smily
 */
@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {
    
    MyConnect myConnect;
    
    public void init(){
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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
    private User checkUser(String user, String password){
        for (User u : myConnect.getUserList()) {
            if (u.getUser().equals(user) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        myConnect.loadUser();
        myConnect.loadGenre();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if (u != null) {
            response.sendRedirect(request.getContextPath()+"/welcome");
        } else {
            request.setAttribute("myConnect", myConnect);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession();
        if (request.getParameter("logout") != null) {
            ses.setAttribute("user", null);
            doGet(request, response);
        }
        myConnect.loadUser();
        myConnect.loadGenre();
        request.setAttribute("myConnect", myConnect);
        
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        
        User u = checkUser(user, password);
        if (u == null) {
            try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Đăng Nhập</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            out.println("alert('Tài khoản hoặc mật khẩu không chính xác!!!')");
            out.println("window.location = './login'");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
        }
        } else {
            ses.setAttribute("user", u);
            response.sendRedirect(request.getContextPath()+"/welcome");
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
