 package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerTest {
	
	private final static String email="danielachocue2@gmailcom";
	
	private final static Logger log=LoggerFactory.getLogger(CustomerTest.class);
	
	//patron de inyeccion de dependencia
	@Autowired
	EntityManager entityManager;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		//Siga si no es nulo
		assertNotNull(entityManager,"El entityManager es Null");
		Customer customer= entityManager.find(Customer.class, email);
		
		//siga si es nulo
		assertNull(customer,"el cliente con email "+email+" ya existe");
		
		//Se llena el objeto
		customer=new Customer();
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Daniela");
		customer.setPhone("317 575 8488");
		customer.setToken("NADEA2356AEFNAG");
		
		entityManager.persist(customer);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		//siga si no es nulo
		assertNotNull(entityManager,"el entityManager es nulo");
		Customer customer= entityManager.find(Customer.class, email);
		
		//siga si no es nulo
		assertNotNull(customer,"el cliente con email "+email+" no existe");
		
		log.info(customer.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		//siga si es nulo
		assertNotNull(entityManager,"el entityManager es nulo");
		
		Customer customer= entityManager.find(Customer.class, email);
		
		//siga si no es nulo significa que existe
		assertNotNull(customer,"el cliente con email"+email+"no existe");
		
		customer.setEnable("N");
		entityManager.merge(customer);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		assertNotNull(entityManager,"el entityManager es nulo");
		
		Customer customer= entityManager.find(Customer.class, email);
		
		//siga si no es nulo
		assertNotNull(customer,"el cliente con email"+email+"no existe");
		
		entityManager.remove(customer);
	}
	

}
