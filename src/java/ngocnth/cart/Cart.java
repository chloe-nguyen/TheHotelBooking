
package ngocnth.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ngocnth.roomDetail.RoomDetailDTO;

public class Cart implements Serializable {
    List<Item> cart;
    int totalQuantity;
    double totalPrice;
    
    public List<Item> getCart() {
        return cart;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalQuantity() {
        totalQuantity = 0;
        if (cart == null)
            return;
        
        for (Item item : cart) {
            totalQuantity += item.getQuantity();
        }
    } 
    
    public void setTotalPrice() {
        totalPrice = 0;
        if (cart == null)
            return;
        
        for (Item item : cart) {
            totalPrice += item.getQuantity() * item.getRoomDetail().getPrice();
        }
    }
    
    public void addItemToCart(RoomDetailDTO roomDetail) {
        if (cart == null)
            cart = new ArrayList<>();
        
        int quantity = 1;
        for (Item item : cart) {
            if (item.getRoomDetail().getRoomDetailId() == roomDetail.getRoomDetailId()) {
                quantity = item.getQuantity();
                item.setQuantity(quantity + 1);
                setTotalQuantity();
                setTotalPrice();
                return;
            }
        }
        
        Item newItem = new Item(roomDetail, quantity);
        cart.add(newItem);
        setTotalQuantity();
        setTotalPrice();
    }
    
    public boolean updateItemQuantity(int id, int quantity) {
        for (Item item : cart) {
            if (item.getRoomDetail().getRoomDetailId() == id) {
                if (item.getRoomDetail().getQuantity() >= quantity) {
                    item.setQuantity(quantity);
                    setTotalQuantity();
                    setTotalPrice();
                    return true;
                }
            }
        }
        return false;
    }
    
    public void removeItemFromCart(int id) {
        for (Item item : cart) {
            if (item.getRoomDetail().getRoomDetailId() == id) {
                cart.remove(item);
                setTotalQuantity();
                setTotalPrice();
                
                if (cart.isEmpty())
                    cart = null;
                return;
            }
        }
    }
}
