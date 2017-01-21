package ncei.gis

import org.springframework.stereotype.Service
import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.io.ParseException
import com.vividsolutions.jts.io.WKTReader


@Service
public class CustomerService {
    WKTReader fromText = new WKTReader()
	
	//utility method to create a Geometry from a WKT string
    Geometry wktToGeometry(String wktString) {
        Geometry geom = null
        try {
            geom = fromText.read(wktString)
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktString)
        }
        return geom
    }
}
