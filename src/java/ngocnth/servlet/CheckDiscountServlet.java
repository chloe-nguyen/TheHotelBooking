
package ngocnth.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngocnth.cart.Cart;
import ngocnth.discount.DiscountDAO;
import org.apache.log4j.Logger;

@WebServlet(name = "CheckDiscountServlet", urlPatterns = {"/CheckDiscountServlet"})
public class CheckDiscountServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CheckDiscountServlet.class);
    
    private static final String VIEW_CART_PAGE = "view-cart-page";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String discountId = request.getParameter("txtDiscountId");
            DiscountDAO dao = new DiscountDAO();
            int percent = dao.getDiscountPercent(discountId);
            if (percent > 0) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    Cart cart = (Cart) session.getAttribute("CART");
                    double dec = cart.getTotalPrice() / 100 * percent;
                    request.setAttribute("DEC", dec);
                    request.removeAttribute("NON_AVAILABLE_DISCOUNT");
                }
            } else {
                request.setAttribute("NON_AVAILABLE_DISCOUNT", "This discount code is not existed!");
            }
        } catch (NamingException ex) {
            LOGGER.info("NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SQLException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_CART_PAGE);
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
