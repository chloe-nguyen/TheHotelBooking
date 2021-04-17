
package ngocnth.cart;

import java.io.Serializable;
import ngocnth.roomDetail.RoomDetailDTO;

public class Item implements Serializable {
    
    private RoomDetailDTO roomDetail;
    private int quantity;

    public Item() {
    }

    public Item(RoomDetailDTO roomDetail, int quantity) {
        this.roomDetail = roomDetail;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public RoomDetailDTO getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetailDTO roomDetail) {
        this.roomDetail = roomDetail;
    }
}
