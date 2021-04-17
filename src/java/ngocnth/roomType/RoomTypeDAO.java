
package ngocnth.roomType;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import ngocnth.util.DBHelper;

public class RoomTypeDAO implements Serializable {
    
    public Map<Integer, String> getRoomTypes() throws NamingException, SQLException {
        
        Map<Integer, String> map = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT roomTypeId, roomTypeName FROM roomType";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int roomTypeId = rs.getInt("roomTypeId");
                    String roomTypeName = rs.getString("roomTypeName");

                    if (map != null)
                        map.put(roomTypeId, roomTypeName);
                    else {
                        map = new HashMap<>();
                        map.put(roomTypeId, roomTypeName);
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
}
