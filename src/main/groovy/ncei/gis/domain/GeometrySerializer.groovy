package ncei.gis.domain

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.vividsolutions.jts.geom.Geometry

class GeometrySerializer extends JsonSerializer<Geometry> {
    @Override
    void serialize(Geometry value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        gen.writeString(value.toString())
    }
}
