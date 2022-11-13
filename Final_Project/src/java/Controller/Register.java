package Controller;

import Model.*;
import DAL.MyConnect;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Smily
 */
@WebServlet(urlPatterns = {"/register"})
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        myConnect.loadQuestion();
        myConnect.loadUser();
        request.setAttribute("myConnect", myConnect);
        request.getRequestDispatcher("/Register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean checkRegister(String user) {
        for (User u : myConnect.getUserList()) {
            if (user.equalsIgnoreCase(u.getUser())) {
                return false;
            }
        }
        return true;
    }

    private String getRandom() {
        Random rand = new Random();
        int n = rand.nextInt(900000) + 100000;
        return String.format("%6d", n);
    }

    private Message prepareMessage(Session session, String fromEmail, String recepient, String code) throws MessagingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Veification of Register from KHOTRUYEN!");
            message.setText("You have already registered from KHOTRUYEN!\n" + "Your code is: " + code);
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    private void sendEmail(String recepient) throws MessagingException {
//        Properties pr = new Properties();
//        pr.setProperty("mail.smtp.auth", "true");
//        pr.setProperty("mail.smtp.starttls.enable", "true");
//        pr.setProperty("mail.smtp.host", "smtp.gmail.com");
//        pr.setProperty("mail.smtp.port", "587");
//
//        String fromEmail = "dannthe160853@fpt.edu.vn";
//        String password = "Thuadanvan123";
//
//        Session session = Session.getInstance(pr, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, password);
//            }
//
//        });
//        String code = getRandom();
//        Message message = prepareMessage(session, fromEmail, recepient, code);
//
//        Transport.send(message);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        final String userRegex = "^[a-zA-Z0-9][a-zA-Z0-9_]{6,18}[a-zA-Z0-9]$";
        final String passRegex = "^[a-zA-Z0-9][^|]{7,48}[a-zA-Z0-9]$";
        request.setCharacterEncoding("UTF-8");
        myConnect.loadQuestion();
        myConnect.loadUser();
        String user = request.getParameter("user");
        if (!checkRegister(user) || user.equals("") || !user.matches(userRegex)) {
            doGet(request, response);
        } else {
            String password = request.getParameter("password");
            String re_pass = request.getParameter("re_pass");
            if (!password.equals(re_pass) || !password.matches(passRegex)) {
                doGet(request, response);
            } else {
                String name = request.getParameter("name");
                String password_2 = request.getParameter("password_2");
                int ques_id = Integer.parseInt(request.getParameter("ques_id"));
                String ans = request.getParameter("ans");
//                myConnect.insertUser(user, password, name, password_2, ques_id, ans, email);
                String email = request.getParameter("email");
                try {
                    Properties pr = new Properties();
                    pr.setProperty("mail.smtp.auth", "true");
                    pr.setProperty("mail.smtp.starttls.enable", "true");
                    pr.setProperty("mail.smtp.host", "smtp.gmail.com");
                    pr.setProperty("mail.smtp.port", "587");

                    String fromEmail = "dannthe160853@fpt.edu.vn";
                    String passEmail = "Thuadanvan123";

                    Session session = Session.getInstance(pr, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(fromEmail, passEmail);
                        }

                    });
                    String authcode = getRandom();
                    Message message = prepareMessage(session, fromEmail, email, authcode);

                    Transport.send(message);
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
                    request.getRequestDispatcher("Verify.jsp").forward(request, response);
                } catch (MessagingException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
