package ngocnth.servlet;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ngocnth.util.SendingEmail;
import org.apache.log4j.Logger;

@WebServlet(name = "SendLinkResetPasswordServlet", urlPatterns = {"/SendLinkResetPasswordServlet"})
public class SendLinkResetPasswordServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SendLinkResetPasswordServlet.class);

    private static final String FORGOT_PASSWORD_PAGE = "forgot-password-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = FORGOT_PASSWORD_PAGE;

        try {
            String email = request.getParameter("txtEmail");
            SendingEmail sender = new SendingEmail(email);
            sender.sendEmail();
        } catch (MessagingException ex) {
            LOGGER.info("MessagingException: " + ex.getMessage());
            request.setAttribute("INVALID_EMAIL", "This email is not invalid. Please enter another!");
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
