package ncei.gis.web

import groovy.util.logging.Log4j
import ncei.gis.GeometryService
import ncei.gis.domain.*
import org.springframework.beans.factory.annotation.*
import org.springframework.http.*
//import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


@Log4j
@RestController
@RequestMapping("/city")
class CityController {
    private final CityRepository cityRepository
    private final GeometryService geometryService
    @Autowired
    CityController (CityRepository cityRepository, GeometryService geometryService) {
        this.cityRepository = cityRepository
        this.geometryService = geometryService
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    City read(@PathVariable Long id) {
        //TODO needs to return 404 if id not found
        return this.cityRepository.findOne(id)
    }


    @RequestMapping (method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody City input) {
        println input
        this.cityRepository.save(input)

        HttpHeaders httpHeaders = new HttpHeaders()
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(input.getId()).toUri())
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
}