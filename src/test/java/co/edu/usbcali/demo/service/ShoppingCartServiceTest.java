package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
class ShoppingCartServiceTest {
	
	private final static Logger log=LoggerFactory.getLogger(ShoppingCartServiceTest.class);
	
	private static Integer carId=null;
	
	private static String email="abaglowbn@furl.net";
	
	private static Integer payId=1;
	
	//Inyeccion de dependencia
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	CustomerService customerService;
	@Autowired
	PaymentMethodService paymentMethodService;

	@Test
	@Transactional
	@Order(1)
	void save() throws Exception{
		log.info("save");
		
		//se declara la variable y se le asigna memoria
		ShoppingCart shoppingCart=new ShoppingCart();
		
		shoppingCart.setCarId(null);
		shoppingCart.setItems(2);
		shoppingCart.setTotal(1223564L);
		shoppingCart.setEnable("Y");
		
		//Se consulta y se trae el objeto y siga si es true osea que existe
		Optional<Customer> customerOptional=customerService.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email "+email+" no existe");
		
		Customer customer=customerOptional.get();
		shoppingCart.setCustomer(customer);
		
		Optional<PaymentMethod> paymentMethoOptional= paymentMethodService.findById(payId);
		assertTrue(paymentMethoOptional.isPresent(),"El PaymentMethod "+payId+" no existe");
		
		PaymentMethod paymentMethod= paymentMethoOptional.get();
		shoppingCart.setPaymentMethod(paymentMethod);
		
		shoppingCartService.save(shoppingCart);
		
		//se guarda la informacion
		carId=shoppingCart.getCarId();
		
		assertNotNull(carId, "El carId es nulo");
		
		log.info("carId: "+carId);
	}
	
	@Test
	@Transactional
	@Order(2)
	void finById() throws Exception{
		
		log.info("finById");
		//Se consulta y se trae el objeto y siga si es true osea que existe
		Optional<ShoppingCart> shoppingCartOptional= shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent()," El shoppingCartOPtional con carId "+carId+" No existe");	
		
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() throws Exception{
		
		log.info("update");
		
		//Se consulta y se trae el objeto y siga si es true osea que existe
		Optional<ShoppingCart> shoppingCartOptional= shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent()," El shoppingCartOPtional con carId "+carId+" No existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		shoppingCart.setEnable("Y");
		
		shoppingCartService.update(shoppingCart);
		
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() throws Exception{
		
		log.info("delete");
		//Se consulta y se trae el objeto y siga si es no es true osea que no existe
		Optional<ShoppingCart> shoppingCartOptional= shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent()," El shoppingCartOPtional con carId "+carId+" No existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		
		shoppingCartService.delete(shoppingCart);
		
	}

}
