
package ngocnth.hotel;

import java.io.Serializable;

public class HotelDTO implements Serializable {
    private int hotelId;
    private String hotelName;
    private String hotelAddress;
    private String hotelEmail;
    private String hotelPhoneNumber;
    private String hotelImage;
    private int regionId;
    private int statusId;

    public HotelDTO() {
    }

    public HotelDTO(int hotelId, String hotelName, String hotelAddress, 
            String hotelEmail, String hotelPhoneNumber, String hotelImage, int regionId, int statusId) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelEmail = hotelEmail;
        this.hotelPhoneNumber = hotelPhoneNumber;
        this.hotelImage = hotelImage;
        this.regionId = regionId;
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    
    
}
