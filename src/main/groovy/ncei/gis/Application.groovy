package ncei.gis

import ncei.gis.domain.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.*


@SpringBootApplication
class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {
    @Autowired
    CustomerRepository repository

    @Autowired
    CityRepository cityRepository

	@Autowired
	GeometryService service

    static void main(String[] args) {
        SpringApplication.run Application, args
    }

    @Override
    void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToGeometryConverter())
    }


    @Override
    public void run(String... strings) throws Exception {
	    cityRepository.save(new City(
                name: 'Denver',
                country: 'US',
                population: 1405300,
                capital: false,
                geom: service.wktToGeometry('POINT(-105.069999694824 39.75)')
        ))

        // save a couple of customers
        repository.save(new Customer(firstName:  "Jack", lastName:  "Bauer"))
        repository.save(new Customer(firstName:  "Chloe", lastName:  "O'Brian"))
        repository.save(new Customer(firstName:  "Kim", lastName:  "Bauer"))
        repository.save(new Customer(firstName:  "David", lastName:  "Palmer"))
        repository.save(new Customer(firstName:  "Michelle", lastName:  "Dessler"))

        repository.save(new Customer(firstName:"Roger", lastName:"Rabbit", geom: service.wktToGeometry('POINT(-105 40)')))

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
        repository.findWithin(service.wktToGeometry('POLYGON((-107 39, -102 39, -102 41, -107 41, -107 39))')).each {
            println it
        }
    }
}