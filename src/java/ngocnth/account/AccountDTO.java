
package ngocnth.account;

import java.io.Serializable;
import java.util.Date;

public class AccountDTO implements Serializable {
    
    private String email;
    private String password;
    private String fullname;
    private String phoneNumber;
    private String address;
    private Date createDate;
    private boolean role;
    private int statusId;

    public AccountDTO() {
    }

    public AccountDTO(String email, String password, String fullname, 
            String phoneNumber, String address, Date createDate, boolean role, int statusId) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createDate = createDate;
        this.role = role;
        this.statusId = statusId;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
    
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}
