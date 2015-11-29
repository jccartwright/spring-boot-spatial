package ncei.gis

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Geometry
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type

import groovy.transform.ToString

@Entity
@ToString(includeNames=true, includeFields = true)
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    @Type(type="org.hibernate.spatial.GeometryType")
    private Geometry geom

    //default constructor required by JPA and not otherwise used
    protected Customer() {}
}