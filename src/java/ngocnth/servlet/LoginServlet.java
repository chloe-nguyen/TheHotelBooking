package ngocnth.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngocnth.account.AccountDAO;
import ngocnth.account.AccountDTO;
import ngocnth.util.VerifyUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    private static final String INVALID_PAGE = "invalid-page";
    private static final String INDEX_PAGE = "index-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = INVALID_PAGE;
        try {
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyUtil.verify(gRecaptchaResponse);

            if (verify) {
                String email = request.getParameter("txtEmail");
                String password = request.getParameter("txtPassword");
                String encryptPassword = DigestUtils.sha256Hex(password);

                AccountDAO dao = new AccountDAO();
                AccountDTO dto = dao.getAccount(email, encryptPassword);

                if (dto != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("ACCOUNT", dto);
                    url = INDEX_PAGE;
                }
            }

        } catch (NamingException ex) {
            LOGGER.info("NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SQLException: " + ex.getMessage());
        } catch (Exception ex) {
            LOGGER.info("Exception: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
