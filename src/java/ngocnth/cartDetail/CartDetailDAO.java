
package ngocnth.cartDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import ngocnth.cart.Item;
import org.apache.log4j.Logger;

public class CartDetailDAO implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(CartDetailDAO.class);
    
    public boolean saveCartDetail(List<Item> list, int cartId, Connection con)
            throws NamingException, SQLException {

        PreparedStatement stm = null;

        try {
            String sql = "INSERT cartDetail(cartId, roomDetailId, quantity, price, statusId) "
                    + "VALUES(?, ?, ?, ?, 3)"; //is active
            stm = con.prepareStatement(sql);

            for (Item item : list) {
                int roomDetailId = item.getRoomDetail().getRoomDetailId();
                int quantity = item.getQuantity();
                double price = item.getRoomDetail().getPrice();
                
                stm.setInt(1, cartId);
                stm.setInt(2, roomDetailId);
                stm.setInt(3, quantity);
                stm.setDouble(4, price);
                
                stm.executeUpdate();
            }
            return true;
        } catch (Exception ex) {
            LOGGER.info("Exception: " + ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }
    
    public boolean updateCartDetailStatus(int cartId, int statusId, Connection con)
            throws NamingException, SQLException {

        PreparedStatement stm = null;

        try {
            String sql = "UPDATE cartDetail "
                    + "SET statusId = ? "
                    + "WHERE cartId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, statusId);
            stm.setInt(2, cartId);
            
            int row = stm.executeUpdate();
            if (row > 0)
                return true;
        } catch (Exception ex) {
            LOGGER.info("Exception: " + ex.getMessage());
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }
}
