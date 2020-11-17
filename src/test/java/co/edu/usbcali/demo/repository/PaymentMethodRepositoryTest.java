package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodRepositoryTest {
	
	private final static Logger log=LoggerFactory.getLogger(PaymentMethodRepositoryTest.class);

	private static Integer payId= null;
	
	//Inyeccion de Dependencia
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Test
	@Order(1)
	@Transactional
	void save() {
		//se declara la variable y se le asigna memoria
		PaymentMethod paymentMethod= new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("EFECTY");
		
		//se guarda la informacions
		paymentMethod=paymentMethodRepository.save(paymentMethod);
		payId=paymentMethod.getPayId();
		
		assertNotNull(payId,"El pay es nullo");
		
		log.info("PayId: "+payId);
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void fndById() {
		
		//Se consulta y se trae el objeto
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		
		assertNotNull(paymentMethod,"El Paymenmethod no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		
		//Se consulta y se trae el objeto
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		paymentMethod.setEnable("N");
		
		paymentMethodRepository.save(paymentMethod);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		//Se consulta y se trae el objeto
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		
		paymentMethodRepository.delete(paymentMethod);
	}
}
