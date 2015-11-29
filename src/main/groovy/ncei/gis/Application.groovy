package ncei.gis

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.io.ParseException
import com.vividsolutions.jts.io.WKTReader


@SpringBootApplication
class Application implements CommandLineRunner {
    @Autowired
    CustomerRepository repository;

    static void main(String[] args) {
        SpringApplication.run Application, args
    }


    @Override
    public void run(String... strings) throws Exception {
        // save a couple of customers
        repository.save(new Customer(firstName:  "Jack", lastName:  "Bauer"))
        repository.save(new Customer(firstName:  "Chloe", lastName:  "O'Brian"))
        repository.save(new Customer(firstName:  "Kim", lastName:  "Bauer"))
        repository.save(new Customer(firstName:  "David", lastName:  "Palmer"))
        repository.save(new Customer(firstName:  "Michelle", lastName:  "Dessler"))

        repository.save(new Customer(firstName:"Roger", lastName:"Rabbit", geom: wktToGeometry('POINT(-105 40)')))

        // fetch all customers
        println("Customers found with findAll():")
        println("-------------------------------")

        repository.findAll().each {
            println it
        }
        println()

        // fetch an individual customer by ID
        Customer customer = repository.findOne(1L)
        println("Customer found with findOne(1L):")
        println("--------------------------------")
        println(customer)
        println()

        // fetch customers by last name
        println("Customer found with findByLastName('Bauer'):")
        println("--------------------------------------------")

        repository.findByLastName("Bauer").each {
            println it
        }

        println("Customers found within POLYGON((-107 39, -102 39, -102 41, -107 41, -107 39)):")
        println("--------------------------------");
        repository.findWithin(wktToGeometry('POLYGON((-107 39, -102 39, -102 41, -107 41, -107 39))')).each {
            println it
        }
    }

    //utility method to create a Geometry from a WKT string
    private Geometry wktToGeometry(String wktString) {
        WKTReader fromText = new WKTReader()
        Geometry geom = null
        try {
            geom = fromText.read(wktString)
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktString)
        }
        return geom
    }
}