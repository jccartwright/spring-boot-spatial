package ncei.gis

import com.vividsolutions.jts.io.ParseException
import com.vividsolutions.jts.io.WKTReader
import org.springframework.core.convert.converter.*
import com.vividsolutions.jts.geom.*

/*
 * based on a tutorial at http://logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-mvc-custom-converter/
 */
class StringToGeometryConverter implements Converter<String, Geometry> {
    WKTReader fromText = new WKTReader()


    @Override
    Geometry convert(String wktString) {
        Geometry geom = null
        try {
            geom = fromText.read(wktString)
        } catch (ParseException e) {
            println e
            throw new RuntimeException("Not a WKT string:" + wktString)
        }
        return geom
    }
}
