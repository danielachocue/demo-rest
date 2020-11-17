package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.ShoppingCart;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingCartRepositoryTest {

	private final static Logger log=LoggerFactory.getLogger(ShoppingCartRepositoryTest.class);
	
	private static Integer carId=null;
	
	private static String email="abaglowbn@furl.net";
	
	private static Integer payId=1;
	
	//Inyeccion de dependencia
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("save");
		
		ShoppingCart shoppingCart=new ShoppingCart();
		shoppingCart.setCarId(null);
		shoppingCart.setItems(2);
		shoppingCart.setTotal(1223564L);
		shoppingCart.setEnable("N");

		Optional<Customer> customerOptional=customerRepository.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email "+email+" no existe");
		
		Customer customer=customerOptional.get();
		shoppingCart.setCustomer(customer);
		
		Optional<PaymentMethod> paymentMethoOptional= paymentMethodRepository.findById(payId);
		assertTrue(paymentMethoOptional.isPresent(),"El PaymentMethod "+payId+" no existe");
		
		PaymentMethod paymentMethod= paymentMethoOptional.get();
		shoppingCart.setPaymentMethod(paymentMethod);
		
		shoppingCartRepository.save(shoppingCart);
		
		carId=shoppingCart.getCarId();
		
		assertNotNull(carId, "El carId es nulo");
		
		log.info("carId: "+carId);
	}
	
	@Test
	@Transactional
	@Order(2)
	void finById() {
		
		log.info("finById");
		
		Optional<ShoppingCart> shoppingCartOptional= shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent()," El shoppingCartOPtional con carId "+carId+" No existe");	
		
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		log.info("update");
		
		Optional<ShoppingCart> shoppingCartOptional= shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent()," El shoppingCartOPtional con carId "+carId+" No existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		shoppingCart.setEnable("Y");
		
		shoppingCartRepository.save(shoppingCart);
		
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		log.info("delete");
		
		Optional<ShoppingCart> shoppingCartOptional= shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent()," El shoppingCartOPtional con carId "+carId+" No existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		
		shoppingCartRepository.delete(shoppingCart);
		
	}
}
