package ngocnth.cart;

import java.io.Serializable;
import java.util.Date;

public class CartDTO implements Serializable {

    private int id;
    private String cartId;
    private String email;
    private Date checkinDate;
    private Date checkoutDate;
    private Date cartDate;
    private String discountId;
    private int statusId;

    public CartDTO() {
    }

    public CartDTO(int id, String cartId, String email, Date checkinDate, Date checkoutDate, Date cartDate, String discountId, int statusId) {
        this.id = id;
        this.cartId = cartId;
        this.email = email;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.cartDate = cartDate;
        this.discountId = discountId;
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getCartDate() {
        return cartDate;
    }

    public void setCartDate(Date cartDate) {
        this.cartDate = cartDate;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }
    
    
}
