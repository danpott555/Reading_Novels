package Controller;

import DAL.MyConnect;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author Smily
 */
@WebServlet(name = "Forget", urlPatterns = {"/forget"})
public class Forget extends HttpServlet {
    
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
            out.println("<title>Servlet Forget</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Forget at " + request.getContextPath() + "</h1>");
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
        myConnect.loadQuestion();
        myConnect.loadUser();
        request.setAttribute("myConnect", myConnect);
        request.getRequestDispatcher("ForgetPassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private String getRandom() {
        Random rand = new Random();
        int n = rand.nextInt(90000000) + 10000000;
        return String.format("%8d", n);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("user");
        String email = request.getParameter("email");
        String password_2 = request.getParameter("password_2");
        int ques_id = Integer.parseInt(request.getParameter("ques_id"));
        String ans = request.getParameter("ans");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Quên Mật Khẩu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<script>");
            if (myConnect.checkUser(user, email, password_2, ques_id, ans) != null) {
                String password = getRandom();
                out.println("alert('Mật khẩu của bạn đã được reset về " + password + "')");
                out.println("window.location = './login'");
                User u = myConnect.checkUser(user, email, password_2, ques_id, ans);
                myConnect.updatePassword(u.getUser(), password);
            } else {
                out.println("alert('Thông tin bạn nhập không chính xác!')");
                out.println("window.location = './forget'");
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
