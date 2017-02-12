package ncei.gis.domain

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import ncei.gis.GeometryService
import org.springframework.beans.factory.annotation.Autowired

import javax.persistence.*
import com.vividsolutions.jts.geom.*
import org.hibernate.annotations.*
import org.hibernate.spatial.*
import groovy.transform.ToString

@Entity
class City {
    @Id
    @GeneratedValue
    private long id
    private String name
    private String country
    private Float population
    private Boolean capital
    @JsonDeserialize(using=GeometryDeserializer.class)
    private Geometry geom

    //default constructor required by JPA and not otherwise used
    protected City() {}

    //explicit getters and setters appear to be required for Spring to create instances from JSON
    Long getId() {
        return id
    }
    void setId(Long id) {
        this.id = id
    }
    String getName() {
        return this.name
    }
    void setName(String name) {
        this.name = name
    }
    String getCountry() {
        return this.country
    }
    void setCountry(String country) {
        this.country = country
    }
    String getGeom() {
        return this.geom
    }
    void setGeom(Geometry geom) {
        this.geom = geom
    }
    Float getPopulation() {
        return this.population
    }
    void setPopulation(Float population) {
        this.population = population
    }
    Boolean isCapital() {
        return this.capital
    }
    void setCapital(Boolean capital) {
        this.capital = capital
    }

    String toString() {
        "${name}, ${country}"
    }
}