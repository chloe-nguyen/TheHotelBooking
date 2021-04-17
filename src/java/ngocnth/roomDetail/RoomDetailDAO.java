package ngocnth.roomDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import ngocnth.cart.Item;
import ngocnth.util.DBHelper;
import org.apache.log4j.Logger;

public class RoomDetailDAO implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(RoomDetailDAO.class);

    public List<RoomDetailDTO> searchAvailableRooms(String region, String roomType) throws NamingException, SQLException {

        List<RoomDetailDTO> list = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT roomDetailId, quantity, price, image, roomDetail.roomTypeId, roomDetail.hotelId, roomDetail.statusId "
                        + "FROM roomType, roomDetail, hotel, region "
                        + "WHERE roomType.roomTypeId = roomDetail.roomTypeId "
                        + "AND roomDetail.hotelId = hotel.hotelId "
                        + "AND hotel.regionId = region.regionId "
                        + "AND roomType.roomTypeName LIKE ? "
                        + "AND region.regionName LIKE ? "
                        + "AND roomDetail.statusId = ?";
                stm = con.prepareStatement(sql);

                stm.setString(1, "%" + roomType + "%");
                stm.setString(2, "%" + region + "%");
                stm.setInt(3, 1); //is available

                rs = stm.executeQuery();
                while (rs.next()) {
                    int roomDetailId = rs.getInt("roomDetailId");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    int roomTypeId = rs.getInt("roomTypeId");
                    int hotelId = rs.getInt("hotelId");
                    int statusId = rs.getInt("statusId");
                    
                    RoomDetailDTO dto = new RoomDetailDTO(roomDetailId, quantity, price, image, roomTypeId, hotelId, statusId);
                    
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
    
    public List<RoomDetailDTO> getAvailableRooms(int hotelId) throws NamingException, SQLException {

        List<RoomDetailDTO> list = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT roomDetailId, quantity, price, image, roomDetail.roomTypeId, roomDetail.hotelId, roomDetail.statusId "
                        + "FROM roomDetail, hotel "
                        + "WHERE roomDetail.hotelId = hotel.hotelId "
                        + "AND roomDetail.hotelId = ? "
                        + "AND roomDetail.statusId = ?";
                stm = con.prepareStatement(sql);

                stm.setInt(1, hotelId);
                stm.setInt(2, 1); //is available

                rs = stm.executeQuery();
                while (rs.next()) {
                    int roomDetailId = rs.getInt("roomDetailId");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    int roomTypeId = rs.getInt("roomTypeId");
                    int statusId = rs.getInt("statusId");
                    
                    RoomDetailDTO dto = new RoomDetailDTO(roomDetailId, quantity, price, image, roomTypeId, hotelId, statusId);
                    
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
    
    public boolean updateRoomDetailQuantity(List<Item> list, Connection con) throws SQLException {
        PreparedStatement stm = null;

        try {
            con.setAutoCommit(false);
            String sql = "UPDATE roomDetail "
                        + "SET quantity = quantity - ? "
                        + "WHERE roomDetailId = ?";
            stm = con.prepareStatement(sql);

            for (Item item : list) {
                int roomDetailId = item.getRoomDetail().getRoomDetailId();
                int quantity = item.getQuantity();
                
                stm.setInt(1, quantity);
                stm.setInt(2, roomDetailId);
                
                stm.executeUpdate();
            }
            con.commit();
            con.setAutoCommit(true);
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
    
    public boolean updateRoomDetailStatus() throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE roomDetail "
                            + "SET statusId = 0 " //non-available
                            + "WHERE quantity <= 0";
                stm = con.prepareStatement(sql);
                int row = stm.executeUpdate();
                if (row > 0)
                    return true;
            }
        } finally {
            if (stm != null)
                stm.close();
            
            if (con != null)
                con.close();
        }
        return false;
    }
}
