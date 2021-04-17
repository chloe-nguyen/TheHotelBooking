
package ngocnth.roomType;

import java.io.Serializable;

public class RoomTypeDTO implements Serializable {
    
    private int roomTypeId;
    private String roomTypeName;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(int roomTypeId, String roomTypeName) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
    
    
}
