
package ngocnth.hotel;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import ngocnth.util.DBHelper;

public class HotelDAO implements Serializable {
    
    public Map<Integer, HotelDTO> getHotels() throws NamingException, SQLException {
        
        Map<Integer, HotelDTO> map = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT hotelId, hotelName, hotelAddress, hotelEmail, hotelPhoneNumber, hotelImage, regionId, statusId "
                            + "FROM hotel";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    String hotelAddress = rs.getString("hotelAddress");
                    String hotelEmail = rs.getString("hotelEmail");
                    String hotelPhoneNumber = rs.getString("hotelPhoneNumber");
                    String hotelImage = rs.getString("hotelImage");
                    int regionId = rs.getInt("regionId");
                    int statusId = rs.getInt("statusId");
                    
                    HotelDTO dto = new HotelDTO(hotelId, hotelName, hotelAddress, hotelEmail, hotelPhoneNumber, hotelImage, regionId, statusId);

                    if (map != null)
                        map.put(hotelId, dto);
                    else {
                        map = new HashMap<>();
                        map.put(hotelId, dto);
                    }
                }
            }
        } finally {
            if (rs != null)
                rs.close();
            
            if (stm != null)
                stm.close();
            
            if (con != null)
                con.close();
        }
        return map;
    }
    
    public HotelDTO getHotelById(int hotelId) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT hotelId, hotelName, hotelAddress, hotelEmail, hotelPhoneNumber, hotelImage, regionId, statusId "
                            + "FROM hotel "
                            + "WHERE hotelId = ? "
                            + "AND statusId = ?";
                stm = con.prepareStatement(sql);
                
                stm.setInt(1, hotelId);
                stm.setInt(2, 1); //is available
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    String hotelName = rs.getString("hotelName");
                    String hotelAddress = rs.getString("hotelAddress");
                    String hotelEmail = rs.getString("hotelEmail");
                    String hotelPhoneNumber = rs.getString("hotelPhoneNumber");
                    String hotelImage = rs.getString("hotelImage");
                    int regionId = rs.getInt("regionId");
                    int statusId = rs.getInt("statusId");
                    
                    HotelDTO dto = new HotelDTO(hotelId, hotelName, hotelAddress, hotelEmail, hotelPhoneNumber, hotelImage, regionId, statusId);

                    return dto;
                }
            }
        } finally {
            if (rs != null)
                rs.close();
            
            if (stm != null)
                stm.close();
            
            if (con != null)
                con.close();
        }
        return null;
    }
}
