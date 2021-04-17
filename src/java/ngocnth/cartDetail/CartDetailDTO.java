
package ngocnth.cartDetail;

import java.io.Serializable;

public class CartDetailDTO implements Serializable {
    
    private int cartDetailId;
    private int cartId;
    private int roomDetailId;
    private int quantity;
    private double price;
    private int statusId;

    public CartDetailDTO() {
    }

    public CartDetailDTO(int cartDetailId, int cartId, int roomDetailId, int quantity, double price, int statusId) {
        this.cartDetailId = cartDetailId;
        this.cartId = cartId;
        this.roomDetailId = roomDetailId;
        this.quantity = quantity;
        this.price = price;
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getRoomDetailId() {
        return roomDetailId;
    }

    public void setRoomDetailId(int roomDetailId) {
        this.roomDetailId = roomDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
