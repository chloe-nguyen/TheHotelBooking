package ngocnth.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngocnth.cart.Cart;
import ngocnth.roomDetail.RoomDetailDTO;
import org.apache.log4j.Logger;

@WebServlet(name = "AddRoomToCartServlet", urlPatterns = {"/AddRoomToCartServlet"})
public class AddRoomToCartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddRoomToCartServlet.class);

    private final static String DETAIL_HOTEL_CONTROLLER = "detail-hotel";
    private final static String SEARCH_CONTROLLER = "search";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "";
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                int roomDetailId = Integer.parseInt(request.getParameter("txtId"));
                int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                double price = Double.parseDouble(request.getParameter("txtPrice"));
                String image = request.getParameter("txtImage");
                int roomTypeId = Integer.parseInt(request.getParameter("txtTypeId"));
                int hotelId = Integer.parseInt(request.getParameter("txtHotelId"));
                int statusId = Integer.parseInt(request.getParameter("txtStatusId"));

                String page = request.getParameter("page");

                RoomDetailDTO roomDetail = new RoomDetailDTO(roomDetailId, quantity, price, image, roomTypeId, hotelId, statusId);

                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                }

                cart.addItemToCart(roomDetail);
                session.setAttribute("CART", cart);

                if (page.equals("detail-hotel")) {
                    String txtHotelId = request.getParameter("txtHotelId");
                    url = DETAIL_HOTEL_CONTROLLER + "?txtHotelId=" + txtHotelId;
                } else {
                    String region = request.getParameter("cboRegion");
                    String roomType = request.getParameter("cboRoomType");
                    String checkinDate = request.getParameter("dateCheckin");
                    String checkoutDate = request.getParameter("dateCheckout");

                    url = SEARCH_CONTROLLER 
                            + "?cboRegion=" + region
                            + "&cboRoomType=" + roomType
                            + "&dateCheckin=" + checkinDate
                            + "&dateCheckout=" + checkoutDate;
                }
            }
        } catch (NumberFormatException ex) {
            LOGGER.info("NumberFormatException: " + ex.getMessage());
        } catch (NullPointerException ex) {
            LOGGER.info("NullPointerException: " + ex.getMessage());
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
