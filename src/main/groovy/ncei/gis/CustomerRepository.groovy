package ncei.gis

import org.springframework.data.repository.CrudRepository;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    @Query("select c from Customer c where within(c.geom, ?1) = true")
    List<Customer> findWithin(Geometry filter)
}