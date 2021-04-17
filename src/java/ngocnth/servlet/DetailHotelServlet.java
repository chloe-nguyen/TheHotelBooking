
package ngocnth.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ngocnth.hotel.HotelDAO;
import ngocnth.hotel.HotelDTO;
import ngocnth.roomDetail.RoomDetailDAO;
import ngocnth.roomDetail.RoomDetailDTO;
import org.apache.log4j.Logger;

@WebServlet(name = "DetailHotelServlet", urlPatterns = {"/DetailHotelServlet"})
public class DetailHotelServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(DetailHotelServlet.class);
    
    private final static String DETAIL_HOTEL_PAGE = "detail-hotel-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = DETAIL_HOTEL_PAGE;
        try {
            int hotelId = Integer.parseInt(request.getParameter("txtHotelId"));
            RoomDetailDAO dao = new RoomDetailDAO();
            List<RoomDetailDTO> list = dao.getAvailableRooms(hotelId);
            
            HotelDAO hDao = new HotelDAO();
            HotelDTO hotel = hDao.getHotelById(hotelId);
                    
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("LIST_ROOM_BY_HOTEL", list);
                session.setAttribute("HOTEL_DTO", hotel);
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
