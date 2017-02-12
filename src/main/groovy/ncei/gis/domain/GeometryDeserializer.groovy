package ncei.gis.domain

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.io.ParseException
import com.vividsolutions.jts.io.WKTReader
import jdk.nashorn.internal.parser.JSONParser

class GeometryDeserializer extends JsonDeserializer<Geometry>{
    private static WKTReader fromText = new WKTReader()


    @Override
    Geometry deserialize(JsonParser jsonParser, DeserializationContext context)
    throws IOException, JsonProcessingException{
        String wkt = jsonParser.getText()
        try {
            return fromText.read(wkt)
        } catch (ParseException e) {
            throw new RuntimeException("Not a valid WKT string:" + wkt)
        }
    }

}
