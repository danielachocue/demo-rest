package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.jpa.ProductTest;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductServiceTest {
	
	private final static String proid= "App1540";
	
	private final static Logger log= LoggerFactory.getLogger(ProductTest.class);
	
	//Inyeccion de dependencia
	@Autowired
	ProductService productService;
	
	@Test
	@Order(1)
	void save() throws Exception{
		
		log.info("save");
		
		//Se le asigna memoria
		Product product= new Product();
		
		//Se llena el objeto
		product= new Product();
		product.setName("phone");
		product.setDetail("Color Negro");
		product.setProId(proid);
		product.setEnable("X");
		product.setPrice(1236);
		product.setImage("wasw1456ad");
		
		//Grabar
		productService.save(product);	
	}
	
	@Test
	@Order(2)
	void findById()throws Exception {
		
		log.info("findById");
		
		//Me retorna un Optional
		Optional<Product> productOptional=productService.findById(proid);
		
		//Siga si True, quiere decir que existe
		assertTrue(productOptional.isPresent(),"El producto ya no existe");

	}
	
	@Test
	@Order(3)
	void update()throws Exception {
		
		log.info("updDate");
		
		//Me retorna un Optional
		Optional<Product> productOptional=productService.findById(proid);
		
		//Siga si True, quiere decir que existe
		assertTrue(productOptional.isPresent(),"El product no existe");
		
		Product product= productOptional.get();
		
		product.setEnable("N");
		
		productService.update(product);

	}
		
	@Test
	@Order(4)
	void delete() throws Exception{
		
		log.info("delete");
		
		//Me retorna un Optional
		Optional<Product> productOptional=productService.findById(proid);
		
		//Siga si True, quiere decir que existe
		assertTrue(productOptional.isPresent(),"El product no existe");
		
		Product product= productOptional.get();
		
		productService.delete(product);

	}

}
