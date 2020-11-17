package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

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
class PaymenMethodServiceTest {
	
	private final static Logger log=LoggerFactory.getLogger(PaymenMethodServiceTest.class);

	private static Integer payId= null;
	
	//Inyeccion de dependencia
	@Autowired
	PaymentMethodService paymentmethodService;

	@Test
	@Order(1)
	void save() throws Exception{
		log.info("save");
		
		//se declara la variable y se le asigna memoria
		PaymentMethod paymentMethod= new PaymentMethod();
		
		paymentMethod.setEnable("Y");
		paymentMethod.setName("GANE");
				
		//se guarda la informacion
		paymentMethod=paymentmethodService.save(paymentMethod);
		payId=paymentMethod.getPayId();
				
		assertNotNull(payId,"El pay es nulo");
				
		log.info("PayId: "+payId);
	}
	
	@Test
	@Transactional
	@Order(2)
	void fndById() throws Exception {
		
		log.info("findById");
		
		Optional<PaymentMethod> paymentmethodOptional=paymentmethodService.findById(payId);
		
		//Se consulta y se trae el objeto y siga si es true osea que existe
		assertTrue(paymentmethodOptional.isPresent(),"El paymenMethod ya existe");
		PaymentMethod paymentMethod=paymentmethodService.findById(payId).get();
		
		//si no existe
		assertNotNull(paymentMethod,"El Paymenmethod no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() throws Exception{
		
		log.info("updDate");
		
		//Se consulta y se trae el objeto y siga si es true osea que existe
		assertTrue(paymentmethodService.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentmethodService.findById(payId).get();
		//se edita
		paymentMethod.setEnable("N");
		
		paymentmethodService.update(paymentMethod);
	}
	
	
	@Test
	@Transactional
	@Order(4)
	void delete() throws Exception{
		
		log.info("delete");
		
		//Se consulta y se trae el objeto y siga si es true osea que existe
		assertTrue(paymentmethodService.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentmethodService.findById(payId).get();
		//se elimina
		paymentmethodService.delete(paymentMethod);
	}

}
