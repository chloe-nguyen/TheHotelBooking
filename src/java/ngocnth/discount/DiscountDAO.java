
package ngocnth.discount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import ngocnth.util.DBHelper;

public class DiscountDAO implements Serializable {
    
    public boolean addNewDiscount(String discountId, String discountName, 
            int discountPercent, Date createDate, Date exp, int statusId) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT discount (discountId, discountName, discountPercent, createDate, exp, statusId) "
                            + "VALUES (?, ?, ?, ?, ?, ?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, discountId.toUpperCase());
                stm.setString(2, discountName);
                stm.setInt(3, discountPercent);
                java.sql.Date sqlCreateDate = new java.sql.Date(createDate.getTime());
                stm.setDate(4, sqlCreateDate);
                java.sql.Date sqlExp = new java.sql.Date(exp.getTime());
                stm.setDate(5, sqlExp);
                stm.setInt(6, statusId);
                
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
    
    public boolean existedDiscount(String discountId) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT discountId "
                            + "FROM discount "
                            + "WHERE discountId LIKE ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, discountId.toUpperCase());
                
                rs = stm.executeQuery();
                if (rs.next())
                    return true;
            }
        } finally {
            if (rs != null)
                rs.close();
            
            if (stm != null)
                stm.close();
            
            if (con != null)
                con.close();
        }
        return false;
    }
    
    public List<DiscountDTO> getDiscounts() throws NamingException, SQLException {
        
        List<DiscountDTO> list = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT discountId, discountName, discountPercent, createDate, exp, statusId "
                            + "FROM discount";
                
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    String discountId = rs.getString("discountId");
                    String discountName = rs.getString("discountName");
                    int discountPercent = rs.getInt("discountPercent");
                    Date createDate = rs.getDate("createDate");
                    Date exp = rs.getDate("exp");
                    int statusId = rs.getInt("statusId");
                    
                    DiscountDTO dto = new DiscountDTO(discountId, discountName, discountPercent, createDate, exp, statusId);
                    
                    if (list != null)
                        list.add(dto);
                    else {
                        list = new ArrayList<>();
                        list.add(dto);
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
        return list;
    }
    
    public int getDiscountPercent(String discountId) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT discountId, discountName, discountPercent, createDate, exp, statusId "
                            + "FROM discount "
                            + "WHERE discountId LIKE ? AND statusId = 1"; //is available
                
                stm = con.prepareStatement(sql);
                stm.setString(1, discountId.toUpperCase());
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    int discountPercent = rs.getInt("discountPercent");
                    return discountPercent;
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
        return 0;
    }
}
