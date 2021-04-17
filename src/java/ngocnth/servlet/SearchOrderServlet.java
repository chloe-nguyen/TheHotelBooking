package ngocnth.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngocnth.account.AccountDTO;
import ngocnth.cart.CartDAO;
import ngocnth.cart.CartDTO;
import org.apache.log4j.Logger;

@WebServlet(name = "SearchOrderServlet", urlPatterns = {"/SearchOrderServlet"})
public class SearchOrderServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ViewBookingHistoryServlet.class);

    private static final String INDEX_PAGE = "index-page";
    private static final String VIEW_BOOKING_HISTORY_PAGE = "view-booking-history-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = INDEX_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String orderId = request.getParameter("txtOrderId");
                String date = request.getParameter("dateOrder");

                AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                String email = account.getEmail();

                CartDAO dao = new CartDAO();
                if (!orderId.isEmpty() && date.isEmpty()) {
                    CartDTO cart = dao.searchCartsById(email, orderId);
                    session.setAttribute("CART_RESULT", cart);
                }
                if (!date.isEmpty() && orderId.isEmpty()) {
                    Date dateOrder = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    List<CartDTO> list = dao.searchCartsByDate(email, dateOrder);
                    session.setAttribute("CART_LIST", list);
                } 
                
                if (orderId.isEmpty() && date.isEmpty()) {
                    List<CartDTO> list = dao.getCarts(email);
                    session.setAttribute("CART_LIST", list);
                }
                url = VIEW_BOOKING_HISTORY_PAGE;
            }
        } catch (NamingException ex) {
            LOGGER.info("NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SQLException: " + ex.getMessage());
        } catch (ParseException ex) {
            LOGGER.info("ParseException: " + ex.getMessage());
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
