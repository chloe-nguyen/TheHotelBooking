package ngocnth.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ngocnth.account.AccountDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
    
    private static final String LOGIN_PAGE = "login-page";
    private static final String REGISTER_PAGE = "register-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");

        String url = REGISTER_PAGE;

        try {
            AccountDAO dao = new AccountDAO();

            if (dao.isDuplicatedEmail(email))
                request.setAttribute("DUPLICATED", "This email is being used.");
            else if (!confirmPassword.equals(password))
                request.setAttribute("NOT_MATCH", "Confirm is not match.");
            else {
                String fullname = request.getParameter("txtFullName");
                String phoneNumber = request.getParameter("txtPhoneNumber");
                String address = request.getParameter("txtAddress");
                Date createDate = new Date();
                boolean role = false; //is user
                int statusId = 3; //is activated
                String encryptPassword = DigestUtils.sha256Hex(password);
                
                boolean result = dao.createNewAccount(email, encryptPassword, fullname, 
                        phoneNumber, address, createDate, role, statusId);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }

        } catch (NamingException ex) {
            LOGGER.info("NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SQLException: " + ex.getMessage());
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
