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
import ngocnth.discount.DiscountDAO;
import ngocnth.discount.DiscountDTO;
import org.apache.log4j.Logger;

@WebServlet(name = "AddDiscountServlet", urlPatterns = {"/AddDiscountServlet"})
public class AddDiscountServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddDiscountServlet.class);

    private static final String ADD_DISCOUNT_PAGE = "add-discount-page";
    private static final String VIEW_DISCOUNT_PAGE = "view-discount-page";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ADD_DISCOUNT_PAGE;
        try {
            String id = request.getParameter("txtId");
            String name = request.getParameter("txtName");
            int percent = Integer.parseInt(request.getParameter("numPercent"));
            Date createDate = new Date();
            Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateExp"));
            int status = 1; //is available

            DiscountDAO dDao = new DiscountDAO();
            if (dDao.existedDiscount(id)) {
                request.setAttribute("DUPPLICATED_ID", "This discount code has been existed!");
            } else {
                boolean result = dDao.addNewDiscount(id, name, percent, createDate, exp, status);
                if (result) {
                    List<DiscountDTO> list = dDao.getDiscounts();
                    request.setAttribute("DISCOUNT_LIST", list);
                    url = VIEW_DISCOUNT_PAGE;
                }
            }
        } catch (ParseException ex) {
            LOGGER.info("ParseException: " + ex.getMessage());
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
