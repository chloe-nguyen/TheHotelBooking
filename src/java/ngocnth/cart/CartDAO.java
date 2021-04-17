package ngocnth.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import ngocnth.cartDetail.CartDetailDAO;
import ngocnth.roomDetail.RoomDetailDAO;
import ngocnth.util.DBHelper;

public class CartDAO implements Serializable {

    public int saveCart(String email, Date checkinDate, Date checkoutDate, Date cartDate, String discountId, int statusId, List<Item> list)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = "INSERT cart(email, checkinDate, checkoutDate, cartDate, discountId, statusId) "
                        + "VALUES(?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stm.setString(1, email);
                java.sql.Date sqlCheckin = new java.sql.Date(checkinDate.getTime());
                stm.setDate(2, sqlCheckin);
                java.sql.Date sqlCheckout = new java.sql.Date(checkoutDate.getTime());
                stm.setDate(3, sqlCheckout);
                java.sql.Date sqlCartDate = new java.sql.Date(cartDate.getTime());
                stm.setDate(4, sqlCartDate);
                stm.setString(5, discountId);
                if (discountId.trim().equals("")) {
                    stm.setNull(5, Types.VARCHAR);
                }
                stm.setInt(6, statusId);

                stm.executeUpdate();
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);

                    CartDetailDAO cdDao = new CartDetailDAO();
                    boolean result = cdDao.saveCartDetail(list, id, con);
                    if (result) {
                        RoomDetailDAO rdDao = new RoomDetailDAO();
                        boolean successfull = rdDao.updateRoomDetailQuantity(list, con);
                        if (successfull) {
                            con.commit();
                            con.setAutoCommit(true);
                            return id;
                        }
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public List<CartDTO> getCarts(String email) throws NamingException, SQLException {

        List<CartDTO> list = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT id, cartId, checkinDate, checkoutDate, cartDate, discountId, statusId "
                        + "FROM cart "
                        + "WHERE email = ? AND statusId = 3 "
                        + "ORDER BY cartDate desc";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String cartId = rs.getString("cartId");
                    Date checkinDate = rs.getDate("checkinDate");
                    Date checkoutDate = rs.getDate("checkoutDate");
                    Date cartDate = rs.getDate("cartDate");
                    String discountId = rs.getString("discountId");
                    int statusId = rs.getInt("statusId");
                    
                    CartDTO dto = new CartDTO(id, cartId, email, checkinDate, checkoutDate, cartDate, discountId, statusId);
                    
                    if (list != null)
                        list.add(dto);
                    else {
                        list = new ArrayList<>();
                        list.add(dto);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return list;
    }
    
    public CartDTO searchCartsById(String email, String cartId) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT id, checkinDate, checkoutDate, cartDate, discountId, statusId "
                        + "FROM cart "
                        + "WHERE email = ? AND cartId = ? AND statusId = 3 ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, cartId);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    Date checkinDate = rs.getDate("checkinDate");
                    Date checkoutDate = rs.getDate("checkoutDate");
                    Date cartDate = rs.getDate("cartDate");
                    String discountId = rs.getString("discountId");
                    int statusId = rs.getInt("statusId");
                    
                    CartDTO dto = new CartDTO(id, cartId, email, checkinDate, checkoutDate, cartDate, discountId, statusId);                    
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
    public List<CartDTO> searchCartsByDate(String email, Date dateOrder) throws NamingException, SQLException {

        List<CartDTO> list = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT id, cartId, checkinDate, checkoutDate, cartDate, discountId, statusId "
                        + "FROM cart "
                        + "WHERE email = ? AND cartDate = ? AND statusId = 3 "
                        + "ORDER BY cartDate desc";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                java.sql.Date sqlDate = new java.sql.Date(dateOrder.getTime());
                stm.setDate(2, sqlDate);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String cartId = rs.getString("cartId");
                    Date checkinDate = rs.getDate("checkinDate");
                    Date checkoutDate = rs.getDate("checkoutDate");
                    Date cartDate = rs.getDate("cartDate");
                    String discountId = rs.getString("discountId");
                    int statusId = rs.getInt("statusId");
                    
                    CartDTO dto = new CartDTO(id, cartId, email, checkinDate, checkoutDate, cartDate, discountId, statusId);
                    
                    if (list != null)
                        list.add(dto);
                    else {
                        list = new ArrayList<>();
                        list.add(dto);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return list;
    }
    
    public boolean updateStatusCart(String cartId, int status) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE cart "
                        + "SET statusId = ? "
                        + "WHERE cartId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, status);
                stm.setString(2, cartId);
                
                int row = stm.executeUpdate();
                if (row > 0)
                    return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
