package ngocnth.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngocnth.account.AccountDTO;
import ngocnth.cart.Cart;
import ngocnth.cart.CartDAO;
import ngocnth.roomDetail.RoomDetailDAO;
import org.apache.log4j.Logger;

@WebServlet(name = "ConfirmServlet", urlPatterns = {"/ConfirmServlet"})
public class ConfirmServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConfirmServlet.class);

    private static final String LOGIN_PAGE = "login-page";
    private static final String INDEX_PAGE = "index-page";
    private static final String VIEW_CART_PAGE = "view-cart-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
                if (account != null) {
                    if (account.getRole()) {
                        url = INDEX_PAGE;
                    } else {
                        Cart cart = (Cart) session.getAttribute("CART");
                        if (cart != null) {
                            String email = account.getEmail();
                            String discountId = request.getParameter("txtDiscountId");
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date checkinDate = formatter.parse((String) session.getAttribute("CHECKIN"));
                            Date checkoutDate = formatter.parse((String) session.getAttribute("CHECKOUT"));
                            Date cartDate = new Date();
                            int statusId = 3; //is active

                            CartDAO cartDao = new CartDAO();
                            int id = cartDao.saveCart(email, checkinDate, checkoutDate, cartDate, discountId, statusId, cart.getCart());
                            if (id > 0) {
                                RoomDetailDAO rdDao = new RoomDetailDAO();
                                rdDao.updateRoomDetailStatus();
                                session.removeAttribute("INVALID_QUANTITY");
                                session.removeAttribute("CART");
                                request.setAttribute("BOOKING_SUCCESS", true);
                                url = VIEW_CART_PAGE;
                            } else {
                                session.setAttribute("INVALID_QUANTITY", true);
                            }
                        }
                    }
                }
            }
        } catch (ParseException ex) {
            LOGGER.info("ParseException: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.info("SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.info("NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //response.sendRedirect(url);
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
