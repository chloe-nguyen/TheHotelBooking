package ngocnth.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import ngocnth.util.DBHelper;

public class AccountDAO implements Serializable {

    public AccountDTO getAccount(String email, String password) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT email, password, fullname, phoneNumber, address, createDate, role, statusId "
                        + "FROM account "
                        + "WHERE email = ? AND password = ? AND statusId = 3"; //is activated

                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);

                rs = stm.executeQuery();

                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    String phoneNumber = rs.getString("phoneNumber");
                    String address = rs.getString("address");
                    Date createDate = rs.getDate("createDate");
                    boolean role = rs.getBoolean("role");
                    int statusId = rs.getInt("statusId");
                    AccountDTO dto = new AccountDTO(email, password, fullname, 
                            phoneNumber, address, createDate, role, statusId);
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
    
    public boolean createNewAccount(String email, String password, String fullname, 
            String phoneNumber, String address, Date createDate, boolean role, int statusId) 
            throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT account(email, password, fullName, phoneNumber, address, createDate, role, statusId) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                
                stm.setString(1, email);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setString(4, phoneNumber);
                stm.setString(5, address);
                java.sql.Date sqlDate = new java.sql.Date(createDate.getTime());
                stm.setDate(6, sqlDate);
                stm.setBoolean(7, role);
                stm.setInt(8, statusId);
                
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
    
    public boolean isDuplicatedEmail(String email) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT email "
                        + "FROM account "
                        + "WHERE email = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                rs = stm.executeQuery();

                if (rs.next()) {
                    return true;
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
        return false;
    }
    
    public boolean changePassword(String email, String newPassword) throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE account "
                        + "SET password = ? "
                        + "WHERE email = ? AND statusId = 3"; //is active
                stm = con.prepareStatement(sql);
                stm.setString(1, newPassword);
                stm.setString(2, email);
                
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
