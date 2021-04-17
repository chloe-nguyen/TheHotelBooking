
package ngocnth.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import ngocnth.hotel.HotelDAO;
import ngocnth.hotel.HotelDTO;
import ngocnth.region.RegionDAO;
import ngocnth.roomType.RoomTypeDAO;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyServletListener implements ServletContextListener, HttpSessionListener {

    private static final Logger LOGGER = Logger.getLogger(MyServletListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Khoi tao file log
        ServletContext context = sce.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("/") + log4jConfigFile;
        
        PropertyConfigurator.configure(fullPath);
        
        //Khoi tao map tham chieu den resource
        Map<String, String> lableMap = null;
        FileReader f = null;
        BufferedReader bf = null;
        
        String realPath = context.getRealPath("/");
        String path = realPath + "WEB-INF/lableMap.txt";
        
        try {
            f = new FileReader(path);
            bf = new BufferedReader(f);
            
            while (bf.ready()) {
                String line = bf.readLine();
                String[] arr = line.split("=");
                if (arr.length == 2) {
                    if (lableMap != null)
                        lableMap.put(arr[0], arr[1]);
                    else {
                        lableMap = new HashMap<>();
                        lableMap.put(arr[0], arr[1]);
                    }
                }
            }
            context.setAttribute("LABLE_MAP", lableMap);
            
        } catch (FileNotFoundException ex ) {
            LOGGER.info("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            LOGGER.info("IOException: " + ex.getMessage());
        } finally {
            try {
                if (bf != null)
                    bf.close();
                
                if (f != null)
                    f.close();
            } catch (IOException ex) {
                LOGGER.info("IOException: " + ex.getMessage());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        try {
            HttpSession session = se.getSession();
            
            //Get current date
            Date date = new Date();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(date);
            session.setAttribute("TODAY", today);
            
            //Get map of regions
            RegionDAO rDao = new RegionDAO();
            Map<Integer, String> regionMap = rDao.getRegions();
            if (regionMap != null)
                session.setAttribute("REGION_MAP", regionMap);
            
            //Get map of room types
            RoomTypeDAO roomDao = new RoomTypeDAO();
            Map<Integer, String> roomMap = roomDao.getRoomTypes();
            if (roomMap != null)
                session.setAttribute("ROOMTYPE_MAP", roomMap);
            
            //Get map of hotels
            HotelDAO hotelDao = new HotelDAO();
            Map<Integer, HotelDTO> hotelMap = hotelDao.getHotels();
            if (hotelMap != null)
                session.setAttribute("HOTEL_MAP", hotelMap);
            
        } catch(SQLException ex) {
            LOGGER.info("SQLException: " + ex.getMessage());
        } catch(NamingException ex) {
            LOGGER.info("NamingException: " + ex.getMessage());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
