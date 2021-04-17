
package ngocnth.discount;

import java.io.Serializable;
import java.util.Date;

public class DiscountDTO implements Serializable {
    
    private String discountId;
    private String discountName;
    private int discountPercent;
    private Date createDate;
    private Date exp;
    private int statusId;

    public DiscountDTO() {
    }

    public DiscountDTO(String discountId, String discountName, int discountPercent, Date createDate, Date exp, int statusId) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountPercent = discountPercent;
        this.createDate = createDate;
        this.exp = exp;
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }
    
}
