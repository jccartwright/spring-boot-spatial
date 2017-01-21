package ncei.gis

import javax.persistence.*
import com.vividsolutions.jts.geom.*
import org.hibernate.annotations.*
import org.hibernate.spatial.*
import groovy.transform.ToString

@Entity
@ToString(includeNames=true, includeFields = true)
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    private Geometry geom

    //default constructor required by JPA and not otherwise used
    protected Customer() {}
}