
package ngocnth.roomDetail;

import java.io.Serializable;

public class RoomDetailDTO implements Serializable {
    private int roomDetailId;
    private int quantity;
    private double price;
    private String image;
    private int roomTypeId;
    private int hotelId;
    private int statusId;

    public RoomDetailDTO() {
    }

    public RoomDetailDTO(int roomDetailId, int quantity, double price, String image, int roomTypeId, int hotelId, int statusId) {
        this.roomDetailId = roomDetailId;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.roomTypeId = roomTypeId;
        this.hotelId = hotelId;
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    
    
}
