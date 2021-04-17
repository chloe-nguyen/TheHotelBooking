
package ngocnth.region;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import ngocnth.util.DBHelper;

public class RegionDAO implements Serializable {
    
    public Map<Integer, String> getRegions() throws NamingException, SQLException {
        
        Map<Integer, String> map = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT regionId, regionName FROM region";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int regionId = rs.getInt("regionId");
                    String regionName = rs.getString("regionName");

                    if (map != null)
                        map.put(regionId, regionName);
                    else {
                        map = new HashMap<>();
                        map.put(regionId, regionName);
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
