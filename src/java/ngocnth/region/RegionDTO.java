
package ngocnth.region;

import java.io.Serializable;

public class RegionDTO implements Serializable {
    
    private int regionId;
    private String regionName;

    public RegionDTO() {
    }

    public RegionDTO(int regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    
    
}
