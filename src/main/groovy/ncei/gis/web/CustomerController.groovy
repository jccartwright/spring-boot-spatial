package ncei.gis.web

import com.vividsolutions.jts.geom.Geometry
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ncei.gis.*
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMethodMappingNamingStrategy
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class CustomerController {
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody Customer input) {
        println input

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping("/greeting")
    String greeting(@RequestParam(value="geometry", required = false) Geometry geom) {
        println geom
        return "Hello Customer!"
    }
}