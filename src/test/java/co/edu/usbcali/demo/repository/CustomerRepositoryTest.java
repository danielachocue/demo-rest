package co.edu.usbcali.demo.repository;



import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {
	
	private final static Logger log=LoggerFactory.getLogger(CustomerRepositoryTest.class);
	
	private final static String email="danielachocue2@gmail.com";
	
	//Inyeccion de dependencia
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		
		//Me retorna un Optional
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si False, valida si me esta devolviendo un opcional
		assertFalse(customerOptional.isPresent(),"El Customer ya no exite");
		
		//Se le asigna memoria
		Customer customer= new Customer();
		
		//Se llena el objeto
		customer=new Customer();
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Daniela");
		customer.setPhone("317 575 8488");
		customer.setToken("NADEA2356AEFNAG");
		
		//Grabar
		customerRepository.save(customer);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		
		log.info("findById");
		
		//Me retorna un Optional
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si True, quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El Customer ya no exite");

	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		log.info("updDate");
		
		//Me retorna un Optional
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si True, quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El Customer no exite");
		
		Customer customer= customerOptional.get();
		
		customer.setEnable("N");
		
		customerRepository.save(customer);

	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		log.info("delete");
		
		//Me retorna un Optional
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		//Siga si True, quiere decir que existe
		assertTrue(customerOptional.isPresent(),"El Customer no exite");
		
		Customer customer= customerOptional.get();
		
		
		customerRepository.delete(customer);

	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		//funcional
		log.info("findAll");
		customerRepository.findAll().forEach(customer->{
			log.info(customer.getName());
			log.info(customer.getAddress());
		});
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		log.info("Count: "+customerRepository.count());
	}
	
	@Test
	@Transactional
	@Order(7)
	void findByEnableAndEmail() {
		List<Customer> customers=customerRepository.findByEnableAndEmail("Y", "bropsdf@imgur.com");
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(8)
	void findCustomerLikemar() {
		List<Customer> customers=customerRepository.findCustomerLikemar();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(9)
	void findBynameEquals() {
		List<Customer> customers=customerRepository.findByNameEquals("Suzanna Elstone");
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
			log.info("Phone: "+customer.getPhone());
		});
	}
	
	@Test
	@Transactional
	@Order(10)
	void findnNameorderBy() {
		List<Customer> customers=customerRepository.findByNameOrderBy();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(11)
	void findByPhonelike() {
		List<Customer> customers=customerRepository.findByPhoneLike();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Phone: "+customer.getPhone());
		});
	}
	
	@Test
	@Transactional
	@Order(12)
	void findBynameIn() {
		List<Customer> customers=customerRepository.findByNameIn();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email: "+customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(13)
	void findByNameOremail() {
		List<Customer> customers=customerRepository.findByNameOrEmail("Daniela", "mchristy2@is.gd");
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Enable: "+customer.getEnable());
		});
	}

}
