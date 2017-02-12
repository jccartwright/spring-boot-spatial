package ncei.gis.domain

import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.*

@Repository
interface CityRepository extends CrudRepository <City, Long> {

}